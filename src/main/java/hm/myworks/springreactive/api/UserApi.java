package hm.myworks.springreactive.api;

import hm.myworks.springreactive.domain.User;
import hm.myworks.springreactive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserApi {

    private UserRepository userRepository;

    @GetMapping("/users")
    public Flux<User> getUsers() {
        return Flux.fromIterable(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public Mono<User> getUser(@PathVariable Long id) {
        return Mono.justOrEmpty(userRepository.findById(id));
    }

    @PostMapping("/users")
    public Mono<User> saveUser(@RequestBody User user) {
        return Mono.justOrEmpty(userRepository.save(user));
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
