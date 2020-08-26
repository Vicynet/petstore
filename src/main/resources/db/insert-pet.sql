SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE pets;


INSERT INTO pets(`id`, `name`, `type`, `breed`, `sex`, `age`)
VALUES (15, 'Biggy', 'DOG', 'Russian Bull', 'FEMALE', 2),
       (16, 'Lalal', 'CAT','Russian Cat', 'MALE', 3);

SET FOREIGN_KEY_CHECKS = 1;