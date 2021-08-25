DROP SCHEMA IF EXISTS `locality_management`;
CREATE SCHEMA IF NOT EXISTS `locality_management` DEFAULT CHARACTER SET utf8;
USE `locality_management`;

DROP TABLE IF EXISTS `locality_management`.`role`;
CREATE TABLE IF NOT EXISTS `locality_management`.`role`
(
  `id_role` INT         NOT NULL AUTO_INCREMENT,
  `role`    VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_role`)
);

insert into locality_management.role (role)
values ('admin');
insert into locality_management.role (role)
values ('user');
insert into locality_management.role (role)
values ('chairmen');

DROP TABLE IF EXISTS `locality_management`.`user`;
CREATE TABLE IF NOT EXISTS `locality_management`.`user`
(
  `id_user`    INT         NOT NULL AUTO_INCREMENT,
  `username`   VARCHAR(45) NOT NULL,
  `password`   VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name`  VARCHAR(45) NOT NULL,
  `role_id`    INT         NOT NULL,
  PRIMARY KEY (`id_user`),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
      REFERENCES `locality_management`.`role` (`id_role`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

insert into locality_management.user (username, password, first_name, last_name, role_id)
values ('admin', 'admin', 'admin', 'admin', 1);
insert into locality_management.user (username, password, first_name, last_name, role_id)
values ('user', 'user', 'ivan', 'ivanov', 2);
insert into locality_management.user (username, password, first_name, last_name, role_id)
values ('chairmen', 'chairmen', 'ivan', 'ivanov', 3);
insert into locality_management.user (username, password, first_name, last_name, role_id)
values ('chairmen2', 'chairmen2', 'petr', 'petrov', 3);

DROP TABLE IF EXISTS `locality_management`.`status_locality`;
CREATE TABLE IF NOT EXISTS `locality_management`.`status_locality`
(
  `id_status` INT         NOT NULL AUTO_INCREMENT,
  `status`    VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_status`)
);
insert into locality_management.status_locality (status)
values ('city');
insert into locality_management.status_locality (status)
values ('town');
insert into locality_management.status_locality (status)
values ('village');

DROP TABLE IF EXISTS `locality_management`.`locality`;
CREATE TABLE IF NOT EXISTS `locality_management`.`locality`
(
  `id_locality` INT         NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NOT NULL,
  `population`  INT         NOT NULL,
  `user_id`     INT         NOT NULL,
  `status_id`   INT         NOT NULL,
  PRIMARY KEY (`id_locality`),
  CONSTRAINT `fk_locality_user`
    FOREIGN KEY (`user_id`)
      REFERENCES `locality_management`.`user` (`id_user`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT `fk_locality_status_locality`
    FOREIGN KEY (`status_id`)
      REFERENCES `locality_management`.`status_locality` (`id_status`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

insert into locality_management.locality (name, population, user_id, status_id)
values ('minsk', 1000000, 3, 1);
insert into locality_management.locality (name, population, user_id, status_id)
values ('gomel', 500000, 3, 1);
insert into locality_management.locality (name, population, user_id, status_id)
values ('mogilev', 400000, 3, 1);
insert into locality_management.locality (name, population, user_id, status_id)
values ('sluck', 30000, 3, 2);
insert into locality_management.locality (name, population, user_id, status_id)
values ('molodechno', 20000, 3, 2);
insert into locality_management.locality (name, population, user_id, status_id)
values ('town', 10000, 3, 2);
insert into locality_management.locality (name, population, user_id, status_id)
values ('village', 10000, 3, 3);

DROP TABLE IF EXISTS `locality_management`.`infrastructure`;
CREATE TABLE IF NOT EXISTS `locality_management`.`infrastructure`
(
  `id_infrastructure` INT         NOT NULL AUTO_INCREMENT,
  `name`              VARCHAR(45) NOT NULL,
  `square_meters`     INT         NOT NULL,
  `floors`            INT         NOT NULL,
  `persons`           INT         NOT NULL,
  `locality_id`       INT         NOT NULL,
  PRIMARY KEY (`id_infrastructure`),
  CONSTRAINT `fk_infrastructure_locality`
    FOREIGN KEY (`locality_id`)
      REFERENCES `locality_management`.`locality` (`id_locality`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('school N10 minsk', 1000, 2, 50, 1);
insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('sad N20 minsk', 2000, 3, 150, 1);
insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('hospital minsk', 5000, 4, 250, 1);

insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('school N3 gomel', 1000, 2, 50, 2);
insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('sad N8 gomel', 1000, 2, 50, 2);
insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('hospital gomel', 1000, 2, 50, 2);

insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('school mogilev', 2000, 3, 150, 3);
insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('sad mogilev', 2000, 3, 150, 3);
insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('hospital mogilev', 2000, 3, 150, 3);

insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('hospital', 2000, 3, 150, 4);

insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('hospital', 2000, 3, 150, 5);

insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('hospital', 2000, 3, 150, 6);

insert into locality_management.infrastructure (name, square_meters, floors, persons, locality_id)
values ('hospital', 2000, 3, 150, 7);