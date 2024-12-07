package Exercice3;

import java.util.List;

public interface IService<T> {
    T add(T object);
    List<T> getAll();
    T findByName(String name);
    boolean delete(String name);
    void saveAll();
}
