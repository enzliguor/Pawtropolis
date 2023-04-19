package pawtropolis.complex.marshaller;

public interface Marshaller<T, E> {

    T marshall(E e);

    E unmarshall(T t);

    Class<T> getEntityClass();

    Class<E> getBoClass();
}
