DROP DATABASE IF EXISTS travellog;
CREATE DATABASE IF NOT EXISTS travellog;
USE travellog;

CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT,
    user_username VARCHAR(50) UNIQUE NOT NULL,
	user_password VARCHAR(50) NOT NULL,
	user_email VARCHAR(100) UNIQUE NOT NULL,
	PRIMARY KEY (user_id)
);

CREATE TABLE travels (
    travel_id INT NOT NULL AUTO_INCREMENT,
	travel_name VARCHAR(50) NOT NULL,
	travel_itinerary TEXT,
	user_id INT NOT NULL,
	PRIMARY KEY (travel_id),
    CONSTRAINT travel_fk FOREIGN KEY (user_id)
        REFERENCES users (user_id)
		ON DELETE CASCADE
);

CREATE TABLE flights (
    flight_id INT NOT NULL AUTO_INCREMENT,
    flight_date DATE NOT NULL,
	flight_source VARCHAR(50) NOT NULL,
	flight_destination VARCHAR(50) NOT NULL,
	travel_id INT NOT NULL,
	PRIMARY KEY (flight_id),
	CONSTRAINT flight_fk FOREIGN KEY (travel_id)
		REFERENCES travels (travel_id)
		ON DELETE CASCADE
);

INSERT INTO users (user_username, user_password, user_email) VALUES
('user001', 'pass123', 'user001@gmail.com'),
('user002', 'pass123', 'user002@gmail.com');

INSERT INTO travels (travel_name, travel_itinerary, user_id) VALUES
('Holiday 2020', 'Trip around Asia: Singapore -> Japan -> Korea -> Singapore', 1),
('Business Trip', 'Week-long stay in Australia', 2);

INSERT INTO flights (flight_date, flight_source, flight_destination, travel_id) VALUES
('2023-01-25', 'Singapore', 'Japan', 1),
('2023-02-02', 'Japan', 'Korea', 1),
('2023-02-11', 'Korea', 'Singapore', 1),
('2023-01-05', 'Australia', 'Singapore', 2),
('2020-01-12', 'Singapore', 'Australia', 2);