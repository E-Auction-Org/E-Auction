package edu.eci.eauction.security;

import edu.eci.eauction.repositories.UserRepository;
import edu.eci.eauction.service.model.User;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOneByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("El Nombre de usuario " + userName + " no existe"));
        return new UserDetailsImpl(user);
    }
}
