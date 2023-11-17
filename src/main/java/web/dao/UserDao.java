package web.dao;

import web.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAllUser();

    void saveUser(User user);

    User getUserById(int id);

    void deleteUser(int id);
}