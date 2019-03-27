package demo.juster.spboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import demo.juster.spboot.pojo.user.User;
import demo.juster.spboot.service.interfaces.IUserService;


//@Service
public class SecurityService implements UserDetailsService{

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername=======");
        User user = userService.findUserByName(s);
        if (user == null) {
            throw new  UsernameNotFoundException("");
        }
        return user;
    }
}
