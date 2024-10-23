package spring.interfaces;

import java.util.List;
import java.util.Optional;

public interface GeneralInterface<T> {
    
    T creer(T entity);

    T modifier(T entity);

    void supprimer(Long id);

    Optional<T> trouverParId(Long id);

    List<T> trouverTous();

}
