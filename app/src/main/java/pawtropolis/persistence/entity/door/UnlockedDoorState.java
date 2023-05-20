package pawtropolis.persistence.entity.door;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue(value = "unlocked")
public class UnlockedDoorState extends DoorState{


}
