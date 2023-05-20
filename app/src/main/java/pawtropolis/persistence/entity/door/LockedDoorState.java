package pawtropolis.persistence.entity.door;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pawtropolis.persistence.entity.Item;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue(value = "locked")
public class LockedDoorState extends DoorState{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "item_key")
    private Item itemKey;
}
