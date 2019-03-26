package demo.juster.spboot.mapper;

import demo.juster.spboot.pojo.user.User;

import java.util.List;

import org.springframework.stereotype.Repository;



@Repository
public interface UserMapper {


    User findById(Long id);
    
    User findByName(String name);
    
    List<User> findAll();
    void deleteById(Long id);
    void delete(User u);
    void save(User u);
    void update(User u);
}
