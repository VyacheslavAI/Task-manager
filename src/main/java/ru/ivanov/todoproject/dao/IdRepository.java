package ru.ivanov.todoproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class IdRepository {

    static final List<String> listId = new ArrayList<>();

    static String generateId() {
        String id = UUID.randomUUID().toString();
        while (listId.contains(id)) {
            id = UUID.randomUUID().toString();
        }
        listId.add(id);
        return id;
    }
}
