package ru.ivanov.todoproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.todoproject.entity.Authority;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(userName);
        if (user == null) throw new UsernameNotFoundException("User not found");
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = null;
        userBuilder = org.springframework.security.core.userdetails.User.withUsername(userName);
        userBuilder.disabled(!user.isEnabled());
        userBuilder.password(user.getPasswordHash());
        final List<String> authorities = new ArrayList<>();
        for (final Authority authority : user.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        userBuilder.authorities(authorities.toArray(new String[authorities.size()]));
        return userBuilder.build();
    }
}
