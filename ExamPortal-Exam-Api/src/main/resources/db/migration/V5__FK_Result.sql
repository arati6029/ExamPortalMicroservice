use examPortalmicroserviceExam;
drop table result;

CREATE TABLE result(
    id BIGINT NOT NULL,
  student_id BIGINT NOT NULL,
  student_marks INT NULL,
  total_marks INT NULL,
  grade INT NULL,
  date_stamp DATETIME NULL,
  PRIMARY KEY (id),
 FOREIGN KEY (student_id) REFERENCES student_competency (student_id)
  );