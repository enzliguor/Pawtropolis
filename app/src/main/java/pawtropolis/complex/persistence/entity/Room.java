package pawtropolis.complex.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import pawtropolis.complex.game.map.util.CardinalPoint;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"adjacentRooms", "adjacentRoomsOf"})
@ToString(exclude = {"adjacentRooms", "adjacentRoomsOf"})
@Entity
@Table(name = "room")
@Builder
public class Room {
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    private List<Animal> animals;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "linked_rooms",
            joinColumns = {
                    @JoinColumn(name = "id_room", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_adjacent_room", referencedColumnName = "id")
            })
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyColumn(name = "cardinal_point")
    private EnumMap<CardinalPoint, Room> adjacentRooms;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "linked_rooms",
            joinColumns = {
                    @JoinColumn(name = "id_adjacent_room", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_room", referencedColumnName = "id")
            })
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyColumn(name = "cardinal_point")
    private EnumMap<CardinalPoint, Room> adjacentRoomsOf;
}
