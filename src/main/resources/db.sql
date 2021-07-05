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

DROP TABLE IF EXISTS `locality_management`.`locality`;
CREATE TABLE IF NOT EXISTS `locality_management`.`locality`
(
  `id_locality` INT         NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NOT NULL,
  `population`  INT(8)      NOT NULL,
  `user_id`     INT         NOT NULL,
  PRIMARY KEY (`id_locality`),
  CONSTRAINT `fk_locality_user`
    FOREIGN KEY (`user_id`)
      REFERENCES `locality_management`.`user` (`id_user`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS `locality_management`.`infrastructure`;
CREATE TABLE IF NOT EXISTS `locality_management`.`infrastructure`
(
  `id_infrastructure` INT         NOT NULL AUTO_INCREMENT,
  `name`              VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_infrastructure`)
);


DROP TABLE IF EXISTS `locality_management`.`infrastructure_locality`;
CREATE TABLE IF NOT EXISTS `locality_management`.`infrastructure_locality`
(
  `id_infr_local`     INT NOT NULL AUTO_INCREMENT,
  `id_infrastructure` INT NOT NULL,
  `id_locality`       INT NOT NULL,
  PRIMARY KEY (`id_infr_local`),
  CONSTRAINT `fk_infrastructure_locality_id`
    FOREIGN KEY (`id_infrastructure`)
      REFERENCES `locality_management`.`infrastructure` (`id_infrastructure`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT `fk_locality_infrastructure_id`
    FOREIGN KEY (`id_locality`)
      REFERENCES `locality_management`.`locality` (`id_locality`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);