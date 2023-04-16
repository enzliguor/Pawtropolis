package pawtropolis.complex.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import pawtropolis.complex.game.map.domain.RoomBO;
import pawtropolis.complex.game.map.util.CardinalPoint;
import pawtropolis.complex.persistence.PersistentObject;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "room")
@Builder
public class Room implements PersistentObject {
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
    private Map<CardinalPoint, Room> adjacentRooms = new EnumMap<>(CardinalPoint.class);

    @Override
    public RoomBO parseToBO() {
        return RoomBO.builder()
                .id(this.id)
                .name(this.name)
                .items(this.items.entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey().parseToBO(),
                                Map.Entry::getValue
                        )))
                .animals(this.animals.stream()
                        .map(Animal::parseToBO)
                        .toList())
                .adjacentRooms(this.adjacentRooms.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().parseToBO()
                        )))
                .build();
    }
}