package br.com.andrergomes.foodmanageralpha.service;

import br.com.andrergomes.foodmanageralpha.model.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private Map<UUID, User> users = new HashMap();

    public UserServiceImpl() {
        UUID uuid = UUID.randomUUID();

        User user = User.builder()
                .id(uuid)
                .name("Andre")
                .username("agomes")
                .build();

        users.put(uuid, user);
    }

    @Override
    public List<User> findAll() {
        return users.entrySet().stream().map(Map.Entry::getValue).toList();
    }

    @Override
    public User findById(UUID userId) {
        return userId != null ? users.get(userId) : null;
    }

    @Override
    public User create(User user) {
        return users.put(UUID.randomUUID(), user);
    }

    @Override
    public void update(UUID userId, User user) {

        User fetchedUser = users.get(userId);

        fetchedUser.setName(user.getName());
        fetchedUser.setUsername(user.getUsername());

        users.put(userId, fetchedUser);
    }

    @Override
    public void patch(UUID userId, Map<String, Object> userMap) throws JsonMappingException {

        User fetchedUser = this.findById(userId);

        mapper.updateValue(fetchedUser, userMap);

        users.put(userId, fetchedUser);
    }

    @Override
    public void delete(UUID userId) {

        users.remove(userId);
    }
}
