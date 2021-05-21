package ru.job4j.post;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PostTest {

    @Test
    public void addEmail() {
        Map<String, Set<String>> users = new HashMap<>();
        users.put("user3", new HashSet<>(Set.of("xyz@pisem.net", "vasya@pupkin.com")));
        users.put("user5", new HashSet<>(Set.of("xyz@pisem.net")));
        Post post = new Post();
        Map<String, Set<String>> emails = post.mergeUsers(users);
        assertEquals(emails.size(), 1);
    }

    @Test
    public void whenAddEmail() {
        Map<String, Set<String>> users = new LinkedHashMap<>();
        users.put("user3", new HashSet<>(Set.of("xyz@pisem.net", "vasya@pupkin.com")));
        users.put("user5", new HashSet<>(Set.of("xyz@pisem.net")));
        Post post = new Post();
        Map<String, Set<String>> emails = post.mergeUsers(users);
        assertEquals(emails.get("user3"), Set.of("xyz@pisem.net", "vasya@pupkin.com"));
    }

    @Test
    public void whenPrepareDiffUsers() {
        Post post = new Post();
        User user1 = new User("user1", new HashSet<>());
        User user2 = new User("user3", new HashSet<>());
        Set<User> users = Set.of(user1, user2);
        assertEquals(post.prepareUser(users).size(), 2);
    }

    @Test
    public void whenPrepareCopyUsers() {
        Post post = new Post();
        User user1 = new User("user1", new HashSet<>());
        User user2 = new User("user3", new HashSet<>());
        user2.setOrigin(user1);
        Set<User> users = Set.of(user1, user2);
        assertEquals(post.prepareUser(users).size(), 1);
    }

    @Test
    public void when3UsersIsOne() {
        Map<String, Set<String>> users = new LinkedHashMap<>();
        users.put("user1", new HashSet<>(Set.of("vasya@pupkin.com")));
        users.put("user3", new HashSet<>(Set.of("xyz@pisem.net", "vasya@pupkin.com")));
        users.put("user5", new HashSet<>(Set.of("xyz@pisem.net")));
        Post post = new Post();
        Map<String, Set<String>> emails = post.mergeUsers(users);
        assertEquals(emails.size(), 1);
    }
}