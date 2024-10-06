package dev.gisela.paddle_tennis_couch_backend.services;

import dev.gisela.paddle_tennis_couch_backend.models.Profile;
import dev.gisela.paddle_tennis_couch_backend.repositories.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedProfile() {
       
        Profile profile = new Profile("john@example.com", null);
        when(profileRepository.save(profile)).thenReturn(profile);

        
        Profile savedProfile = profileService.save(profile);

    
        assertThat(savedProfile.getEmail(), is("john@example.com"));
        verify(profileRepository, times(1)).save(profile);  
    }

    @Test
    void findByUserId_ShouldReturnProfile_WhenProfileExists() {
        
        Profile profile = new Profile("john@example.com", null);
        when(profileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));

      
        Optional<Profile> profileOptional = profileService.findByUserId(1L);

       
        assertThat(profileOptional.isPresent(), is(true));
        assertThat(profileOptional.get().getEmail(), is("john@example.com"));
        verify(profileRepository, times(1)).findByUserId(1L);  // Verifica que se llamó a findByUserId
    }

    @Test
    void findByUserId_ShouldReturnEmpty_WhenProfileDoesNotExist() {
       
        when(profileRepository.findByUserId(1L)).thenReturn(Optional.empty());

    
        Optional<Profile> profileOptional = profileService.findByUserId(1L);

       
        assertThat(profileOptional.isPresent(), is(false));
        verify(profileRepository, times(1)).findByUserId(1L);
    }

    @Test
    void getEmailByUserId_ShouldReturnEmail_WhenProfileExists() {
      
        Profile profile = new Profile("john@example.com", null);
        when(profileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));

  
        String email = profileService.getEmailByUserId(1L);

       
        assertThat(email, is("john@example.com"));
        verify(profileRepository, times(1)).findByUserId(1L);  // Verifica que se llamó a findByUserId
    }

    @Test
    void getEmailByUserId_ShouldReturnNull_WhenProfileDoesNotExist() {
     
        when(profileRepository.findByUserId(1L)).thenReturn(Optional.empty());

       
        String email = profileService.getEmailByUserId(1L);

        
        assertThat(email, is((String) null));
        verify(profileRepository, times(1)).findByUserId(1L);  // Verifica que se llamó a findByUserId
    }
}
