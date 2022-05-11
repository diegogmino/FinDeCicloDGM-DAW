-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-05-2022 a las 17:40:01
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `findeciclodgmdb`
--
CREATE DATABASE IF NOT EXISTS `findeciclodgmdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `findeciclodgmdb`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `directores`
--

CREATE TABLE `directores` (
  `id` int(5) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `foto` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `directores`
--

INSERT INTO `directores` (`id`, `nombre`, `apellido`, `pais`, `foto`) VALUES
(2, 'Alex', 'Garland', 'Reino Unido', 'garland_alex.jpeg'),
(3, 'Takeshi', 'Kitano', 'Japón', 'kitano_takeshi.jpeg'),
(9, 'Kiyoshi', 'Kurosawa', 'Japón', 'kurosawa_kiyoshi.jpeg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(5) NOT NULL,
  `numeroTarjeta` int(16) NOT NULL,
  `fechaCaducidad` date NOT NULL,
  `cvv` int(3) NOT NULL,
  `idUsuario` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(10) NOT NULL,
  `direccionEnvio` varchar(300) NOT NULL,
  `fechaPedido` date NOT NULL,
  `precioTotal` decimal(5,2) NOT NULL,
  `entregado` tinyint(1) NOT NULL,
  `idUsuario` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `direccionEnvio`, `fechaPedido`, `precioTotal`, `entregado`, `idUsuario`) VALUES
(1, 'C/ Luis Freire García', '2022-04-09', '15.99', 1, 1),
(3, 'C/ Luis Freire García', '2022-04-09', '29.98', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `id` int(10) NOT NULL,
  `codigoBarras` bigint(13) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `portada` varchar(255) NOT NULL,
  `destacada` tinyint(1) NOT NULL,
  `unidades` int(3) NOT NULL,
  `genero` varchar(100) NOT NULL,
  `formato` enum('DVD','Bluray','UHD4K','') NOT NULL,
  `sinopsis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`id`, `codigoBarras`, `titulo`, `precio`, `portada`, `destacada`, `unidades`, `genero`, `formato`, `sinopsis`) VALUES
(1, 8414533133036, 'Ex Machina', '11.99', '8414533133036.jpeg', 1, 50, 'Ciencia ficción', 'Bluray', 'Ex-Machina, un intenso thriller psicológico del aclamado guionista y director Alex Garland (28 Días Después, Sunshine), supone una escalofriante visión de un futuro no tan distante de la inteligencia artificial. En la aislada mansión en las montañas de un multimillonario de Internet, un joven toma parte en un extraño experimento: poner a prueba una inteligencia artificial instalada dentro de una preciosa robot.'),
(2, 8421394000360, 'Aniquilación', '11.99', '8421394000360.jpeg', 0, 100, 'Ciencia ficción', 'Bluray', 'Cuando su marido desaparece durante una misión secreta para regresar sin recordar nada, la bióloga Lena se une a una expedición a una misteriosa región acordonada por el gobierno de los Estados Unidos.'),
(12, 8421394412194, 'Outrage 3', '17.99', '8421394412194.jpeg', 1, 20, 'Acción', 'Bluray', 'Cinco años después de la guerra entre los clanes Sanno y Hanabishi, el antiguo jefe yakuza Otomo vive en Corea del Sur, tratando de mantener un perfil bajo. Pero la sombra de su pasado es alargada y no tardará en alcanzarle. Takeshi Kitano pone fin a la trilogía Outrage con una coda tan estoica como inmensa, hecha de plomo y sangre.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas_directores`
--

CREATE TABLE `peliculas_directores` (
  `idPelicula` int(10) NOT NULL,
  `idDirector` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `peliculas_directores`
--

INSERT INTO `peliculas_directores` (`idPelicula`, `idDirector`) VALUES
(1, 2),
(2, 2),
(12, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas_pedidos`
--

CREATE TABLE `peliculas_pedidos` (
  `id` int(11) NOT NULL,
  `idPelicula` int(10) NOT NULL,
  `idPedido` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `peliculas_pedidos`
--

INSERT INTO `peliculas_pedidos` (`id`, `idPelicula`, `idPedido`) VALUES
(1, 2, 1),
(2, 2, 3),
(5, 12, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfiles`
--

CREATE TABLE `perfiles` (
  `id` int(2) NOT NULL,
  `perfil` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `perfiles`
--

INSERT INTO `perfiles` (`id`, `perfil`) VALUES
(1, 'ADMINISTRADOR'),
(2, 'USUARIO'),
(7, 'VISITANTE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(5) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contrasena` varchar(70) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `direccion` varchar(300) NOT NULL,
  `pais` varchar(50) NOT NULL,
  `fechaRegistro` date NOT NULL,
  `estatus` int(11) NOT NULL,
  `idPerfil` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `email`, `contrasena`, `telefono`, `direccion`, `pais`, `fechaRegistro`, `estatus`, `idPerfil`) VALUES
(1, 'Diego', 'García Miño', 'diego.garcia.mino@gmail.com', '$2a$10$FlS4rSpCpM3ZcdXkQzijBeKflRaRbZpEgKAgVKCmsaAjmOfkoICnq', '681236963', 'C/ Luis Freire García nº 8', 'España', '2022-04-09', 1, 1),
(5, 'Prueba', 'Prueba prueba', 'prueba323232323@gmail.com', '$2a$10$Aww/F8XpHD43iTsEVJq91umD4y7jQ1u3qOlcLPAqE1JE3YPUcrUAy', '645127896', 'Mordor, 3º B', 'La tierra media', '2022-05-08', 1, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `directores`
--
ALTER TABLE `directores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `metodos_pagoToUsuarios` (`idUsuario`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pedidosToUsuarios` (`idUsuario`) USING BTREE;

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigoBarras` (`codigoBarras`);

--
-- Indices de la tabla `peliculas_directores`
--
ALTER TABLE `peliculas_directores`
  ADD PRIMARY KEY (`idPelicula`,`idDirector`),
  ADD KEY `peliculas_directoresToDirectores` (`idDirector`);

--
-- Indices de la tabla `peliculas_pedidos`
--
ALTER TABLE `peliculas_pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `peliculas_pedidosToPeliculas` (`idPelicula`),
  ADD KEY `peliculas_pedidosToPedidos` (`idPedido`);

--
-- Indices de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `usuarioToPerfiles` (`idPerfil`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `directores`
--
ALTER TABLE `directores`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `peliculas_pedidos`
--
ALTER TABLE `peliculas_pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD CONSTRAINT `metodos_pagoToUsuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidosToUsuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `peliculas_directores`
--
ALTER TABLE `peliculas_directores`
  ADD CONSTRAINT `peliculas_directoresToDirectores` FOREIGN KEY (`idDirector`) REFERENCES `directores` (`id`),
  ADD CONSTRAINT `peliculas_directoresToPeliculas` FOREIGN KEY (`idPelicula`) REFERENCES `peliculas` (`id`);

--
-- Filtros para la tabla `peliculas_pedidos`
--
ALTER TABLE `peliculas_pedidos`
  ADD CONSTRAINT `peliculas_pedidosToPedidos` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`id`),
  ADD CONSTRAINT `peliculas_pedidosToPeliculas` FOREIGN KEY (`idPelicula`) REFERENCES `peliculas` (`id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarioToPerfiles` FOREIGN KEY (`idPerfil`) REFERENCES `perfiles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
