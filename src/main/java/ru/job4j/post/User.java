package ru.job4j.post;

import java.util.Objects;
import java.util.Set;

public class User {
    private String name;
    private Set<String> emails;
    private User origin;

    public User(String name, Set<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void add(Set<String> list) {
        emails.addAll(list);
    }
    public Set<String> getEmails() {
        return emails;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", origin=").append(origin);
        sb.append('}');
        return sb.toString();
    }
}
