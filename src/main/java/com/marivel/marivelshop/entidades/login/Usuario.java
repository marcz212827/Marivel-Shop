package com.marivel.marivelshop.entidades.login;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marivel.marivelshop.entidades.Cliente;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String username;

    @Column(nullable = false)
    @Setter(value = AccessLevel.NONE)
    private String password;

    @EqualsAndHashCode.Include
    @ToString.Include
    private boolean enabled;

    @Column(name = "rol", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Set<Rol> roles;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Cliente cliente;

    /** Encripta el password. */
    public void setPassword(String rawPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        this.password = bCryptPasswordEncoder.encode(rawPassword);
    }

    public void setRol(Rol rol) {
        setRoles(Set.of(rol));
    }
}
