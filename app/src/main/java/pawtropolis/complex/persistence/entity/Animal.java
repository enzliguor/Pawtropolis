package pawtropolis.complex.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "specie",
        discriminatorType = DiscriminatorType.STRING
)
@Table(name = "animal")
public abstract class Animal implements EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "favorite_food")
    private String favoriteFood;
    @Column(name = "age")
    private int age;
    @Column(name = "join_date")
    private LocalDate joinDate;
    @Column(name = "weight")
    private double weight;
    @Column(name = "height")
    private double height;
}
