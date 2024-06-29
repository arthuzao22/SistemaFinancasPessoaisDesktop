-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gestao_financeira_poo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestao_financeira_poo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestao_financeira_poo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `gestao_financeira_poo` ;

-- -----------------------------------------------------
-- Table `gestao_financeira_poo`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_financeira_poo`.`categorias` (
  `categoria_id` INT NOT NULL AUTO_INCREMENT,
  `nome_categoria` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`categoria_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gestao_financeira_poo`.`transacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_financeira_poo`.`transacoes` (
  `transacao_id` INT NOT NULL AUTO_INCREMENT,
  `categoria_id` INT NULL DEFAULT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `data_dia` VARCHAR(2) NULL DEFAULT NULL,
  `dataMesAno` VARCHAR(10) NULL DEFAULT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`transacao_id`),
  INDEX `categoria_id` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `transacoes_ibfk_1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `gestao_financeira_poo`.`categorias` (`categoria_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gestao_financeira_poo`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_financeira_poo`.`usuarios` (
  `usuario_id` INT NOT NULL AUTO_INCREMENT,
  `nome_usuario` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE INDEX `nome_usuario` (`nome_usuario` ASC) VISIBLE,
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
