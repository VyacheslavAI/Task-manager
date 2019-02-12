package com.slava.repository;

import com.slava.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRepository implements MyRepository {

    @Override
    public User getUser() {
        return new User("Slava", 25);
    }
}
