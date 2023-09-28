package br.com.andrergomes.foodmanageralpha.service;

import br.com.andrergomes.foodmanageralpha.model.User;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    User findById(UUID userId);

    User create(User user);

    void update(UUID userId, User user);

    void patch(UUID userId, Map<String, Object> userMap) throws JsonMappingException;

    void delete(UUID userId);
}
