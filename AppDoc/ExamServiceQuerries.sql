use examPortalmicroserviceExam;
-- skill
CREATE TABLE `examPortalmicroserviceExam`.`skill` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `skill_name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));
-- question
CREATE TABLE `examPortalmicroserviceExam`.`question_bank` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(255) NOT NULL,
  `optA` VARCHAR(255) NOT NULL,
  `optB` VARCHAR(255) NOT NULL,
  `optC` VARCHAR(255) NOT NULL,
  `optD` VARCHAR(255) NOT NULL,
  `expected_ans` JSON NULL,
  `marks_per_ques` INT NULL,
  `neg_marks_per_ques` INT NULL,
  `image_data` LONGBLOB NULL,
  `date_stamp` DATETIME(6) NOT NULL,
  `severity` VARCHAR(80) NULL,
  `skill_id` BIGINT NULL,
  `file_name` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_skill_idx` (`skill_id` ASC),
  CONSTRAINT `fk_skill`
    FOREIGN KEY (`skill_id`)
    REFERENCES `examportalexamtest`.`skill` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

--   experience
CREATE TABLE `examPortalmicroserviceExam`.`experience` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `noOfYears` INT NOT NULL,
  `speciality` VARCHAR(80) NULL,
  PRIMARY KEY (`id`));

--   grading_scheme
-- CREATE TABLE `examportalexamtest`.`grading_scheme` (
--   `id` BIGINT NOT NULL,
--   `gradeA` INT NULL,
--   `gradeB` INT NULL,
--   `gradeC` INT NULL,
--   `gradeF` INT NULL,
--   `dateStamp` DATETIME NULL,
--   PRIMARY KEY (`id`));


-- result
CREATE TABLE `examPortalmicroserviceExam`.`result` (
  `id` BIGINT NOT NULL,
  `student_id` BIGINT NOT NULL,
  `student_marks` INT NULL,
  `total_marks` INT NULL,
  `grade` INT NULL,
  `date_stamp` DATETIME NULL,
  PRIMARY KEY (`id`));

-- student_response
CREATE TABLE `examPortalmicroserviceExam`.`student_response` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT NOT NULL,
  `question_id` BIGINT NOT NULL,
  `response` JSON NULL,
  `date_stamp` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
  ;



