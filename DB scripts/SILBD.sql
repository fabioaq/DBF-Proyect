-- MySQL Script generated by MySQL Workbench
-- Sun Nov 12 01:24:00 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SILBD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SILBD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SILBD` DEFAULT CHARACTER SET utf8 ;
USE `SILBD` ;

-- -----------------------------------------------------
-- Table `SILBD`.`TiposInstrumentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SILBD`.`TiposInstrumentos` (
  `codigo` VARCHAR(6) NOT NULL,
  `nombre` VARCHAR(16) NOT NULL,
  `unidad` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SILBD`.`Instrumentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SILBD`.`Instrumentos` (
  `serie` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(120) NOT NULL,
  `minimo` INT NOT NULL,
  `maximo` INT NOT NULL,
  `tolerancia` INT NOT NULL,
  `tipo` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`serie`),
  INDEX `fk_table1_TiposInstrumentos_idx` (`tipo` ASC),
  CONSTRAINT `fk_table1_TiposInstrumentos`
    FOREIGN KEY (`tipo`)
    REFERENCES `SILBD`.`TiposInstrumentos` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SILBD`.`Calibraciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SILBD`.`Calibraciones` (
  `numCalibracion` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `instrumento` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`numCalibracion`),
  INDEX `fk_Calibraciones_Instrumentos1_idx` (`instrumento` ASC),
  CONSTRAINT `fk_Calibraciones_Instrumentos1`
    FOREIGN KEY (`instrumento`)
    REFERENCES `SILBD`.`Instrumentos` (`serie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SILBD`.`Medidas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SILBD`.`Medidas` (
  `numero` INT NOT NULL,
  `referencia` INT NOT NULL,
  `lectura` INT NOT NULL,
  `calibracion` INT NOT NULL,
  PRIMARY KEY (`numero`),
  INDEX `fk_Medidas_Calibraciones1_idx` (`calibracion` ASC),
  CONSTRAINT `fk_Medidas_Calibraciones1`
    FOREIGN KEY (`calibracion`)
    REFERENCES `SILBD`.`Calibraciones` (`numCalibracion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

