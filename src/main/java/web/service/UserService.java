package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUser(User user);

    void updateUser(int id, User user);

    List<User> getAllUsers();

    User getUserById(int id);
}
