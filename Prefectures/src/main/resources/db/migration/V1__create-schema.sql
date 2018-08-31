CREATE TABLE areas (
	id	INT PRIMARY KEY AUTO_INCREMENT,
	area_code	INT,
	area_name	VARCHAR(10),
	area_name_ank	VARCHAR(20)
);

CREATE TABLE prefectures (
	id	INT PRIMARY KEY AUTO_INCREMENT,
	prefecture_code	INT,
	prefecture_name	VARCHAR(10),
	prefecture_name_ank	VARCHAR(20),
	prefecture_area_code	INT,
	username	VARCHAR(20)
);

CREATE TABLE users (
	username VARCHAR(20) NOT NULL PRIMARY KEY,
	encoded_password VARCHAR(255)
);
