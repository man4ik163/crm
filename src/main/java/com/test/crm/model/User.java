package com.test.crm.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@Entity
@Table(name = "crm_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "not valid name")
    @NotNull(message = "not valid name")
    @Column(name = "name", unique = true)
    private String name;

    @NotEmpty(message = "not valid password")
    @NotNull(message = "not valid password")
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


}
