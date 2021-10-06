-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 06-Out-2021 às 02:57
-- Versão do servidor: 10.4.20-MariaDB
-- versão do PHP: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `simplerest`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `products`
--

CREATE TABLE `products` (
  `id` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL,
  `STATUS` tinyint(1) NOT NULL DEFAULT 1,
  `product_group_id` varchar(60) NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `products`
--

INSERT INTO `products` (`id`, `name`, `STATUS`, `product_group_id`, `price`, `stock`, `created_at`, `updated_at`) VALUES
('5430298b-263f-11ec-bd0c-842b2b7c7f98', 'Picanha', 1, 'f35fa291-263e-11ec-bd0c-842b2b7c7f98', 73.99, 50, '2021-10-06 00:49:57', '2021-10-06 00:49:57');

-- --------------------------------------------------------

--
-- Estrutura da tabela `products_groups`
--

CREATE TABLE `products_groups` (
  `id` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL,
  `STATUS` tinyint(1) NOT NULL DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `products_groups`
--

INSERT INTO `products_groups` (`id`, `name`, `STATUS`, `created_at`, `updated_at`) VALUES
('f35fa291-263e-11ec-bd0c-842b2b7c7f98', 'Carnes', 1, '2021-10-06 00:47:15', '2021-10-06 00:47:15'),
('f35fc365-263e-11ec-bd0c-842b2b7c7f98', 'Bebidas', 1, '2021-10-06 00:47:15', '2021-10-06 00:47:15');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `id` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL,
  `email` varchar(60) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `age` int(3) NOT NULL COMMENT 'user age',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `users_group`
--

CREATE TABLE `users_group` (
  `id` varchar(60) NOT NULL,
  `name` varchar(40) NOT NULL DEFAULT 'Admin',
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `users_users_group`
--

CREATE TABLE `users_users_group` (
  `id` varchar(60) NOT NULL,
  `user_id` varchar(60) NOT NULL,
  `users_groups_id` varchar(60) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `products_groups`
--
ALTER TABLE `products_groups`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `users_group`
--
ALTER TABLE `users_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `users_users_groups_fk_users` (`id`);

--
-- Índices para tabela `users_users_group`
--
ALTER TABLE `users_users_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `users_users_groups_fk_users_groups` (`id`),
  ADD KEY `oog_fk_users_groups` (`user_id`),
  ADD KEY `oog_fk_users_groups2` (`users_groups_id`);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `users_users_group`
--
ALTER TABLE `users_users_group`
  ADD CONSTRAINT `oog_fk_users_groups` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `oog_fk_users_groups2` FOREIGN KEY (`users_groups_id`) REFERENCES `users_group` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
