package pawtropolis.complex.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pawtropolis.complex.game.animals.domain.*;
import pawtropolis.complex.persistence.entity.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimalBuilderDirector {

    public static<A extends  AnimalBO, B extends AnimalBO.AnimalBOBuilder<A, B>> A builderAnimalBO(AnimalBO.AnimalBOBuilder<A, B> animalBOBuilder, Animal animal){
        return animalBOBuilder
                .id(animal.getId())
                .favoriteFood(animal.getFavoriteFood())
                .age(animal.getAge())
                .weight(animal.getWeight())
                .joinDate(animal.getJoinDate())
                .height(animal.getHeight())
                .name(animal.getName())
                .build();
    }

    public static<A extends  Animal, B extends Animal.AnimalBuilder<A, B>> A builderAnimal(Animal.AnimalBuilder<A, B> animalBuilder, AnimalBO animalBO){
        return animalBuilder
                .id(animalBO.getId())
                .favoriteFood(animalBO.getFavoriteFood())
                .age(animalBO.getAge())
                .weight(animalBO.getWeight())
                .joinDate(animalBO.getJoinDate())
                .height(animalBO.getHeight())
                .name(animalBO.getName())
                .build();
    }

    public static<A extends AnimalWithWingsBO, B extends AnimalWithWingsBO.AnimalWithWingsBOBuilder<A, B>> A builderAnimalWithWingsBO(AnimalWithWingsBO.AnimalWithWingsBOBuilder<A, B> animalWithWingsBOBuilder, AnimalWithWings animalWithWings){
                return  builderAnimalBO(
                        animalWithWingsBOBuilder.wingspan(animalWithWings.getWingspan()),
                        animalWithWings);
    }

    public static<A extends AnimalWithWings, B extends AnimalWithWings.AnimalWithWingsBuilder<A, B>> A builderAnimalWithWings(AnimalWithWings.AnimalWithWingsBuilder<A, B> animalWithWingsBuilder, AnimalWithWingsBO animalWithWingsBO){
        return  builderAnimal(
                animalWithWingsBuilder.wingspan(animalWithWingsBO.getWingspan()),
                animalWithWingsBO);
    }

    public static<A extends AnimalWithTailBO, B extends AnimalWithTailBO.AnimalWithTailBOBuilder<A, B>>  A builderAnimalWithTailBO(AnimalWithTailBO.AnimalWithTailBOBuilder<A, B> animalWithTailBOBuilder, AnimalWithTail animalWithTail){
        return  builderAnimalBO(
                animalWithTailBOBuilder.tailLength(animalWithTail.getTailLength()),
                animalWithTail);
    }

    public static<A extends AnimalWithTail, B extends AnimalWithTail.AnimalWithTailBuilder<A, B>> A builderAnimalWithTail(AnimalWithTail.AnimalWithTailBuilder<A, B> animalWithTailBuilder, AnimalWithTailBO animalWithTailBO){
        return  builderAnimal(
                animalWithTailBuilder.tailLength(animalWithTailBO.getTailLength()),
                animalWithTailBO);
    }

    public static Eagle buildEagle(EagleBO eagleBO){
        return builderAnimalWithWings(Eagle.builder(), eagleBO);
    }

    public static EagleBO buildEagleBO(Eagle eagle){
        return builderAnimalWithWingsBO(EagleBO.builder(), eagle);
    }

    public static Tiger buildTiger(TigerBO tigerBO){
        return builderAnimalWithTail(Tiger.builder(), tigerBO);
    }

    public static TigerBO buildTigerBO(Tiger tiger){
        return builderAnimalWithTailBO(TigerBO.builder(), tiger);
    }

    public static Lion buildLion(LionBO lionBO){
        return builderAnimalWithTail(Lion.builder(), lionBO);
    }

    public static LionBO buildLionBO(Lion lion){
        return builderAnimalWithTailBO(LionBO.builder(), lion);
    }


}
