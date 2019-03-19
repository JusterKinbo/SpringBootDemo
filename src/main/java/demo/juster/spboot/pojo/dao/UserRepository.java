package demo.juster.spboot.pojo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.juster.spboot.pojo.user.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Transactional(propagation=Propagation.REQUIRED) 
	User findByName(String name);
	
}
