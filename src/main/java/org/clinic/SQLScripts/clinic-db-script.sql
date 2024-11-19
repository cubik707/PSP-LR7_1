CREATE DATABASE clinic_db;

USE clinic_db;

CREATE TABLE doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    birth_date DATE NOT NULL,
    position VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

INSERT INTO doctor (first_name, last_name, middle_name, birth_date, position, specialization) 
VALUES 
('Иван', 'Иванов', 'Иванович', '1980-01-15', 'Хирург', 'Общая хирургия'),
('Мария', 'Петрова', 'Владимировна', '1990-02-20', 'Терапевт', 'Внутренние болезни'),
('Пётр', 'Сидоров', 'Александрович', '1975-05-10', 'Педиатр', 'Педиатрия'),
('Ольга', 'Кузнецова', 'Николаевна', '1985-08-25', 'Кардиолог', 'Кардиология'),
('Дмитрий', 'Лебедев', 'Сергеевич', '1982-12-05', 'Ортопед', 'Ортопедия');
