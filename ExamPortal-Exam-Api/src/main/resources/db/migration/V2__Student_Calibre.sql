CREATE TABLE `examPortalmicroserviceExam`.`student_calibre` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT NOT NULL,
  `skillId` JSON NULL,
  `experienceId` JSON NULL,
  PRIMARY KEY (`id`));