package service;

import java.util.List;

public interface DAO<T> {

    void create(T t);

    T getById(int id);

    void update(T t);

    boolean delete(T t);

    List<T> getAll(Class<T> type);
}
