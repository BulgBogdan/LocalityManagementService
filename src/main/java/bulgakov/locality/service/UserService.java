package bulgakov.locality.service;

import bulgakov.locality.entity.User;

import java.util.List;

public interface UserService {

    boolean create(User user);

    User getById(int id);

    void update(User user);

    void delete(int id);

    List<User> getAll();

    User getByUsername(String username);

}
