CREATE SCHEMA `souvenir_shop` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

CREATE TABLE `souvenir_shop`.`purchases` (
  `idpurchases` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `name_souvenir` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `currency` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpurchases`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;