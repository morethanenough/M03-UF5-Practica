DROP DATABASE IF EXISTS m03;
CREATE DATABASE IF NOT EXISTS m03;
USE m03;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-05-2024 a las 15:26:42
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `m03`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `id_foto` int(11) NOT NULL,
  `win_rounds` int(11) NOT NULL DEFAULT 0,
  `lost_rounds` int(11) NOT NULL DEFAULT 0,
  `points` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `game`
--

INSERT INTO `game` (`id`, `id_user`, `name`, `id_foto`, `win_rounds`, `lost_rounds`, `points`) VALUES
(1, 1, 'morethanenough', 1, 12, 3, 1050),
(2, 2, 'AAPP', 2, 4, 5, 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pic`
--

CREATE TABLE `pic` (
  `id` int(11) NOT NULL,
  `src` varchar(255) NOT NULL,
  `type` enum('player','rival') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pic`
--

INSERT INTO `pic` (`id`, `src`, `type`) VALUES
(1, 'dllamas.png', 'player'),
(2, 'alejandro.png', 'player'),
(3, 'quim.png', 'player'),
(4, 'joan.png', 'player'),
(5, 'rivals/fconstants.png', 'rival'),
(6, 'rivals/gcasacuberta.png', 'rival'),
(7, 'rivals/gtorrents.png', 'rival'),
(8, 'rivals/jcortes.png', 'rival'),
(9, 'rivals/jgrau.png', 'rival'),
(10, 'rivals/jmgaya.png', 'rival'),
(11, 'rivals/ocortes.png', 'rival'),
(12, 'rivals/ppalain.png', 'rival');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rivals`
--

CREATE TABLE `rivals` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `frase1` varchar(255) NOT NULL,
  `frase2` varchar(255) NOT NULL,
  `id_foto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rivals`
--

INSERT INTO `rivals` (`id`, `nombre`, `frase1`, `frase2`, `id_foto`) VALUES
(1, 'Oriol', 'Aquest bypass del Safe Exam Browser no m\'ho coneixia.', '2 hores i mitja per cada hora de classe no són suficients.', 11),
(2, 'Jordi C.', 'Tindré que afegir això a l\'Sprint Retrospective.', 'Això no passaria si ho hagués testejat amb el Mockito.', 8),
(5, 'Josep Maria', 'Com pot ser? Les màquines no fan ping!', 'Al crontab ja hi estava marcat el moment de la meva victòria.', 10),
(6, 'Francesc', 'M\'aniria bé prendre una mica la fresca.', 'Developer, no has vigilat i has acabat amb un burnout de la ostia!', 5),
(7, 'Pablo', 'El disseny d\'aquesta UX/UI no contemplava la teva victòria.', 'Esperava uns mockups i m\'has donat unes captures de pantalla.', 12),
(8, 'Guillem', 'Demà patireu!', 'No has seguit l\'estàndard PSR12.', 6),
(9, 'Jordi G.', 'He perdut la clau pública!', 'Has de donar-li un repàs a les rutes relatives.', 9),
(10, 'Gerard', 'Ho reconec. IntelliJ és una merda...', 'No has utilitzat col·leccions.', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `admin` int(11) NOT NULL DEFAULT 0,
  `pass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `name`, `admin`, `pass`) VALUES
(1, 'didac', 1, 'didac'),
(2, 'alejandro', 1, 'alejandro'),
(3, 'joan', 1, 'joan'),
(4, 'quim', 1, 'quim');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_foto` (`id_foto`);

--
-- Indices de la tabla `pic`
--
ALTER TABLE `pic`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rivals`
--
ALTER TABLE `rivals`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_foto` (`id_foto`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pic`
--
ALTER TABLE `pic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `rivals`
--
ALTER TABLE `rivals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `game_ibfk_2` FOREIGN KEY (`id_foto`) REFERENCES `pic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `rivals`
--
ALTER TABLE `rivals`
  ADD CONSTRAINT `rivals_ibfk_1` FOREIGN KEY (`id_foto`) REFERENCES `pic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
