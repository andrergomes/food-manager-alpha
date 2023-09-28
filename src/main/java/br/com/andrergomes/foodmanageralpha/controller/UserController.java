package br.com.andrergomes.foodmanageralpha.controller;

import br.com.andrergomes.foodmanageralpha.model.User;
import br.com.andrergomes.foodmanageralpha.service.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(("/v1/users"))
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {

        List<User> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable(name = "userId") UUID userId) {

        User user = userService.findById(userId);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {

        User savedUser = userService.create(user);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(savedUser.getId()).toUri()).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity update(@PathVariable(name = "userId") UUID userId, @RequestBody User user) {

        userService.update(userId, user);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity patch(@PathVariable(name = "userId") UUID userId, @RequestBody Map<String, Object> userMap) throws JsonMappingException {

        userService.patch(userId, userMap);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@PathVariable(name = "userId") UUID userId) {

        userService.delete(userId);

        return ResponseEntity.noContent().build();
    }
}
