create table if not EXISTS faculty(
id bigint PRIMARY KEY AUTO_INCREMENT,
name varchar(255));

create table IF NOT EXISTS student(
id bigint PRIMARY KEY AUTO_INCREMENT,
name varchar(255) NOT NULL,
--address bigint UNIQUE NOT NULL,
faculty_id bigint NOT NULL,
FOREIGN KEY(faculty_id) REFERENCES  faculty(id) );

create table IF NOT EXISTS address(
id bigint PRIMARY KEY AUTO_INCREMENT,
address varchar(255) NOT NULL,
phone varchar(255),
student_id bigint UNIQUE NOT NULL,
FOREIGN KEY(student_id) REFERENCES student(id) ON DELETE CASCADE);

create table IF NOT EXISTS hobby(
id bigint PRIMARY KEY AUTO_INCREMENT,
title varchar(255),
student_id bigint NOT NULL,
FOREIGN KEY(student_id) REFERENCES student(id) ON DELETE CASCADE);

create table if not EXISTS course(
id bigint PRIMARY KEY AUTO_INCREMENT,
name varchar(255) NOT NULL,
teacher varchar(255));

create table if not EXISTS student_course(
id_student bigint,
id_course bigint,
PRIMARY KEY (id_student, id_course),
FOREIGN KEY(id_student) REFERENCES student (id) ON DELETE CASCADE,
FOREIGN KEY(id_course) REFERENCES course (id) ON DELETE CASCADE);


