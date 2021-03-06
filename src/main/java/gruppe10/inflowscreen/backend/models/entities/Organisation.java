package gruppe10.inflowscreen.backend.models.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="organisations")
public class Organisation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    private String logoPath;
    
    @Singular
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "organisations_slides",
            joinColumns = {@JoinColumn(name = "ID_ORGANISATION", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ID_SLIDE", referencedColumnName = "ID")})
    private Set<Slide> slides;

    @OneToMany(mappedBy = "organisation")
    private Set<Account> accounts;
    
    
    public Organisation(String name, String logoPath)
    {
        this.name = name;
        this.logoPath = logoPath;
        
    }
    
    
    
    @Override
    public String toString() {
        return "Organisation{" +
                "id=" + id +
                '}';
    }
}
