package dev.gisela.paddle_tennis_couch_backend.services;

import dev.gisela.paddle_tennis_couch_backend.exceptions.RoleNotFoundException;
import dev.gisela.paddle_tennis_couch_backend.models.Role;
import dev.gisela.paddle_tennis_couch_backend.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_ShouldReturnRole_WhenRoleExists() {
      
        Role mockRole = new Role("ADMIN");
        mockRole.setId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(mockRole));

      
        Role foundRole = roleService.getById(1L);

      
        assertThat(foundRole.getName(), is(equalTo("ADMIN")));
        assertThat(foundRole.getId(), is(1L));
        verify(roleRepository, times(1)).findById(1L); 
    }

    @Test
    void getById_ShouldThrowException_WhenRoleDoesNotExist() {
    
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        
        assertThrows(RoleNotFoundException.class, () -> roleService.getById(1L));
        verify(roleRepository, times(1)).findById(1L);  
    }

    @Test
    void getRoleByName_ShouldReturnRole_WhenRoleExists() {
   
        Role mockRole = new Role("USER");
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(mockRole));

     
        Role foundRole = roleService.getRoleByName("USER");

      
        assertThat(foundRole.getName(), is(equalTo("USER")));
        verify(roleRepository, times(1)).findByName("USER");
    }

    @Test
    void getRoleByName_ShouldThrowException_WhenRoleDoesNotExist() {
      
        when(roleRepository.findByName("GUEST")).thenReturn(Optional.empty());

    
        assertThrows(RoleNotFoundException.class, () -> roleService.getRoleByName("GUEST"));
        verify(roleRepository, times(1)).findByName("GUEST");
    }
}
