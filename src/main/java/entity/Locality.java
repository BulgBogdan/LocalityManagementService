package entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "locality")
public class Locality implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locality")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private int population;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "infrastructure_locality",
            joinColumns = {@JoinColumn(name = "id_infrastructure")},
            inverseJoinColumns = {@JoinColumn(name = "id_locality")})
    private List<Infrastructure> infrastructures;

}
