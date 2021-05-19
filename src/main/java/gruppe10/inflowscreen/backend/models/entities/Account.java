package gruppe10.inflowscreen.backend.models.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="accounts")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // @ValidEmail
    // https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
    @Column(unique = true)
    private String email;
    private String password;
    
    
    @ManyToOne
    @JoinColumn(name = "id_organisation")
   private Organisation organisation;
    
    
    @Singular
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "accounts_authorities",
            joinColumns = {@JoinColumn(name = "ID_ACCOUNT", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ID_AUTHORITY", referencedColumnName = "ID")})
    private Set<Authority> authorities;
    
    
    // TODO research om de er nødvendige
    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Builder.Default
    private boolean enabled = true;
    
    public Organisation getOrganisation() {
        return organisation;
    }
    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                       "id=" + id +
                       ", email='" + email + '\'' +
                       ", password='" + password + '\'' +
                       ", authorities=" + authorities + '\'' +
                       ", organisation=" + organisation + '\'' +
                       '}';
    }
}
