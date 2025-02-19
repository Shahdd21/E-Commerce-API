package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.enums.UserRole;
import com.project.e_commerce_api.enums.UserRoleConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;

public class UserDTO {

    private Integer user_id;

    private String username;

    private String password;

    private String role;

    private boolean approved;

    public UserDTO(User user){
        this.user_id = user.getUser_id();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().name();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", approved=" + approved +
                '}';
    }
}
