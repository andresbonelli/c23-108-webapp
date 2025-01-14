package tech.nocountry.roadbites.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "user", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String displayName;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String passwordHash;
    private Status status;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;
    private Role role;

    public User(String username, String firstName, String lastName, String email, String phone, String passwordHash) {
        this.username = username;
        this.displayName = firstName+" "+lastName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.created = LocalDateTime.now();
        this.status = Status.INACTIVE;
        this.role = Role.CUSTOMER;
    }

    public enum Status {
        ACTIVE, INACTIVE;
    }

    public enum Role {
        CUSTOMER, ADMIN, DEVELOPER;
    }
}
