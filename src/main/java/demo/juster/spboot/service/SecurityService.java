package demo.juster.spboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import demo.juster.spboot.mapper.UserMapper;
import demo.juster.spboot.pojo.user.User;


//@Service
public class SecurityService implements UserDetailsService{

    @Autowired
    private UserMapper userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername=======");
        User user = userRepository.findByName(s);
        if (user == null) {
            throw new  UsernameNotFoundException("");
        }
        return user;
    }
}
