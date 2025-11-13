package com.hoteljulia.core.dto.auth;

import java.util.regex.Pattern;


public class SignupForm {

    private Pattern namePattern = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ'\\-. ]+$");
    private Pattern emailPattern = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private Pattern phonePattern = Pattern.compile("^\\+?(\\d{1,3})?[-. (]?(\\d{3})[-. )]?(\\d{3})[-. ]?(\\d{4})$");

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;
    private String address;


    public boolean validate() {
        // Name validation
        if (name == null || name.isBlank() || name.length() > 50 || !namePattern.matcher(name).find())
            return false;

        // Email validation
        if (email == null || email.isBlank() || email.length() > 254 || !emailPattern.matcher(email).find())
            return false;

        // Password validation
        if (password == null || password.length() < 8 || password.length() > 64)
            return false;

        boolean hasLower = false, hasUpper = false, hasDigit = false;

        for (char c : password.toCharArray()) {

            if (!hasLower && Character.isLowerCase(c)) hasLower = true;
            else if (!hasUpper && Character.isUpperCase(c)) hasUpper = true;
            else if (!hasDigit && Character.isDigit(c)) hasDigit = true;

            if (hasDigit && hasUpper && hasLower) break;
        }

        if (!(hasLower && hasUpper && hasDigit))
            return false;

        // Confirm password validation
        if (confirmPassword == null || !confirmPassword.equals(password))
            return false;

        // Phone validation final return statement
        return phone != null && !phone.isBlank() && phonePattern.matcher(phone).find();
    }


    // === GETTERS AND SETTERS ===

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}