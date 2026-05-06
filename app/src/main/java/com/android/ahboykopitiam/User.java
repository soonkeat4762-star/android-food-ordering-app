package com.android.ahboykopitiam;

public class User {

    private final String fullName;
    private final String email;
    private final String phone;

    public User(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() {

        return fullName;
    }

    public String getEmail() {

        return email;
    }

    public String getPhone() {

        return phone;
    }
}
