-- =========================================================
-- data.sql — тестовые данные для Student Management System
-- PostgreSQL. Положить в src/main/resources/data.sql
-- (Spring Boot автоматически выполнит его при старте, если
--  spring.sql.init.mode=always и Hibernate уже создал таблицы)
-- =========================================================
-- Предполагаемая схема (подгони имена таблиц/колонок под свои @Entity):
--   teachers(id, first_name, last_name, email, specialization)
--   courses(id, title, description, credits, teacher_id)
--   students(id, first_name, last_name, email, phone, enrollment_date)
--   enrollments(id, student_id, course_id, enrollment_date, grade)
-- =========================================================

-- Очистка перед вставкой (удобно при повторных запусках)
TRUNCATE TABLE enrollments RESTART IDENTITY CASCADE;
TRUNCATE TABLE courses RESTART IDENTITY CASCADE;
TRUNCATE TABLE students RESTART IDENTITY CASCADE;
TRUNCATE TABLE teachers RESTART IDENTITY CASCADE;

-- =========================================================
-- 1. TEACHERS
-- =========================================================
INSERT INTO teachers (id, first_name, last_name, email, specialization) VALUES
                                                                            (1, 'Алишер', 'Токтогулов', 'a.toktogulov@megalab.kg', 'Backend Development'),
                                                                            (2, 'Динара',  'Садыкова',   'd.sadykova@megalab.kg',   'Frontend Development'),
                                                                            (3, 'Марат',   'Жумабеков',  'm.jumabekov@megalab.kg',  'Databases'),
                                                                            (4, 'Айгерим', 'Нурланова',  'a.nurlanova@megalab.kg',  'DevOps & Cloud'),
                                                                            (5, 'Бекзат',  'Исаков',     'b.isakov@megalab.kg',     'Data Analytics'),
                                                                            (6, 'Гульнара','Осмонова',   'g.osmonova@megalab.kg',   'QA & Testing');

-- =========================================================
-- 2. COURSES
-- =========================================================
INSERT INTO courses (id, title, description, credits, teacher_id) VALUES
                                                                      (1, 'Java Core & OOP',           'Основы Java, ООП, коллекции, потоки ввода-вывода', 6, 1),
                                                                      (2, 'Spring Boot REST API',      'Разработка REST API на Spring Boot, JPA, Security', 8, 1),
                                                                      (3, 'React.js для начинающих',   'Компоненты, hooks, state management',              6, 2),
                                                                      (4, 'HTML/CSS/JavaScript',       'Основы веб-верстки и клиентского JS',               4, 2),
                                                                      (5, 'PostgreSQL и SQL',          'Проектирование БД, нормализация, сложные запросы',  5, 3),
                                                                      (6, 'NoSQL: MongoDB',            'Документоориентированные базы данных',              4, 3),
                                                                      (7, 'Docker & Kubernetes',       'Контейнеризация и оркестрация приложений',          6, 4),
                                                                      (8, 'CI/CD с GitHub Actions',    'Автоматизация сборки и деплоя',                     4, 4),
                                                                      (9, 'Python для анализа данных', 'Pandas, NumPy, визуализация данных',                6, 5),
                                                                      (10, 'Тестирование ПО (QA)',     'Виды тестирования, JUnit, Mockito',                 5, 6);

-- =========================================================
-- 3. STUDENTS
-- =========================================================
INSERT INTO students (id, first_name, last_name, email, phone, enrollment_date) VALUES
                                                                                    (1,  'Азамат',   'Кубанычбеков', 'azamat.k@example.com', '996700111001', '2024-09-01'),
                                                                                    (2,  'Айгуль',   'Мамбетова',    'aigul.m@example.com',  '996700111002', '2024-09-01'),
                                                                                    (3,  'Данияр',   'Асанов',       'daniyar.a@example.com','996700111003', '2024-09-01'),
                                                                                    (4,  'Жаныл',    'Törökulova',   'janyl.t@example.com',  '996700111004', '2024-09-02'),
                                                                                    (5,  'Максат',   'Орозбеков',    'maksat.o@example.com', '996700111005', '2024-09-02'),
                                                                                    (6,  'Нурзат',   'Абдылдаева',   'nurzat.a@example.com', '996700111006', '2024-09-03'),
                                                                                    (7,  'Эрлан',    'Сыдыков',      'erlan.s@example.com',  '996700111007', '2024-09-03'),
                                                                                    (8,  'Камила',   'Джумалиева',   'kamila.j@example.com', '996700111008', '2024-09-04'),
                                                                                    (9,  'Тимур',    'Бекболотов',   'timur.b@example.com',  '996700111009', '2024-09-04'),
                                                                                    (10, 'Салтанат', 'Ниязова',      'saltanat.n@example.com','996700111010','2024-09-05'),
                                                                                    (11, 'Руслан',   'Абдиев',       'ruslan.a@example.com', '996700111011', '2024-09-05'),
                                                                                    (12, 'Айзада',   'Кудайбергенова','aizada.k@example.com','996700111012', '2024-09-06'),
                                                                                    (13, 'Бекбол',   'Жапаров',      'bekbol.j@example.com', '996700111013', '2024-09-06'),
                                                                                    (14, 'Чолпон',   'Матиева',      'cholpon.m@example.com','996700111014', '2024-09-07'),
                                                                                    (15, 'Ислам',    'Турдубеков',   'islam.t@example.com',  '996700111015', '2024-09-07');

-- =========================================================
-- 4. ENROLLMENTS (many-to-many: student <-> course)
-- =========================================================
INSERT INTO enrollments (id, student_id, course_id, enrollment_date, grade) VALUES
                                                                                (1,  1,  1,  '2024-09-05', 5),
                                                                                (2,  1,  2,  '2024-09-05', 4),
                                                                                (3,  1,  5,  '2024-09-06', 5),
                                                                                (4,  2,  3,  '2024-09-05', 5),
                                                                                (5,  2,  4,  '2024-09-05', 4),
                                                                                (6,  3,  1,  '2024-09-06', 3),
                                                                                (7,  3,  2,  '2024-09-06', 4),
                                                                                (8,  3,  7,  '2024-09-07', 5),
                                                                                (9,  4,  3,  '2024-09-06', 4),
                                                                                (10, 4,  4,  '2024-09-06', 5),
                                                                                (11, 4,  6,  '2024-09-07', 4),
                                                                                (12, 5,  9,  '2024-09-07', 5),
                                                                                (13, 5,  5,  '2024-09-08', 4),
                                                                                (14, 6,  1,  '2024-09-08', 4),
                                                                                (15, 6,  10, '2024-09-08', 5),
                                                                                (16, 7,  2,  '2024-09-09', 3),
                                                                                (17, 7,  8,  '2024-09-09', 4),
                                                                                (18, 8,  3,  '2024-09-09', 5),
                                                                                (19, 8,  4,  '2024-09-10', 5),
                                                                                (20, 9,  7,  '2024-09-10', 4),
                                                                                (21, 9,  8,  '2024-09-10', 3),
                                                                                (22, 10, 5,  '2024-09-11', 5),
                                                                                (23, 10, 6,  '2024-09-11', 4),
                                                                                (24, 11, 1,  '2024-09-11', 4),
                                                                                (25, 11, 2,  '2024-09-12', 5),
                                                                                (26, 12, 9,  '2024-09-12', 4),
                                                                                (27, 12, 10, '2024-09-12', 5),
                                                                                (28, 13, 3,  '2024-09-13', 3),
                                                                                (29, 13, 4,  '2024-09-13', 4),
                                                                                (30, 14, 7,  '2024-09-13', 5),
                                                                                (31, 14, 8,  '2024-09-14', 4),
                                                                                (32, 15, 1,  '2024-09-14', 5),
                                                                                (33, 15, 5,  '2024-09-14', 4),
                                                                                (34, 15, 9,  '2024-09-15', 5);

-- Синхронизация автоинкремента (на случай ручных id в INSERT)
SELECT setval(pg_get_serial_sequence('teachers', 'id'), (SELECT MAX(id) FROM teachers));
SELECT setval(pg_get_serial_sequence('courses', 'id'), (SELECT MAX(id) FROM courses));
SELECT setval(pg_get_serial_sequence('students', 'id'), (SELECT MAX(id) FROM students));
SELECT setval(pg_get_serial_sequence('enrollments', 'id'), (SELECT MAX(id) FROM enrollments));