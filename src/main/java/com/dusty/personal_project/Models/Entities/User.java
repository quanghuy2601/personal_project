package com.dusty.personal_project.Models.Entities;

import com.dusty.personal_project.Models.Constants.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    @JsonIgnore
    private String password;

    private String fullName;

    private Role role;

    @JsonIgnore
    @CurrentTimestamp
    private Date createdAt;

    @JsonIgnore
    @CurrentTimestamp
    private Date updatedAt;

}
