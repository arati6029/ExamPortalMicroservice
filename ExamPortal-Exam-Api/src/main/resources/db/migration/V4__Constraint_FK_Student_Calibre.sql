use examPortalmicroserviceExam;
drop table student_calibre;

CREATE TABLE student_calibre(
   student_id BIGINT NOT NULL PRIMARY KEY,
  skillId BIGINT NULL,
  experienceId BIGINT NULL,
  date_stamp DATETIME NOT NULL);
 

CREATE TABLE student_skill(
   student_id BIGINT NOT NULL,
 skillId BIGINT NOT NULL,
   date_stamp DATETIME NOT NULL,
PRIMARY KEY (student_id, skillId),
FOREIGN KEY (student_id) REFERENCES student_calibre (student_id),
FOREIGN KEY (skillId) REFERENCES skill (id));
  

CREATE TABLE student_exp (
   student_id BIGINT NOT NULL,
   experienceId BIGINT NOT NULL,
   date_stamp DATETIME NOT NULL,
PRIMARY KEY (student_id, experienceId),
FOREIGN KEY (student_id) REFERENCES student_calibre (student_id),
FOREIGN KEY (experienceId) REFERENCES experience(id));
  
