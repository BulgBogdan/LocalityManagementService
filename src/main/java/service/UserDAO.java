package service;

import entity.User;

public interface UserDAO extends DAO<User> {

    User getByUsername(String username);
}
