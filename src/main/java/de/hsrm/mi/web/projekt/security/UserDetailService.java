package de.hsrm.mi.web.projekt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired private FotoUserRepository fotorepo;
    @Autowired PasswordEncoder pwencoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        FotoUser user = fotorepo.findByUsername(username);

        if(user == null){ throw new UsernameNotFoundException(username); }
         
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(pwencoder.encode(user.getPassword()))
                .roles(user.getRole())
                .build();
    }
    
}
