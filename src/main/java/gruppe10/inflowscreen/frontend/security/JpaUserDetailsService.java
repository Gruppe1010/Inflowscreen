package gruppe10.inflowscreen.frontend.security;


import gruppe10.inflowscreen.frontend.models.entities.Account;
import gruppe10.inflowscreen.frontend.models.entities.Authority;
import gruppe10.inflowscreen.frontend.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.debug("Henter bruger info");
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> {
            return new UsernameNotFoundException("Brugernavn: " + email + " Findes ikke");
        });

        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(),
                account.isEnabled(), account.isAccountNonExpired(), account.isCredentialsNonExpired(), account.isAccountNonLocked(),
        convertToSpringAuthorities(account.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<Authority> authorities) {
        if (authorities != null && authorities.size() > 0) {
            return authorities.stream()
                    .map(Authority::getAuthorityType)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }


}
