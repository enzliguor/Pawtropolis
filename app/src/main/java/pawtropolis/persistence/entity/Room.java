package pawtropolis.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import pawtropolis.game.map.util.CardinalPoint;

import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"doors"})
@ToString(exclude = {"doors"})
@Entity
@Table(name = "room")
@Builder
public class Room implements EntityDB{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "items_in_room",
            joinColumns = {@JoinColumn(name = "id_room", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "id_item", referencedColumnName = "id")
    @Column(name = "quantity")
    private Map<Item, Integer> items;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "animals_in_room",
            joinColumns = {@JoinColumn(name = "id_room", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_animal", referencedColumnName = "id")})
    private Set<Animal> animals;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "linked_doors",
            joinColumns = {
                    @JoinColumn(name = "id_room", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_door", referencedColumnName = "id")
            })
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyColumn(name = "cardinal_point", table = "linked_doors")
    private Map<CardinalPoint, Door> doors;
}
