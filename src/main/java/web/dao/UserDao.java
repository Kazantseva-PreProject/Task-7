package web.dao;

import web.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {
    public List<User>getAllUser();

    public void saveUser(User user);

    public   User getUserById(int id);

    public   void deleteUser(int id);
}