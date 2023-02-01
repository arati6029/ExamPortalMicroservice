
//Admin table
CREATE TABLE `testexamportaluser`.`admin` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `acc_status` BIT(1) NOT NULL,
  `address_line1` VARCHAR(50) NULL,
  `address_line2` VARCHAR(50) NULL,
  `date_stamp` DATETIME(6) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `mobile` VARCHAR(20) NULL,
  `name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `pin_code` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
 
  //address
  CREATE TABLE `testexamportaluser`.`address` (
  `pin_code` VARCHAR(20) NOT NULL,
  `city` VARCHAR(20) NULL,
  `country` VARCHAR(20) NULL,
  `state` VARCHAR(20) NULL,
  PRIMARY KEY (`pin_code`));

  //client
  CREATE TABLE `testexamportaluser`.`client` (
  `id` BIGINT NOT NULL,
  `acc_status` BIT(1) NOT NULL,
  `address_line1` VARCHAR(50) NULL,
  `address_line2` VARCHAR(50) NULL,
  `date_stamp` DATETIME(6) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `mobile` VARCHAR(20) NULL,
  `name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `exam_count` INT NOT NULL,
  `pin_code` VARCHAR(20) NOT NULL,
  `sub_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

//exam_admin
CREATE TABLE `testexamportaluser`.`exam_admin` (
  `id` BIGINT NOT NULL,
  `acc_status` BIT(1) NOT NULL,
  `address_line1` VARCHAR(50) NULL,
  `address_line2` VARCHAR(50) NULL,
  `date_stamp` DATETIME(6) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `mobile` VARCHAR(20) NULL,
  `name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `department` VARCHAR(40) NULL,
  `dob` DATE NOT NULL,
  `gender` VARCHAR(20) NOT NULL,
  `pin_code` VARCHAR(20) NOT NULL,
  `c_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pin_code_idx` (`pin_code` ASC) VISIBLE,
  CONSTRAINT `fk_pin_code`
    FOREIGN KEY (`pin_code`)
    REFERENCES `testexamportaluser`.`address` (`pin_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

//student
CREATE TABLE `testexamportaluser`.`student` (
  `id` BIGINT NOT NULL,
  `acc_status` BIT(1) NOT NULL,
  `address_line1` VARCHAR(50) NULL,
  `address_line2` VARCHAR(50) NULL,
  `date_stamp` DATETIME(6) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `mobile` VARCHAR(20) NULL,
  `name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `dob` DATE NOT NULL,
  `gender` VARCHAR(20) NOT NULL,
  `roll_no` INT NOT NULL,
  `pin_code` VARCHAR(20) NOT NULL,
  `ea_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_pin_code_idx` (`pin_code` ASC) VISIBLE,
  INDEX `fk_examAdmin_idx` (`ea_id` ASC) VISIBLE,
  CONSTRAINT `fk_address`
    FOREIGN KEY (`pin_code`)
    REFERENCES `testexamportaluser`.`address` (`pin_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_examAdmin`
    FOREIGN KEY (`ea_id`)
    REFERENCES `testexamportaluser`.`exam_admin` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
//subscription
CREATE TABLE `testexamportaluser`.`subscription` (
  `id` BIGINT NOT NULL,
  `date_stamp` DATETIME(6) NOT NULL,
  `no_of_exams` INT NOT NULL,
  `plan_name` VARCHAR(200) NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));



//exam 
CREATE TABLE `testexamportaluser`.`exam` (
  `id` BIGINT NOT NULL,
  `date_stamp` DATETIME(6) NOT NULL,
  `exam_duration` INT NOT NULL,
  `exam_name` VARCHAR(60) NOT NULL,
  `exam_status` BIT(1) NOT NULL,
  `marks_per_ques` INT NOT NULL,
  `neg_marks_per_ques` INT NOT NULL,
  `no_of_ques` INT NOT NULL,
  `result_date` DATE NULL,
  `scheduled_date` DATE NOT NULL,
  `scheduled_time` TIME NOT NULL,
  `ea_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`));

  //