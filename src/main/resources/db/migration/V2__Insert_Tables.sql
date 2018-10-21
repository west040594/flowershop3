INSERT INTO tb_user (username, first_name, last_name, password, email, status, role, created_at, updated_at)
VALUES ('admin', 'adminF', 'adminL', 'admin', 'admin@example.com', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_user (username, first_name, last_name, password, email, status, role, created_at, updated_at)
VALUES ('user', 'userF', 'userL', 'user', 'user@example.com', 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_user (username, first_name, last_name, password, email, status, role, created_at, updated_at)
VALUES ('moderator', 'moderatorF', 'moderatorL', 'moderator', 'moderator@example.com', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
