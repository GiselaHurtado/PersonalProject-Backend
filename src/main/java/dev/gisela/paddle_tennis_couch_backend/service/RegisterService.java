package dev.gisela.paddle_tennis_couch_backend.service;

import dev.gisela.paddle_tennis_couch_backend.dtos.RegisterDto;
import dev.gisela.paddle_tennis_couch_backend.facades.EncoderFacade;
import dev.gisela.paddle_tennis_couch_backend.models.Profile;
import dev.gisela.paddle_tennis_couch_backend.models.Role;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final EncoderFacade encoderFacade;
    private final EmailService emailService;

    public RegisterService(UserRepository userRepository, RoleService roleService,
            ProfileService profileService, EncoderFacade encoderFacade,
            EmailService emailService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.profileService = profileService;
        this.encoderFacade = encoderFacade;
        this.emailService = emailService;
    }

    public String registerUser(RegisterDto newRegisterDto) {
        if (userRepository.findByUsername(newRegisterDto.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        String passwordDecoded = encoderFacade.decode("base64", newRegisterDto.getPassword());
        String passwordEncoded = encoderFacade.encode("bcrypt", passwordDecoded);

        User user = new User(newRegisterDto.getUsername(), passwordEncoded);
        user.setRoles(assignDefaultRole());

        User savedUser = userRepository.save(user);

        Profile profile = new Profile(newRegisterDto.getEmail(), savedUser);
        profileService.save(profile);

        sendConfirmationEmail(newRegisterDto.getEmail(), newRegisterDto.getUsername());

        return "Usuario registrado exitosamente. Por favor, revisa tu correo electrónico para la confirmación.";
    }

    private void sendConfirmationEmail(String email, String username) {
        String subject = "Bienvenido a la aplicación Tennis Coach";
        String text = "Estimado " + username + ",\n\nGracias por registrarte. Tu cuenta ha sido creada exitosamente.";
        emailService.sendEmail(email, subject, text);
    }

    private Set<Role> assignDefaultRole() {
        Role defaultRole = roleService.getById(1L);
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        return roles;
    }
}
