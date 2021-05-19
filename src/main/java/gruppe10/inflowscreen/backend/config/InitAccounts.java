/*package gruppe10.inflowscreen.backend.config;


import gruppe10.inflowscreen.backend.models.entities.Account;
import gruppe10.inflowscreen.backend.models.entities.Authority;
import gruppe10.inflowscreen.backend.repositories.AccountRepository;
import gruppe10.inflowscreen.backend.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class InitAccounts implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        loadSecurityData();
    }


    private final AuthorityRepository authorityRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    private void loadSecurityData() {

        Authority admin = authorityRepository.save(Authority.builder().authorityType("ADMIN").build());
        Authority user = authorityRepository.save(Authority.builder().authorityType("USER").build());


        accountRepository.save(Account.builder()
                .email("gruppe1010@hotmail.com")
                .password(passwordEncoder.encode("hejhej"))
                .authority(admin)
                .build());

        accountRepository.save(Account.builder()
                .email("noget")
                .password(passwordEncoder.encode("hejhej"))
                .authority(user)
                .build());


        accountRepository.save(Account.builder()
                .email("noget@hotmail.com")
                .password(passwordEncoder.encode("hejhej"))
                .authority(user)
                .build());
    }
}

 */










