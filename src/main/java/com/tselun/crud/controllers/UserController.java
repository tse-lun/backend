package com.tselun.crud.controllers;

import com.tselun.crud.mappers.UserMapper;
import com.tselun.crud.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserMapper mapper;

    @GetMapping
    public List<User> all() {
        return mapper.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        var u = mapper.get(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User u) {
        mapper.post(u);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody User u) {
        var existing = mapper.get(id);
        if (existing == null) return ResponseEntity.notFound().build();
        u.setId(id);
        mapper.update(u);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id, @RequestBody User u) {
        var existing = mapper.get(id);
        if (existing == null) return ResponseEntity.notFound().build();
        if (u.getName() != null) existing.setName(u.getName());
        if (u.getEmail() != null) existing.setEmail(u.getEmail());
        mapper.update(existing);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var existing = mapper.get(id);
        if (existing == null) return ResponseEntity.notFound().build();
        mapper.delete(id);
        return ResponseEntity.noContent().build();
    }
}
