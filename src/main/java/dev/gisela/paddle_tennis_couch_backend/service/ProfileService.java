package dev.gisela.paddle_tennis_couch_backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.gisela.paddle_tennis_couch_backend.models.Profile;
import dev.gisela.paddle_tennis_couch_backend.repositories.ProfileRepository;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public String getEmailByUserId(Long userId) {
        Optional<Profile> profileOptional = profileRepository.findById(userId);
        if (profileOptional.isPresent()) {
            return profileOptional.get().getEmail();
        }

        return null;
    }

}
