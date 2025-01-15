package tech.nocountry.roadbites.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
        this.displayName = firstName + " " + lastName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.created = LocalDateTime.now();
        this.status = Status.INACTIVE;
        this.role = Role.CUSTOMER;
    }

    public User() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public Status getStatus() {
        return this.status;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Role getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$displayName = this.getDisplayName();
        final Object other$displayName = other.getDisplayName();
        if (this$displayName == null ? other$displayName != null : !this$displayName.equals(other$displayName))
            return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$passwordHash = this.getPasswordHash();
        final Object other$passwordHash = other.getPasswordHash();
        if (this$passwordHash == null ? other$passwordHash != null : !this$passwordHash.equals(other$passwordHash))
            return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$created = this.getCreated();
        final Object other$created = other.getCreated();
        if (this$created == null ? other$created != null : !this$created.equals(other$created)) return false;
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !this$lastUpdated.equals(other$lastUpdated))
            return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $displayName = this.getDisplayName();
        result = result * PRIME + ($displayName == null ? 43 : $displayName.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $passwordHash = this.getPasswordHash();
        result = result * PRIME + ($passwordHash == null ? 43 : $passwordHash.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $created = this.getCreated();
        result = result * PRIME + ($created == null ? 43 : $created.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * PRIME + ($lastUpdated == null ? 43 : $lastUpdated.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        return result;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", username=" + this.getUsername() + ", displayName=" + this.getDisplayName() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", email=" + this.getEmail() + ", phone=" + this.getPhone() + ", passwordHash=" + this.getPasswordHash() + ", status=" + this.getStatus() + ", created=" + this.getCreated() + ", lastUpdated=" + this.getLastUpdated() + ", role=" + this.getRole() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public enum Status {
        ACTIVE, INACTIVE;
    }

    public enum Role {
        CUSTOMER, ADMIN, DEVELOPER;
    }
}
