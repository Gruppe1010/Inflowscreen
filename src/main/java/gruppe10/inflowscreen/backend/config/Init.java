package gruppe10.inflowscreen.backend.config;


import gruppe10.inflowscreen.backend.models.entities.Account;
import gruppe10.inflowscreen.backend.models.entities.Authority;
import gruppe10.inflowscreen.backend.models.entities.Organisation;
import gruppe10.inflowscreen.backend.repositories.AccountRepository;
import gruppe10.inflowscreen.backend.repositories.AuthorityRepository;
import gruppe10.inflowscreen.backend.repositories.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@RequiredArgsConstructor
@Component
public class Init implements CommandLineRunner {
    
    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
    
    
    private final AuthorityRepository authorityRepository;
    private final AccountRepository accountRepository;
    private final OrganisationRepository organisationRepository;
    private final PasswordEncoder passwordEncoder;
   
    
    private void loadData() {
        
        // hent de to første brugere
        Optional<List<Account>> optionalAccounts = Optional.of(accountRepository.findAll());
        
        boolean thereAreMoreThanTwoAccounts = false;
        
        if(optionalAccounts.isPresent()){
            
            // >= 1 == mindst 2 brugere på listen
            if(optionalAccounts.get().size() >= 1){
                thereAreMoreThanTwoAccounts = true;
            }
        }
        
        if(!thereAreMoreThanTwoAccounts){
            // opretter authority-typer
            Authority admin = authorityRepository.save(Authority.builder().authorityType("ADMIN").build());
            Authority user = authorityRepository.save(Authority.builder().authorityType("USER").build());
            
        
            // opretter organisationer
            Organisation fysICentrumOrg = new Organisation("Fysioterapi i Centrum",
                    "/images/slides/corgi.JPG");
        
            Organisation gruppe10Org = new Organisation("Gruppe 10",
                    "/images/logos/gruppe10logo.png");
            
            // tilføjer organisationer
            organisationRepository.save(fysICentrumOrg);
            organisationRepository.save(gruppe10Org);
        
            // opretter brugere
            Account gruppe10Account = Account.builder()
                                              .email("gruppe1010@hotmail.com")
                                              .password(passwordEncoder.encode("hejhej"))
                                              .authority(admin)
                                              .organisation(gruppe10Org)
                                              .build();
        
            Account fysICentrumAccount = Account.builder()
                                                .email("mail@fysicentrum.dk")
                                                .password(passwordEncoder.encode("g9XZQmUM"))
                                                .authority(user)
                                                .organisation(fysICentrumOrg)
                                                .build();
         
            
            // tilføjer accounts
            accountRepository.save(fysICentrumAccount);
            accountRepository.save(gruppe10Account);
        }
    }
    
    
    
    
    
    
    

    
    private void loadSecurityData() {
    
    
    }
}










