package gruppe10.inflowscreen.frontend.config;


import gruppe10.inflowscreen.frontend.models.entities.Account;
import gruppe10.inflowscreen.frontend.models.entities.Authority;
import gruppe10.inflowscreen.frontend.models.entities.Organisation;
import gruppe10.inflowscreen.frontend.repositories.AccountRepository;
import gruppe10.inflowscreen.frontend.repositories.AuthorityRepository;
import gruppe10.inflowscreen.frontend.repositories.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class InitOrganisations implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        loadSecurityData();
    }

    private final OrganisationRepository organisationRepository;

    private void loadSecurityData() {

        // TODO indsæt organisationen med brugeren tilknyttet
        //Organisation organisation = new Organisation("Fysioterapi i Centrum", "Hej");
        //organisationRepository.save(organisation);


    }
}










