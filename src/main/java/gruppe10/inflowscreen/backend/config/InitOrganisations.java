package gruppe10.inflowscreen.backend.config;


import gruppe10.inflowscreen.backend.models.entities.Account;
import gruppe10.inflowscreen.backend.repositories.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
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










