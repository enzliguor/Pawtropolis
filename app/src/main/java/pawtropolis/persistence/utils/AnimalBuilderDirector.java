package pawtropolis.persistence.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pawtropolis.complex.game.domain.animals.domain.*;
import pawtropolis.complex.persistence.entity.*;
import pawtropolis.game.domain.animals.domain.*;
import pawtropolis.persistence.entity.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimalBuilderDirector {

    public static<A extends AnimalBO, B extends AnimalBO.AnimalBOBuilder<A, B>> A buildAnimalBO(AnimalBO.AnimalBOBuilder<A, B> animalBOBuilder, Animal animal){
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

    public static<A extends  Animal, B extends Animal.AnimalBuilder<A, B>> A buildAnimal(Animal.AnimalBuilder<A, B> animalBuilder, AnimalBO animalBO){
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

    public static<A extends AnimalWithWingsBO, B extends AnimalWithWingsBO.AnimalWithWingsBOBuilder<A, B>> A buildAnimalWithWingsBO(AnimalWithWingsBO.AnimalWithWingsBOBuilder<A, B> animalWithWingsBOBuilder, AnimalWithWings animalWithWings){
                return  buildAnimalBO(
                        animalWithWingsBOBuilder.wingspan(animalWithWings.getWingspan()),
                        animalWithWings);
    }

    public static<A extends AnimalWithWings, B extends AnimalWithWings.AnimalWithWingsBuilder<A, B>> A buildAnimalWithWings(AnimalWithWings.AnimalWithWingsBuilder<A, B> animalWithWingsBuilder, AnimalWithWingsBO animalWithWingsBO){
        return  buildAnimal(
                animalWithWingsBuilder.wingspan(animalWithWingsBO.getWingspan()),
                animalWithWingsBO);
    }

    public static<A extends AnimalWithTailBO, B extends AnimalWithTailBO.AnimalWithTailBOBuilder<A, B>>  A buildAnimalWithTailBO(AnimalWithTailBO.AnimalWithTailBOBuilder<A, B> animalWithTailBOBuilder, AnimalWithTail animalWithTail){
        return  buildAnimalBO(
                animalWithTailBOBuilder.tailLength(animalWithTail.getTailLength()),
                animalWithTail);
    }

    public static<A extends AnimalWithTail, B extends AnimalWithTail.AnimalWithTailBuilder<A, B>> A buildAnimalWithTail(AnimalWithTail.AnimalWithTailBuilder<A, B> animalWithTailBuilder, AnimalWithTailBO animalWithTailBO){
        return  buildAnimal(
                animalWithTailBuilder.tailLength(animalWithTailBO.getTailLength()),
                animalWithTailBO);
    }

    public static Eagle build(EagleBO eagleBO){
        return buildAnimalWithWings(Eagle.builder(), eagleBO);
    }

    public static EagleBO build(Eagle eagle){
        return buildAnimalWithWingsBO(EagleBO.builder(), eagle);
    }

    public static Tiger build(TigerBO tigerBO){
        return buildAnimalWithTail(Tiger.builder(), tigerBO);
    }

    public static TigerBO build(Tiger tiger){
        return buildAnimalWithTailBO(TigerBO.builder(), tiger);
    }

    public static Lion build(LionBO lionBO){
        return buildAnimalWithTail(Lion.builder(), lionBO);
    }

    public static LionBO build(Lion lion){
        return buildAnimalWithTailBO(LionBO.builder(), lion);
    }


}
