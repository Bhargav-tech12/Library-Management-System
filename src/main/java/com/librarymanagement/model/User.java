package com.librarymanagement.model;

import java.util.Objects;

public class User {
    private String userId;
    private String username;
    private String password;
    private Role role;
    private String linkedEntityId;

    public User() {
    }

    public User(String userId, String username, String password, Role role, String linkedEntityId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.linkedEntityId = linkedEntityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLinkedEntityId() {
        return linkedEntityId;
    }

    public void setLinkedEntityId(String linkedEntityId) {
        this.linkedEntityId = linkedEntityId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User) object;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{"
                + "userId='" + userId + '\''
                + ", username='" + username + '\''
                + ", role=" + role
                + ", linkedEntityId='" + linkedEntityId + '\''
                + '}';
    }
}
