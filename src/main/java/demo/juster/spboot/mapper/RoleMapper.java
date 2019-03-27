package demo.juster.spboot.mapper;

import java.util.List;

import demo.juster.spboot.pojo.user.Role;


public interface RoleMapper {
	
	 Role findById(Long i);
	 List<Role> findAll();
	 void save(Role r);
	 void delete(Role r);
	 void deleteById(Long id);

}
