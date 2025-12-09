-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Tempo de geração: 08/12/2025 às 02:04
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `museu`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `atracao`
--

CREATE TABLE `atracao` (
  `idAtracao` int(11) NOT NULL,
  `nomeAtracao` varchar(100) NOT NULL,
  `descricao` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `atracao`
--

INSERT INTO `atracao` (`idAtracao`, `nomeAtracao`, `descricao`) VALUES
(1, 'Salão dos Dinossauros', 'Exposição de fósseis do período Cretáceo'),
(2, 'Planetário', 'Observação de estrelas e projeção 3D'),
(3, 'História Egípcia', 'Múmias e artefatos do Egito Antigo'),
(5, 'esnuipe', 'klkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkesnuepe');

-- --------------------------------------------------------

--
-- Estrutura para tabela `guia`
--

CREATE TABLE `guia` (
  `idGuia` int(11) NOT NULL,
  `nomeGuia` varchar(100) NOT NULL,
  `especialidade` varchar(100) DEFAULT NULL,
  `emailGuia` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `guia`
--

INSERT INTO `guia` (`idGuia`, `nomeGuia`, `especialidade`, `emailGuia`) VALUES
(1, 'Carlos Silva', 'Paleontologia', 'carlos@museu.com'),
(2, 'Ana Souza', 'Astronomia', 'ana@museu.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `reserva`
--

CREATE TABLE `reserva` (
  `idReserva` int(11) NOT NULL,
  `idVisitante` int(11) NOT NULL,
  `dataReserva` date NOT NULL,
  `horario` time NOT NULL,
  `status` enum('ativa','cancelada','concluída') DEFAULT 'ativa'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `reserva`
--

INSERT INTO `reserva` (`idReserva`, `idVisitante`, `dataReserva`, `horario`, `status`) VALUES
(2, 2, '2222-10-11', '11:11:11', 'ativa'),
(4, 4, '2012-12-12', '12:12:12', 'ativa');

-- --------------------------------------------------------

--
-- Estrutura para tabela `reservaatracao`
--

CREATE TABLE `reservaatracao` (
  `idReserva` int(11) NOT NULL,
  `idAtracao` int(11) NOT NULL,
  `horarioVisita` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `reservaatracao`
--

INSERT INTO `reservaatracao` (`idReserva`, `idAtracao`, `horarioVisita`) VALUES
(2, 3, '11:11:11'),
(4, 2, '12:12:12');

-- --------------------------------------------------------

--
-- Estrutura para tabela `reservaguia`
--

CREATE TABLE `reservaguia` (
  `idReserva` int(11) NOT NULL,
  `idGuia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `reservaguia`
--

INSERT INTO `reservaguia` (`idReserva`, `idGuia`) VALUES
(2, 2),
(4, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `visitante`
--

CREATE TABLE `visitante` (
  `idVisitante` int(11) NOT NULL,
  `nomeVisitante` varchar(100) NOT NULL,
  `emailVisitante` varchar(50) NOT NULL,
  `telVisitante` varchar(15) DEFAULT NULL,
  `dataNasc` date DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `tipoUsuario` enum('ADMIN','VISITANTE') DEFAULT 'VISITANTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `visitante`
--

INSERT INTO `visitante` (`idVisitante`, `nomeVisitante`, `emailVisitante`, `telVisitante`, `dataNasc`, `senha`, `tipoUsuario`) VALUES
(1, 'Administrador', 'admin@museu.com', NULL, NULL, 'admin123', 'ADMIN'),
(2, 'João Visitante', 'joao@gmail.com', '4299999999', NULL, '1234', 'VISITANTE'),
(4, 'fulano', 'fulano@gmail.com', '34343434', '2012-12-12', '123', 'VISITANTE');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `atracao`
--
ALTER TABLE `atracao`
  ADD PRIMARY KEY (`idAtracao`);

--
-- Índices de tabela `guia`
--
ALTER TABLE `guia`
  ADD PRIMARY KEY (`idGuia`);

--
-- Índices de tabela `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `idVisitante` (`idVisitante`);

--
-- Índices de tabela `reservaatracao`
--
ALTER TABLE `reservaatracao`
  ADD PRIMARY KEY (`idReserva`,`idAtracao`),
  ADD KEY `idAtracao` (`idAtracao`);

--
-- Índices de tabela `reservaguia`
--
ALTER TABLE `reservaguia`
  ADD PRIMARY KEY (`idReserva`,`idGuia`),
  ADD KEY `idGuia` (`idGuia`);

--
-- Índices de tabela `visitante`
--
ALTER TABLE `visitante`
  ADD PRIMARY KEY (`idVisitante`),
  ADD UNIQUE KEY `emailVisitante` (`emailVisitante`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `atracao`
--
ALTER TABLE `atracao`
  MODIFY `idAtracao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `guia`
--
ALTER TABLE `guia`
  MODIFY `idGuia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `reserva`
--
ALTER TABLE `reserva`
  MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `visitante`
--
ALTER TABLE `visitante`
  MODIFY `idVisitante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`idVisitante`) REFERENCES `visitante` (`idVisitante`);

--
-- Restrições para tabelas `reservaatracao`
--
ALTER TABLE `reservaatracao`
  ADD CONSTRAINT `reservaatracao_ibfk_1` FOREIGN KEY (`idReserva`) REFERENCES `reserva` (`idReserva`) ON DELETE CASCADE,
  ADD CONSTRAINT `reservaatracao_ibfk_2` FOREIGN KEY (`idAtracao`) REFERENCES `atracao` (`idAtracao`);

--
-- Restrições para tabelas `reservaguia`
--
ALTER TABLE `reservaguia`
  ADD CONSTRAINT `reservaguia_ibfk_1` FOREIGN KEY (`idReserva`) REFERENCES `reserva` (`idReserva`) ON DELETE CASCADE,
  ADD CONSTRAINT `reservaguia_ibfk_2` FOREIGN KEY (`idGuia`) REFERENCES `guia` (`idGuia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
