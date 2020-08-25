SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE pet;

INSERT INTO pet(`id`, `name`, `breed`, `type`, `sex`, `age`)
VALUES (12, 'Biggy', 'Russian Bull', 'Dog', 'Female', 2)

SET FOREIGN_KEY_CHECKS = 1;