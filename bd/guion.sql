CREATE SCHEMA IF NOT EXISTS `chekeado` DEFAULT CHARACTER SET utf8 ;

USE `chekeado` ;

CREATE TABLE IF NOT EXISTS `chekeado`.`categorias` (
  `categoria_id` INT(11) NOT NULL AUTO_INCREMENT,
  `categoria_nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`categoria_id`));

CREATE TABLE IF NOT EXISTS `chekeado`.`chequeos` (
`chequeo_id` INT(11) NOT NULL AUTO_INCREMENT,
  `chequeo_categoria` INT(11) NOT NULL,
  `chequeo_palabraclave` VARCHAR(45) NULL DEFAULT NULL,
  `chequeo_frase` VARCHAR(45) NULL DEFAULT NULL,
  `chequeo_medio_origen` VARCHAR(45) NULL DEFAULT NULL,
  `chequeo_enlace` VARCHAR(45) NULL DEFAULT NULL,
  `chequeo_fecha` DATE NULL DEFAULT NULL,
  `chequeo_verificacion` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`chequeo_id`),
  INDEX `chequeo_categoria_idx` (`chequeo_categoria` ASC) VISIBLE,
  CONSTRAINT `chequeo_categoria`
    FOREIGN KEY (`chequeo_categoria`)
    REFERENCES `chekeado`.`categorias` (`categoria_id`));

CREATE TABLE IF NOT EXISTS `chekeado`.`explicaciones` (
  `explicacion_id` INT(11) NOT NULL AUTO_INCREMENT,
  `explicacion_nombre` VARCHAR(45) NULL DEFAULT NULL,
  `explicacion_epigrafe` VARCHAR(225) NULL DEFAULT NULL,
  `explicacion_contenido` VARCHAR(225) NULL DEFAULT NULL,
  `explicacion_fecha_creacion` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`explicacion_id`));

CREATE TABLE IF NOT EXISTS `chekeado`.`investigaciones` (
  `investigacion_id` INT(11) NOT NULL AUTO_INCREMENT,
  `investigacion_tema` VARCHAR(45) NULL DEFAULT NULL,
  `investigacion_palabraclave` VARCHAR(45) NULL DEFAULT NULL,
  `investigacion_titulo` VARCHAR(45) NULL DEFAULT NULL,
  `investigacion_epigrafe` TEXT NULL DEFAULT NULL,
  `investigacion_contenido` TEXT NULL DEFAULT NULL,
  `investigacion_fecha_creacion` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`investigacion_id`))
;

CREATE TABLE IF NOT EXISTS `chekeado`.`sugerencia` (
  `sugerencia_id` INT(11) NOT NULL,
  `frase` VARCHAR(255) NOT NULL,
  `sugerencia_autor_frase` VARCHAR(45) NULL DEFAULT NULL,
  `sugerencia_medio_frase` VARCHAR(255) NOT NULL,
  `sugerencia_enlace` VARCHAR(255) NULL DEFAULT NULL,
  `sugerencia_fecha_frase` DATE NOT NULL,
  PRIMARY KEY (`sugerencia_id`));

CREATE TABLE IF NOT EXISTS `chekeado`.`explicaciones_has_chequeos` (
  `explicaciones_explicacion_id` INT(11) NOT NULL,
  `chequeos_chequeo_id` INT(11) NOT NULL,
  PRIMARY KEY (`explicaciones_explicacion_id`, `chequeos_chequeo_id`),
  INDEX `fk_explicaciones_has_chequeos_chequeos1_idx` (`chequeos_chequeo_id` ASC) VISIBLE,
  INDEX `fk_explicaciones_has_chequeos_explicaciones1_idx` (`explicaciones_explicacion_id` ASC) VISIBLE,
  CONSTRAINT `fk_explicaciones_has_chequeos_explicaciones1`
    FOREIGN KEY (`explicaciones_explicacion_id`)
    REFERENCES `chekeado`.`explicaciones` (`explicacion_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_explicaciones_has_chequeos_chequeos1`
    FOREIGN KEY (`chequeos_chequeo_id`)
    REFERENCES `chekeado`.`chequeos` (`chequeo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
