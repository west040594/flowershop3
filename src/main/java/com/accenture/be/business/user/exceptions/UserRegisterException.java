package com.accenture.be.business.user.exceptions;

public class UserRegisterException extends Throwable {

   /* public static final String USERNAME_TOO_SHORT = "Логин должно содержать как миниум 4 символа";
    public static final String USERNAME_TOO_SIMPLE = "Логин должно содержать комбинацию символов и чисел";
    public static final String USERNAME_IS_TAKEN = "Аккаунт с таким логином уже существует";

    public static final String EMAIL_TOO_SHORT = "Имя должно содержать как миниум 4 символа";
    public static final String EMAIL_TOO_SIMPLE = "Имя должно содержать комбинацию символов и чисел";
    public static final String EMAIL_IS_TAKEN = "Аккаунт с таким Email уже существует";

    public static final String PASSWORDS_NOT_MATCH = "Пароли не совпадают";
    public static final String PASSWORD_TOO_SIMPLE = "Пароль должнен содержать комбинацию символов и чисел";*/

    public UserRegisterException(String message) {
        super(message);
    }

}
