CREATE SCHEMA petfinder;

USE petfinder;

CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    reset_token VARCHAR(255),
    reset_token_expiration DATETIME,
    registration_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login DATETIME
);

CREATE TABLE status (
    status_id INT AUTO_INCREMENT PRIMARY KEY,
    status_name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE animal (
    animal_id INT AUTO_INCREMENT PRIMARY KEY,
    animal_name VARCHAR(100) NOT NULL,
    animal_type VARCHAR(50) NOT NULL,
    animal_breed VARCHAR(100),
    animal_color VARCHAR(50),
    description TEXT,
    image_path VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    lost_date DATETIME,
    status_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (status_id) REFERENCES status(status_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE reporter (
    reporter_id INT AUTO_INCREMENT PRIMARY KEY,
    reporter_name VARCHAR(100),
    reporter_email VARCHAR(100)
);

CREATE TABLE animal_report (
    animal_report_id INT AUTO_INCREMENT PRIMARY KEY,
    animal_id INT NOT NULL,
    reporter_id INT NOT NULL,
    reported_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    note TEXT,
    location_reported VARCHAR(255),
    FOREIGN KEY (animal_id) REFERENCES animal(animal_id),
    FOREIGN KEY (reporter_id) REFERENCES reporter(reporter_id)
);

CREATE TABLE animal_photo (
    photo_id INT AUTO_INCREMENT PRIMARY KEY,
    animal_id INT NOT NULL,
    photo_path VARCHAR(255),
    uploaded_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (animal_id) REFERENCES animal(animal_id)
);

CREATE TABLE animal_location (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    animal_id INT NOT NULL,
    location VARCHAR(255) NOT NULL,
    reported_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (animal_id) REFERENCES animal(animal_id)
);
