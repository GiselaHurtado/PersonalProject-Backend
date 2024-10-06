package dev.gisela.paddle_tennis_couch_backend.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gisela.paddle_tennis_couch_backend.dtos.UserDto;
import dev.gisela.paddle_tennis_couch_backend.models.Profile;
import dev.gisela.paddle_tennis_couch_backend.models.Role;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, ProfileService profileService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.profileService = profileService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerNewUser(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getEmail());
        userRepository.save(user);

        Set<Role> roles = Set.of(roleService.getRoleByName("ROLE_USER"));
        user.setRoles(roles);

        Profile profile = new Profile(userDto.getEmail(), user);
        profileService.save(profile);
    }

    public User saveUser(User user, Set<String> roleNames, String email) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = roleNames.stream()
                .map(roleService::getRoleByName)
                .collect(Collectors.toSet());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        Profile profile = new Profile(email, savedUser);
        profileService.save(profile);

        return savedUser;
    }

    public Optional<User> updateUser(Long id, UserDto userDto, Set<String> roleNames, String email) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDto.getUsername());

            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }

            Set<Role> roles = roleNames.stream()
                .map(roleService::getRoleByName)
                .collect(Collectors.toSet());
            user.setRoles(roles);

            User updatedUser = userRepository.save(user);

            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email is required");
            }
            Profile profile = profileService.findByUserId(user.getId())
                    .orElse(new Profile(email, updatedUser));

            profile.setEmail(email);
            profileService.save(profile);

            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDto> findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.flatMap(user -> {
            Optional<Profile> profileOptional = profileService.findByUserId(user.getId());
            String email = profileOptional.map(Profile::getEmail).orElse(null);

            return Optional.of(new UserDto(
                user.getId(),  
                user.getUsername(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                email
            ));
        });
    }

    public String getEmailByUserId(Long userId) {
        Optional<Profile> profileOptional = profileService.findByUserId(userId);
        return profileOptional.map(Profile::getEmail).orElse(null);
    }
}
