package gruppe10.inflowscreen.frontend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Organisation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /*
    @OneToMany(mappedBy = "organisation")
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "filmId")
    private Set<Account> accounts;

     */


}
