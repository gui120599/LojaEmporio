-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 19-Mar-2020 às 21:59
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `lojaemporio`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `bairro`
--

CREATE TABLE `bairro` (
  `cod_bairro` int(11) NOT NULL,
  `nome_bairro` varchar(90) DEFAULT NULL,
  `cod_cidade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `bairro`
--

INSERT INTO `bairro` (`cod_bairro`, `nome_bairro`, `cod_cidade`) VALUES
(1, 'Jardim Helvécia', NULL),
(2, 'ts', NULL),
(3, 'Jardim Helvécia', 3),
(4, 'tes', 9),
(5, 'teste', 4),
(6, 'tes', 6),
(7, 'tes', 3),
(8, 'tes', 6),
(9, 'América', 6),
(10, 'tes', 3),
(11, 'Amazonas', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargo`
--

CREATE TABLE `cargo` (
  `cod_cargo` int(11) NOT NULL,
  `desc_cargo` varchar(90) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cargo`
--

INSERT INTO `cargo` (`cod_cargo`, `desc_cargo`) VALUES
(1, 'Vendedor'),
(2, 'Gerente de venda'),
(3, 'Dono'),
(4, 'Gerente');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

CREATE TABLE `cidade` (
  `cod_cidade` int(11) NOT NULL,
  `nome_cidade` varchar(90) DEFAULT NULL,
  `cod_uf` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`cod_cidade`, `nome_cidade`, `cod_uf`) VALUES
(1, 'Goiânia', NULL),
(2, 'Indiara', NULL),
(3, 'Goiânia', 1),
(4, 'Indiara', 1),
(5, 'São Paulo', 3),
(6, 'Rio de Janeiro', 5),
(8, 'Belo', 2),
(9, 'res', 2),
(10, 'rege', 1),
(11, '2', 1),
(12, 'tes', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `nome_cliente` varchar(90) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `cpf_cliente` varchar(14) DEFAULT NULL,
  `rg_cliente` varchar(7) DEFAULT NULL,
  `cnpj_cliente` varchar(18) DEFAULT NULL,
  `telefone_1` varchar(13) DEFAULT NULL,
  `whatsapp_cliente` varchar(14) DEFAULT NULL,
  `email_cliente` varchar(90) DEFAULT NULL,
  `cep_cliente` varchar(9) DEFAULT NULL,
  `cod_uf` int(11) DEFAULT NULL,
  `cod_cidade` int(11) DEFAULT NULL,
  `cod_bairro` int(11) DEFAULT NULL,
  `endereco_cliente` varchar(150) DEFAULT NULL,
  `tipo_pessoa` varchar(20) DEFAULT NULL,
  `status_cliente` tinyint(1) DEFAULT NULL,
  `data_cadastro_cliente` date DEFAULT NULL,
  `cod_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `cod_funcionario` int(11) NOT NULL,
  `cod_cargo` int(11) DEFAULT NULL,
  `nome_funcionario` varchar(90) DEFAULT NULL,
  `data_nascimento_func` date DEFAULT NULL,
  `cpf_func` varchar(14) DEFAULT NULL,
  `rg_func` varchar(7) DEFAULT NULL,
  `sexo_func` varchar(1) DEFAULT NULL,
  `estado_civil_func` varchar(50) DEFAULT NULL,
  `telefone_func` varchar(13) DEFAULT NULL,
  `whatsapp_func` varchar(14) DEFAULT NULL,
  `email_func` varchar(90) DEFAULT NULL,
  `cep_func` varchar(9) DEFAULT NULL,
  `cod_uf` int(11) DEFAULT NULL,
  `cod_cidade` int(11) DEFAULT NULL,
  `cod_bairro` int(11) DEFAULT NULL,
  `endereco_func` varchar(150) DEFAULT NULL,
  `status_func` tinyint(1) DEFAULT NULL,
  `data_cadastro_func` date DEFAULT NULL,
  `cod_usuario_cadastro` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`cod_funcionario`, `cod_cargo`, `nome_funcionario`, `data_nascimento_func`, `cpf_func`, `rg_func`, `sexo_func`, `estado_civil_func`, `telefone_func`, `whatsapp_func`, `email_func`, `cep_func`, `cod_uf`, `cod_cidade`, `cod_bairro`, `endereco_func`, `status_func`, `data_cadastro_func`, `cod_usuario_cadastro`) VALUES
(1, 4, 'teste', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-03-09', NULL),
(2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 1, 'gui', '2020-02-02', '111.111.111-11', '1111111', 'M', 'Solteiro(a)', '(11)1111-1111', '(11)11111-1111', '11111111111', '11111-111', 1, 4, 6, '1 Nº 1 tes Indiara GO', 1, '2020-02-02', 1),
(5, 4, 'Guilherme Marins dos Santos', '1999-05-12', '256.330.031-01', '2222222', 'M', 'Solteiro(a)', '(  )    -    ', '(62)99392-6738', 'gui120599@gmail.com', '74933-550', 1, 3, 3, 'Av.Guajupia Qd85 Lt02 Nº  Jardim Helvécia Goiânia GO', 1, '2020-03-10', 1),
(6, 3, 'teste', '1999-05-12', '056.330.031-01', 'S/N', 'F', 'Solteiro(a)', '(  )    -    ', '(62)99392-6738', 'Não informado', '74933-500', 1, 3, 4, '111 Nº 62 tes Goiânia GO', 1, '2020-03-19', 1),
(7, 1, 'teste', '1999-05-12', '030.303.030-31', '1151351', 'F', 'Solteiro(a)', '(  )    -    ', '(33)33333-3333', 'Não informado', '33333-333', 1, 6, 8, '23 Nº 23 tes Rio de Janeiro GO', 1, '2020-03-19', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itens_movimentacao_usuario`
--

CREATE TABLE `itens_movimentacao_usuario` (
  `Valor_antigo` varchar(150) DEFAULT NULL,
  `Valor_novo` varchar(150) DEFAULT NULL,
  `Cod_movimentacao` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itens_movimentacao_usuario`
--

INSERT INTO `itens_movimentacao_usuario` (`Valor_antigo`, `Valor_novo`, `Cod_movimentacao`) VALUES
('teste', 'teste', 2),
('teste', 'teste', 2),
('7', 'fdf', 2),
('7', '1', 2),
('teste', 'tes', 2),
('teste', 'teste1', 2),
('teste', 'esdf', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 're', 2),
('teste', 'wer', 2),
('7', '4', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'wae', 2),
('teste', 'valendo', 2),
('7', '24', 2),
('teste', 'be', 2),
('7', '23', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `movimentacao_usuario`
--

CREATE TABLE `movimentacao_usuario` (
  `Cod_movimentacao` int(11) NOT NULL,
  `Tipo_movimentacao` varchar(50) DEFAULT NULL,
  `Tabela_alterada` varchar(50) DEFAULT NULL,
  `Cod_registro_alterado` int(11) DEFAULT NULL,
  `Data_hora_movimentacao` datetime DEFAULT NULL,
  `cod_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `movimentacao_usuario`
--

INSERT INTO `movimentacao_usuario` (`Cod_movimentacao`, `Tipo_movimentacao`, `Tabela_alterada`, `Cod_registro_alterado`, `Data_hora_movimentacao`, `cod_usuario`) VALUES
(1, NULL, NULL, NULL, NULL, 1),
(2, 'Atualização', 'funcionario', 0, '2020-03-19 11:45:41', 1),
(3, NULL, NULL, NULL, NULL, 1),
(4, 'Atualização', 'funcionario', 0, '2020-03-19 11:58:27', 1),
(5, 'Salvar', 'funcionario', 6, '2020-03-19 01:03:57', 1),
(6, 'Salvar', 'funcionario', 6, '2020-03-19 01:09:18', 1),
(7, 'Salvar', 'funcionario', 6, '2020-03-19 01:10:33', 1),
(8, NULL, NULL, NULL, NULL, 1),
(9, NULL, NULL, NULL, NULL, 1),
(10, NULL, NULL, NULL, NULL, 1),
(11, NULL, NULL, NULL, NULL, 1),
(12, NULL, NULL, NULL, NULL, 1),
(13, 'Salvar', 'funcionario', 6, '2020-03-19 01:20:17', 1),
(14, NULL, NULL, NULL, NULL, 1),
(15, NULL, NULL, NULL, NULL, 1),
(16, 'Salvar', 'funcionario', 7, '2020-03-19 01:41:29', 1),
(17, NULL, NULL, NULL, NULL, 1),
(18, NULL, NULL, NULL, NULL, 1),
(19, NULL, NULL, NULL, NULL, 1),
(20, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `uf`
--

CREATE TABLE `uf` (
  `cod_uf` int(11) NOT NULL,
  `desc_uf` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `uf`
--

INSERT INTO `uf` (`cod_uf`, `desc_uf`) VALUES
(1, 'GO'),
(2, 'BA'),
(3, 'SP'),
(5, 'RJ'),
(6, 'AM');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `cod_usuario` int(11) NOT NULL,
  `cod_funcionario` int(11) DEFAULT NULL,
  `login_usuario` varchar(90) DEFAULT NULL,
  `senha_usuario` varchar(90) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`cod_usuario`, `cod_funcionario`, `login_usuario`, `senha_usuario`) VALUES
(1, 1, 'gmsantos', '200723');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `bairro`
--
ALTER TABLE `bairro`
  ADD PRIMARY KEY (`cod_bairro`),
  ADD KEY `cod_cidade` (`cod_cidade`);

--
-- Índices para tabela `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`cod_cargo`);

--
-- Índices para tabela `cidade`
--
ALTER TABLE `cidade`
  ADD PRIMARY KEY (`cod_cidade`),
  ADD KEY `cod_uf` (`cod_uf`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cliente`),
  ADD KEY `cod_uf` (`cod_uf`),
  ADD KEY `cod_cidade` (`cod_cidade`),
  ADD KEY `cod_bairro` (`cod_bairro`),
  ADD KEY `cod_usuario` (`cod_usuario`);

--
-- Índices para tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`cod_funcionario`),
  ADD KEY `cod_cargo` (`cod_cargo`),
  ADD KEY `cod_uf` (`cod_uf`),
  ADD KEY `cod_cidade` (`cod_cidade`),
  ADD KEY `cod_bairro` (`cod_bairro`),
  ADD KEY `funcionario_ibfk_5` (`cod_usuario_cadastro`);

--
-- Índices para tabela `itens_movimentacao_usuario`
--
ALTER TABLE `itens_movimentacao_usuario`
  ADD KEY `Cod_movimentacao` (`Cod_movimentacao`);

--
-- Índices para tabela `movimentacao_usuario`
--
ALTER TABLE `movimentacao_usuario`
  ADD PRIMARY KEY (`Cod_movimentacao`),
  ADD KEY `cod_usuario` (`cod_usuario`);

--
-- Índices para tabela `uf`
--
ALTER TABLE `uf`
  ADD PRIMARY KEY (`cod_uf`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cod_usuario`),
  ADD KEY `cod_funcionario` (`cod_funcionario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `bairro`
--
ALTER TABLE `bairro`
  MODIFY `cod_bairro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `cargo`
--
ALTER TABLE `cargo`
  MODIFY `cod_cargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `cidade`
--
ALTER TABLE `cidade`
  MODIFY `cod_cidade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `cod_funcionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `movimentacao_usuario`
--
ALTER TABLE `movimentacao_usuario`
  MODIFY `Cod_movimentacao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de tabela `uf`
--
ALTER TABLE `uf`
  MODIFY `cod_uf` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `bairro`
--
ALTER TABLE `bairro`
  ADD CONSTRAINT `bairro_ibfk_1` FOREIGN KEY (`cod_cidade`) REFERENCES `cidade` (`cod_cidade`);

--
-- Limitadores para a tabela `cidade`
--
ALTER TABLE `cidade`
  ADD CONSTRAINT `cidade_ibfk_1` FOREIGN KEY (`cod_uf`) REFERENCES `uf` (`cod_uf`);

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`cod_uf`) REFERENCES `uf` (`cod_uf`),
  ADD CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`cod_cidade`) REFERENCES `cidade` (`cod_cidade`),
  ADD CONSTRAINT `cliente_ibfk_3` FOREIGN KEY (`cod_bairro`) REFERENCES `bairro` (`cod_bairro`),
  ADD CONSTRAINT `cliente_ibfk_4` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`cod_cargo`) REFERENCES `cargo` (`cod_cargo`),
  ADD CONSTRAINT `funcionario_ibfk_2` FOREIGN KEY (`cod_uf`) REFERENCES `uf` (`cod_uf`),
  ADD CONSTRAINT `funcionario_ibfk_3` FOREIGN KEY (`cod_cidade`) REFERENCES `cidade` (`cod_cidade`),
  ADD CONSTRAINT `funcionario_ibfk_4` FOREIGN KEY (`cod_bairro`) REFERENCES `bairro` (`cod_bairro`),
  ADD CONSTRAINT `funcionario_ibfk_5` FOREIGN KEY (`cod_usuario_cadastro`) REFERENCES `usuario` (`cod_usuario`);

--
-- Limitadores para a tabela `itens_movimentacao_usuario`
--
ALTER TABLE `itens_movimentacao_usuario`
  ADD CONSTRAINT `itens_movimentacao_usuario_ibfk_1` FOREIGN KEY (`Cod_movimentacao`) REFERENCES `movimentacao_usuario` (`Cod_movimentacao`);

--
-- Limitadores para a tabela `movimentacao_usuario`
--
ALTER TABLE `movimentacao_usuario`
  ADD CONSTRAINT `movimentacao_usuario_ibfk_1` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`);

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`cod_funcionario`) REFERENCES `funcionario` (`cod_funcionario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
