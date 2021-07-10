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
  `id_user`  INT         NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id`  INT         NOT NULL,
  PRIMARY KEY (`id_user`),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
      REFERENCES `locality_management`.`role` (`id_role`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

insert into locality_management.user (username, password, role_id)
values ('admin', 'admin', 1);
insert into locality_management.user (username, password, role_id)
values ('user', 'user', 2);
insert into locality_management.user (username, password, role_id)
values ('chairmen', 'chairmen', 3);


DROP TABLE IF EXISTS `locality_management`.`locality`;
CREATE TABLE IF NOT EXISTS `locality_management`.`locality`
(
  `id_locality` INT         NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NOT NULL,
  `population`  INT         NOT NULL,
  `user_id`     INT         NOT NULL,
  PRIMARY KEY (`id_locality`),
  CONSTRAINT `fk_locality_user`
    FOREIGN KEY (`user_id`)
      REFERENCES `locality_management`.`user` (`id_user`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

insert into locality_management.locality (name, population, user_id)
values ('minsk', 1000000, 3);
insert into locality_management.locality (name, population, user_id)
values ('gomel', 500000, 3);
insert into locality_management.locality (name, population, user_id)
values ('mogilev', 400000, 3);

DROP TABLE IF EXISTS `locality_management`.`infrastructure`;
CREATE TABLE IF NOT EXISTS `locality_management`.`infrastructure`
(
  `id_infrastructure` INT         NOT NULL AUTO_INCREMENT,
  `name`              VARCHAR(45) NOT NULL,
  `locality_id`       INT         NOT NULL,
  PRIMARY KEY (`id_infrastructure`),
  CONSTRAINT `fk_infrastructure_locality`
    FOREIGN KEY (`locality_id`)
      REFERENCES `locality_management`.`locality` (`id_locality`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

insert into locality_management.infrastructure (name, locality_id)
values ('school N10 minsk', 1);
insert into locality_management.infrastructure (name, locality_id)
values ('sad N20 minsk', 1);
insert into locality_management.infrastructure (name, locality_id)
values ('hospital minsk', 1);

insert into locality_management.infrastructure (name, locality_id)
values ('school N3 gomel', 2);
insert into locality_management.infrastructure (name, locality_id)
values ('sad N8 gomel', 2);
insert into locality_management.infrastructure (name, locality_id)
values ('hospital gomel', 2);

insert into locality_management.infrastructure (name, locality_id)
values ('school mogilev', 3);
insert into locality_management.infrastructure (name, locality_id)
values ('sad mogilev', 3);
insert into locality_management.infrastructure (name, locality_id)
values ('hospital mogilev', 2);