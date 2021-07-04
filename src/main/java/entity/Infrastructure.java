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
@Table(name = "infrastructure")
public class Infrastructure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_infrastructure")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "infrastructures")
    private List<Locality> localities;
}
