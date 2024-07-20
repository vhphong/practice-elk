package com.pnk.practice_elk;

import com.pnk.practice_elk.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
@RestController
public class PracticeElkApplication {

    Logger logger = LoggerFactory.getLogger(PracticeElkApplication.class);


    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) {
        List<User> users = getUsers();
        User retrievedUser = users
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);

        if (retrievedUser != null) {
            logger.info("User is found as : {}", retrievedUser);
            return retrievedUser;
        }

        try {
            throw new NullPointerException();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("User with ID: {} is not found", id);
        }

        return new User();
    }


    private List<User> getUsers() {
        return Stream.of(
                new User(1, "Liam"),
                new User(2, "Pete"),
                new User(3, "Emma")
        ).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        SpringApplication.run(PracticeElkApplication.class, args);
    }

}
