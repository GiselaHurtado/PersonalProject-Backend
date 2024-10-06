package dev.gisela.paddle_tennis_couch_backend.models;

import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "role_id")
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public Role(String name) {
        this.name = name;
    }


    public Role() {
    }
}
