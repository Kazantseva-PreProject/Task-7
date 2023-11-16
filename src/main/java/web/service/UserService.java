package web.service;

import web.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();

    public void saveUser(User user);

    public User getUserById(int id);

    public void deleteUser(int id);
}