package demo.juster.spboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import demo.juster.spboot.pojo.dao.UserRepository;
import demo.juster.spboot.pojo.user.Role;
import demo.juster.spboot.pojo.user.User;
import demo.juster.spboot.util.MD5Util;


//@Service
public class SecurityService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//    	User user = new User("juster",MD5Util.encode("123456"));
//    	Role role1 = new Role();
//    	List<Role> roles = new ArrayList<Role>();
//    	role1.setRolename("ROLE_ADMIN");
//        roles.add(role1);
//        user.setRoles(roles);
        System.out.println("loadUserByUsername=======");
        User user = userRepository.findByName(s);
        if (user == null) {
            throw new  UsernameNotFoundException("");
        }
        return user;
    }
}
