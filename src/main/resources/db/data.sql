INSERT INTO faculty (name) VALUES
('Free');

INSERT INTO student (name, faculty_id) VALUES
('Ivan Ivanov', 1),
('Vasya Vasiliev', 1);

INSERT INTO address (address, phone, student_id) VALUES
('Moscow', '851469963', 1),
('Rostov-on-Don', '7854123697', 2);

INSERT INTO hobby (title, student_id) VALUES
('Swimming', 1),
('Football', 1),
('Dances', 2),
('Painting', 2),
('Reading', 2);

INSERT INTO course (name, teacher) VALUES
('mathematics', 'Mrs.Anny'),
('physics', 'Mrs.Janie'),
('chemistry', 'Mrs.Laura');

INSERT INTO student_course (id_student, id_course) VALUES
(1, 1), (1, 3), (2, 2), (2, 1);
