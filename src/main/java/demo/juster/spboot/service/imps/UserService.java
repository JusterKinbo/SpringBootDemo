package demo.juster.spboot.service.imps;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.juster.spboot.mapper.RoleMapper;
import demo.juster.spboot.mapper.UserMapper;
import demo.juster.spboot.mapper.UserRoleMapper;
import demo.juster.spboot.pojo.user.Role;
import demo.juster.spboot.pojo.user.User;
import demo.juster.spboot.pojo.user.UserRole;
import demo.juster.spboot.service.interfaces.IUserService;
import demo.juster.spboot.util.MD5Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements IUserService {

	@Autowired
	RoleMapper rmp;
	
	@Autowired
	UserMapper ump;
	
	@Autowired
	UserRoleMapper urmp;

	@Transactional(propagation=Propagation.REQUIRED) 
	public void saveUser(User u) {
		
		String encodePassword = MD5Util.encode(u.getPassword());
		u.setPwd(encodePassword);
		//重新生成user防止修改外部user
		User user = new User(u.getName(),encodePassword);
		user.setRoles(u.getRoles());
		
		ump.save(user);
	
		log.info("user saved" + user.getId());
		List<Role> roles = u.getRoles();
		if(null != roles)
		{
			for(Role r :roles)
			{
				rmp.save(r);
				log.info("role saved" + user.getId());
				UserRole ur = new UserRole();
				ur.setUser_id(user.getId());
				ur.setRole_id(r.getId());
				urmp.save(ur);
				log.info("user role saved" + user.getId());
			}
		}
		
		
		
	}
	
	
}
