package ru.ivanov.todoproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.repository.IUserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(userName);
        if (user == null) throw new UsernameNotFoundException("User not found");
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = null;
        userBuilder = org.springframework.security.core.userdetails.User.withUsername(userName);
//        userBuilder.disabled()
        userBuilder.password(user.getPasswordHash());


        return null;
    }
}
