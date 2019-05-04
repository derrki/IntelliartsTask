CREATE SCHEMA `souvenir_shop` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

CREATE TABLE `souvenir_shop`.`test` (
  `idtest` INT NOT NULL AUTO_INCREMENT,
  `testcol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtest`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;