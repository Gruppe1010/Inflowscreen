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
@Table(name="authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String authorityType;

    @ManyToMany(mappedBy = "authorities")
    private Set<Account> accounts;

}
