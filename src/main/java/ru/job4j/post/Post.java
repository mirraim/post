package ru.job4j.post;

import java.util.*;
import java.util.stream.Collectors;

public class Post {

    public static void main(String[] args) {
        Map<String, Set<String>> users = new HashMap<>();
        users.put("user1", new HashSet<>(Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        users.put("user2", new HashSet<>(Set.of("foo@gmail.com", "ups@pisem.net")));
        users.put("user3", new HashSet<>(Set.of("xyz@pisem.net", "vasya@pupkin.com")));
        users.put("user4", new HashSet<>(Set.of("ups@pisem.net", "aaa@bbb.ru")));
        users.put("user5", new HashSet<>(Set.of("xyz@pisem.net")));
        Post post = new Post();
        Map<String, Set<String>> emails = post.mergeUsers(users);
        emails.forEach((user, emailsList) -> System.out.println(user + "->" + emailsList));
    }

    /**
     * Метод обьединяет пользователей, если у них есть одинаковый email
     * Если порядок имен пользователей не учитывается,
     * итоговое имя пользователя может получиться любым из объединенных
     * @param persons принимает перечень пользователей
     * @return объединенный перечень пользователей
     */
    public Map<String, Set<String>> mergeUsers(Map<String, Set<String>> persons) {
        Set<User> users = createUsers(persons);
        for (User user : users) {
            User origin = user.getOrigin();
            while (origin != null && origin.getOrigin() != null) {
                origin = origin.getOrigin();
            }
            if (origin != null) {
                origin.add(user.getEmails());
            }
        }
        return prepareUser(users);
    }

    /**
     * Метод создает список пользователей
     * В процессе создания пользователя проверяется, есть ли для этого пользователя дубликат
     * @param persons принимает перечень пользователей
     * @return множество (Set) объектов User
     */
    public Set<User> createUsers(Map<String, Set<String>> persons) {
        Map<String, User> emails = new HashMap<>();
        Set<User> users = new HashSet<>();
        for (String person : persons.keySet()) {
            User user = new User(person, persons.get(person));
            users.add(user);
            for (String email : persons.get(person)) {
                if (emails.containsKey(email) && user.getOrigin() == null) {
                    user.setOrigin(emails.get(email));
                }
                emails.put(email, user);
            }
        }
        return users;
    }

     /**
     * Метод преобразует множество (Set) пользователей в список(Map) имен пользователей
     * с указанием принадлежащих им емэйлов
     * Причем в конечный список попадают только те пользователи, которые не являются дубликатом
     * @param users - множество пользователей(User)
     * @return список имен пользователей с указанием их емэйлов
     */
    public Map<String, Set<String>> prepareUser(Set<User> users) {
        return users.stream()
                .filter(user -> user.getOrigin() == null)
                .collect(Collectors.toMap(
                        User::getName,
                        User::getEmails
                ));
    }
}
