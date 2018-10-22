package com.accenture.be.business.user;

public class UserRegisterException extends Exception {

    public static final String USERNAME_TOO_SHORT = "Логин должно содержать как миниум 4 символа";
    public static final String USERNAME_TOO_SIMPLE = "Логин должно содержать комбинацию символов и чисел";
    public static final String USERNAME_IS_TAKEN = "Аккаунт с таким логином уже существует";




    public static final String EMAIL_TOO_SHORT = "Имя должно содержать как миниум 4 символа";
    public static final String EMAIL_TOO_SIMPLE = "Имя должно содержать комбинацию символов и чисел";
    public static final String EMAIL_IS_TAKEN = "Аккаунт с таким Email уже существует";

    public static final String PASSWORDS_NOT_MATCH = "Пароли не совпадают";
    public static final String PASSWORD_TOO_SIMPLE = "Пароль должнен содержать комбинацию символов и чисел";

    private String name;

    public UserRegisterException(String message, String name) {
        super(message);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
