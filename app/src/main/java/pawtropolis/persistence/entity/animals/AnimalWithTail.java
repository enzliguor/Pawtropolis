package pawtropolis.persistence.entity.animals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SuperBuilder
public abstract class AnimalWithTail extends Animal {
    @Column(name = "tail_length")
    private double tailLength;
}
