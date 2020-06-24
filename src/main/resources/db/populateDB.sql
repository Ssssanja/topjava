DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE global_meal_seq RESTART WITH 50000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories) VALUES
('2020, 1, 30, 10:00', 'завтрак', '500'),
('2020, 1, 30, 13:00', 'обед', '1000'),
('2020, 1, 30, 20:00', 'ужин', '500'),
('2020, 1, 31, 00:00', 'Еда на граничное значение', '100'),
('2020, 1, 31, 10:00', 'завтрак', '50'),
('2020, 1, 31, 13:00', 'обед', '1000'),
('2020, 1, 31, 20:00', 'ужин', '410');
