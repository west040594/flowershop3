package com.accenture.be.business.user.exceptions;

public class UserException extends Throwable {

    public static final String USER_UNAUTHORIZED = "Для просмотра требуется авторизация";
    public static final String USER_GRANT = "Недостаточно прав для просмотра";

    public UserException(String message) {
        super(message);
    }

}
