package be.jstack.ticketing.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    List<T> findAll();

    Optional<T> findById(String id);

    T add(T object);
}