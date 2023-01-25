ALTER TABLE student_response ADD CONSTRAINT fk_question_id FOREIGN KEY (question_id) REFERENCES question_bank(id) ON DELETE CASCADE
ON UPDATE CASCADE;