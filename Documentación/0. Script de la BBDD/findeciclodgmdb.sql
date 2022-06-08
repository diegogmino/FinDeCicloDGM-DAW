-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-06-2022 a las 13:23:48
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
  `foto` varchar(255) NOT NULL,
  `foto_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `directores`
--

INSERT INTO `directores` (`id`, `nombre`, `apellido`, `pais`, `foto`, `foto_id`) VALUES
(2, 'Alex', 'Garland', 'Reino Unido', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668473/gbs2nuvshnrsqmcfcpig.webp', 'gbs2nuvshnrsqmcfcpig'),
(3, 'Takeshi', 'Kitano', 'Japón', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668827/a82k92ihqtrzaqbbtwvt.webp', 'a82k92ihqtrzaqbbtwvt'),
(10, 'Kiyoshi', 'Kurosawa', 'Japón', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668966/wx4cmcbk7qkfmyvrgj6y.webp', 'wx4cmcbk7qkfmyvrgj6y'),
(11, 'Akira', 'Kurosawa', 'Japón', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668843/htfewd1qukmd9kj1bmmq.jpg', 'htfewd1qukmd9kj1bmmq'),
(12, 'Quentin', 'Tarantino', 'Estados Unidos', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668853/s87zn6avtqn4tuzbqvzn.jpg', 's87zn6avtqn4tuzbqvzn'),
(13, 'Peter', 'Jackson', 'Nueva Zelanda', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668864/s00ch2htvvrzhdmkix3z.jpg', 's00ch2htvvrzhdmkix3z'),
(14, 'Tetsuya', 'Nakashima', 'Japón', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668875/qcli64q1tbhmdwo0net2.webp', 'qcli64q1tbhmdwo0net2'),
(15, 'Todd', 'Phillips', 'Estados Unidos', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668884/aumbamm5gccjrrg9ruda.jpg', 'aumbamm5gccjrrg9ruda'),
(16, 'David', 'Lynch', 'Estados Unidos', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668893/xzq0akenswa3vuopco8v.webp', 'xzq0akenswa3vuopco8v'),
(17, 'James', 'Wan', 'Estados Unidos', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668906/eovfzxww74tlqasyssyb.webp', 'eovfzxww74tlqasyssyb'),
(18, 'Lilly', 'Wachowski', 'Estados Unidos', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668915/rux8j9uuqnd0yw67nrhd.jpg', 'rux8j9uuqnd0yw67nrhd'),
(19, 'Lana ', 'Wachowski', 'Estados Unidos', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668925/ge6rbxbaxumlu4p6srao.jpg', 'ge6rbxbaxumlu4p6srao'),
(20, 'Satoshi', 'Kon', 'Japón', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668934/vptv4xx8uzowh2ey4uxk.jpg', 'vptv4xx8uzowh2ey4uxk'),
(21, 'Hayao', 'Miyazaki', 'Japón', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668944/mdg9zcpv1orf7ogwohiw.jpg', 'mdg9zcpv1orf7ogwohiw'),
(22, 'Mamoru', 'Hosoda', 'Japón', 'http://res.cloudinary.com/diegogmino/image/upload/v1653668055/enatrzh5pzxbeaiqm7bm.jpg', 'enatrzh5pzxbeaiqm7bm'),
(23, 'Tomohiko', 'Ito', 'Japón', 'http://res.cloudinary.com/diegogmino/image/upload/v1654092354/d5shg6eymcxqjaermxbs.jpg', 'd5shg6eymcxqjaermxbs'),
(24, 'Sydney', 'Pollack', 'Estados Unidos', 'http://res.cloudinary.com/diegogmino/image/upload/v1654684514/ax65cablgzcsi5oruznj.jpg', 'ax65cablgzcsi5oruznj');

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
(31, 'Luis Freire García nº 8', '2022-06-01', '29.97', 1, 1),
(32, 'Luis Freire García nº 8', '2022-06-01', '18.98', 0, 1),
(33, '221B Baker Street', '2022-06-01', '30.97', 0, 28),
(34, 'Monte del destino, ático', '2022-06-03', '47.97', 0, 29),
(35, 'Luis Freire García nº 8', '2022-06-03', '31.97', 0, 1),
(36, 'Luis Freire García nº 8', '2022-06-08', '23.97', 0, 1);

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
  `portada_id` varchar(500) DEFAULT NULL,
  `destacada` tinyint(1) NOT NULL,
  `unidades` int(3) NOT NULL,
  `genero` varchar(100) NOT NULL,
  `formato` enum('DVD','Bluray','UHD4K','') NOT NULL,
  `sinopsis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`id`, `codigoBarras`, `titulo`, `precio`, `portada`, `portada_id`, `destacada`, `unidades`, `genero`, `formato`, `sinopsis`) VALUES
(2, 8421394000360, 'Aniquilación', '11.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666301/e4dhscnuoaifoze37tho.jpg', 'e4dhscnuoaifoze37tho', 0, 93, 'Ciencia ficción', 'Bluray', 'Cuando su marido desaparece durante una misión secreta para regresar sin recordar nada, la bióloga Lena se une a una expedición a una misteriosa región acordonada por el gobierno de los Estados Unidos.'),
(12, 8421394412194, 'Outrage 3', '17.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666617/nxy2xorgc2lbxabaodsu.jpg', 'nxy2xorgc2lbxabaodsu', 0, 17, 'Acción', 'Bluray', 'Cinco años después de la guerra entre los clanes Sanno y Hanabishi, el antiguo jefe yakuza Otomo vive en Corea del Sur, tratando de mantener un perfil bajo. Pero la sombra de su pasado es alargada y no tardará en alcanzarle. Takeshi Kitano pone fin a la trilogía Outrage con una coda tan estoica como inmensa, hecha de plomo y sangre.'),
(16, 8436597560283, 'La mujer del espía', '16.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666700/gvrifo6dlsfkfgjcumwd.jpg', 'gvrifo6dlsfkfgjcumwd', 0, 15, 'Thriller', 'Bluray', 'En vísperas del estallido de la II Guerra Mundial. Yusaku (Issey Takahashi) es el director de una compañía comercial en Kobe (Japón), y está casado con Satoko (Yu Aoi). En un viaje a Manchuria durante 1940, Yusaku descubre un terrible secreto nacional. En nombre de la justicia decide hacerlo público, lo que le convierte al instante en un enemigo público. Sin embargo, Satoko asegura creerle y jura que estará a su lado independientemente de las consecuencias.'),
(17, 8436535542906, 'Los siete samuráis', '15.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666726/d4gbv4ukotdql2ple0qm.jpg', 'd4gbv4ukotdql2ple0qm', 0, 6, 'Aventuras', 'Bluray', 'Una banda de forajidos atemorizan a los hebitantes de un pequeño pueblo, saqueándolos periódicamente sin piedad. Para repeler estos ataques, los aldeanos deciden contratar a mercenarios. Finalmente, consiguen los servicios de 7 guerreros, 7 samurais dispuestos a defenderlos a cambio, tan solo, de cobijo y comida.'),
(18, 8420266026323, 'Joker', '19.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666738/wctts9mzkveeih5ebclr.jpg', 'wctts9mzkveeih5ebclr', 0, 21, 'Thriller', 'UHD4K', 'Protagonizada por Joaquin Phoenix como Arthur Fleck, la película explorará los orígenes del personaje, mostrando la historia de un hombre derribado por la sociedad que se convierte en el hombre que conocemos como el Joker. Ganadora de los Premios Oscar a Mejor Actor y Mejor Banda Sonora en 2020.'),
(19, 8414533124461, 'Érase una vez… en Hollywood', '14.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666762/ec8tzaksfvw4ildicomt.jpg', 'ec8tzaksfvw4ildicomt', 0, 8, 'Comedia', 'Bluray', 'Érase una vez... en Hollywood de Quentin Tarantino visita la ciudad de Los Ángeles de 1969, donde todo está cambiando, cuando la estrella de cine Rick Dalton (Leonardo DiCaprio) y su doble de toda la vida Cliff Booth (Brad Pitt) se abren paso en una industria que apenas reconocen.'),
(20, 8437012592759, 'El Mundo de Kanako', '9.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666771/ikrkij3jffa1t017qjkk.jpg', 'ikrkij3jffa1t017qjkk', 1, 3, 'Drama', 'Bluray', 'Cuando Kanako, hija pródiga y alumna intachable, desaparece, su madre no duda en llamar a su exmarido, un policía poco ortodoxo. A medida que la investigación avanza, la imagen idealizada de Kanako se va resquebrajando, dejando entrever que, bajo la excelencia, la chica esconde otra vida, más oscura y secreta.'),
(21, 8436535543033, 'Yojimbo', '15.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666781/czc1ve7qejt5e1jcgh5n.jpg', 'czc1ve7qejt5e1jcgh5n', 0, 10, 'Acción', 'Bluray', 'En el siglo XIX, en un Japón todavía feudal, un samurái llega a un poblado, donde dos bandas de mercenarios luchan entre sí por el control del territorio. Muy pronto el recién llegado da muestras de ser un guerrero invencible, por lo que los jefes de las dos bandas intentan contratar sus servicios.'),
(22, 8436535545416, 'El Señor de los Anillos: La Comunidad del Anillo', '15.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666793/g5sggxyvlhefu25la7fl.jpg', 'g5sggxyvlhefu25la7fl', 0, 1, 'Fantástico', 'Bluray', 'Con la compañía de unos cuantos héroes, Frodo Bolsón se embarca en un peligroso viaje para llevar el místico Anillo Único hasta el Monte del Destino para que se destruyan tanto el anillo como sus poderes, y no caiga en manos del malvado Sauron. El asombroso viaje comienza en la primera entrega de la épica Trilogía del director y coguinista Peter Jackson con la que ha redefinido el Cine de Fantasía.'),
(23, 8453247951648, 'El Señor de los Anillos 2: Las dos torres', '15.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666806/u1bwxmbanmanbt2bhmxe.jpg', 'u1bwxmbanmanbt2bhmxe', 0, 2, 'Fantástico', 'Bluray', 'Frodo y Sam prosiguen su camino a Mordor: Gollum insiste en ser su guía. ¿Se puede confiar en alguien tan corrompido por el Anillo? ¿Se puede confiar en Frodo, tan afectado por el poder del anillo? Mientras, Aragorn, cada vez más cerca de su destino regio, agrupa fuerzas del bien para las batallas que se avecinan.'),
(24, 8456328974561, 'El Señor de los Anillos 3: El retorno del Rey', '15.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666815/lnq1ggur7zynbjmuyg9n.jpg', 'lnq1ggur7zynbjmuyg9n', 0, 1, 'Fantástico', 'Bluray', 'Ha llegado el momento de que Frodo se enfrente a la malicia de Gollum, al horrible ataque de Ella Laraña, y al atormentador encanto de un anillo que se resiste a su destrucción. De que Aragorn acepte la espada de sus antepasados y la corona que le pertenece por nacimiento.'),
(25, 8456329784651, 'Carretera perdida', '9.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666832/h8ciijbpv32jqhybxzaa.jpg', 'h8ciijbpv32jqhybxzaa', 1, 49, 'Suspense', 'Bluray', 'Fred Madison (Bill Pullman), un músico de jazz que vive con su esposa Renee (Patricia Arquette), recibe unas misteriosas cintas de vídeo en las que aparece una grabación de él con su mujer dentro de su propia casa. Poco después, durante una fiesta, un misterioso hombre (Robert Blake) le dice que está precisamente en su casa en ese instante. Las sospechas de que algo raro está pasando se tornan terroríficas cuando ve la siguiente cinta de video...'),
(26, 8462598451035, 'El hombre elefante', '9.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666842/oni1mx8iqinxhqblr8qf.jpg', 'oni1mx8iqinxhqblr8qf', 1, 4, 'Suspense', 'Bluray', 'A finales del siglo XIX, el doctor Frederick Treves descubre en un circo a un hombre llamado John Merrick. Se trata de un ciudadano británico con la cabeza monstruosamente deformada, que vive en una situación de constante humillación y sufrimiento al ser exhibido diariamente como una atracción de feria.'),
(27, 8457963215648, 'Maligno', '15.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666852/by5xwci9urdga68jclf9.jpg', 'by5xwci9urdga68jclf9', 0, 22, 'Terror', 'Bluray', 'Madison está paralizada por visiones de asesinatos espeluznantes, y su tormento empeora cuando descubre que estos sueños de vigilia son, de hecho, realidades aterradoras.'),
(28, 8414533133036, 'Ex Machina', '11.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666861/rnpzx3jh3wuttp1mwalr.jpg', 'rnpzx3jh3wuttp1mwalr', 0, 18, 'Ciencia ficción', 'Bluray', 'Ex-Machina, un intenso thriller psicológico del aclamado guionista y director Alex Garland (28 Días Después, Sunshine), supone una escalofriante visión de un futuro no tan distante de la inteligencia artificial. En la aislada mansión en las montañas de un multimillonario de Internet, un joven toma parte en un extraño experimento: poner a prueba una inteligencia artificial instalada dentro de una preciosa robot.'),
(29, 8412569874301, 'Matrix', '19.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666875/omjug99gqyao0h6nie4j.jpg', 'omjug99gqyao0h6nie4j', 0, 0, 'Ciencia ficción', 'UHD4K', 'PERCEPCIÓN: Nuestro mundo es real. REALIDAD: Ese mundo es un sueño, una complicada trampa elaborada por un programa de un ordenador de inteligencia artificial que controla nuestra vida. Keanu Reeves y Laurence Fishburne lideran la lucha para liberar a la humanidad en este cautivador ciberthriller.'),
(30, 8456325978154, 'Perfect Blue', '9.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666885/ni7a1ppdzo4j2atykfhi.jpg', 'ni7a1ppdzo4j2atykfhi', 1, 9, 'Anime', 'Bluray', 'Mima es la cantante de un famoso grupo musical japonés. Debido al fracaso de ventas de sus discos, su mánager decide apartarla del grupo y darle un papel en una serie de televisión. Mima cae entonces en una profunda depresión que la lleva a replantearse su vida y su carrera, pero su crisis se agrava cuando descubre que su vida está al alcance de cualquiera en Internet y que alguien la está vigilando. Cuando la serie empieza a emitirse por televisión, Mima comprueba que la ficción se reproduce en su vida real: sueño y realidad se confunden hasta el punto de cuestionarse su propia identidad. El desarrollo de los acontecimientos y su propia intuición llevarán a la protagonista a un desenlace absolutamente inesperado...'),
(31, 8456259781036, 'Mi vecino Totoro', '9.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653666895/ldl7yqu5jtaopu01kzwi.jpg', 'ldl7yqu5jtaopu01kzwi', 1, 4, 'Anime', 'Bluray', 'En los años 50, una familia japonesa se traslada al campo. Las dos hijas, Satsuki y Mei, entablan amistad con Totoro, un espíritu del bosque. El padre es un profesor universitario que estimula la imaginación de sus hijas relatándoles fábulas e historias mágicas sobre duendes, fantasmas y espíritus protectores de los hogares, mientras la madre se encuentra enferma en el hospital.'),
(34, 8412569854236, 'Terciopelo Azul', '9.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1653667112/cmto903thvwjaevlb2er.jpg', 'cmto903thvwjaevlb2er', 1, 9, 'Thriller', 'Bluray', 'Una mañana, Jeffrey Beaumont (Kyle MacLachlan), después de visitar a su padre en el hospital, encuentra entre unos arbustos una oreja humana. La guarda en una bolsa de papel y la lleva a la comisaría de policía, donde le atiende el detective Williams (George Dickerson), que es vecino suyo. Comienza así una misteriosa intriga que desvelará extraños sucesos acontecidos en una pequeña localidad de Carolina del Norte.'),
(43, 8412549651238, 'El viaje de Chihiro', '14.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1654092271/gixi7ub44pfkjezgq20a.jpg', 'gixi7ub44pfkjezgq20a', 0, 15, 'Anime', 'Bluray', 'Chihiro es una niña de diez años que viaja en coche con sus padres. Después de atravesar un túnel, llegan a un mundo fantástico, en el que no hay lugar para los seres humanos, sólo para los dioses de primera y segunda clase. Cuando descubre que sus padres han sido convertidos en cerdos, Chihiro se siente muy sola y asustada.'),
(44, 8412795412065, 'Hello World', '9.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1654092428/wc9nz5l3s9ysriooqje5.jpg', 'wc9nz5l3s9ysriooqje5', 1, 9, 'Anime', 'Bluray', 'En el año 2027, Naomi Katagaki se encuentra con su yo de 10 años en el futuro. Ambos tendrán que salvar a la joven Ruri Ichigyō, con quien Naomi empezará a salir en los tres meses siguientes, y así cambiar su destino.'),
(45, 8412465897416, 'Mirai, mi hermana pequeña', '29.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1654092581/t7jalaionltu2zlxscpl.jpg', 't7jalaionltu2zlxscpl', 0, 6, 'Anime', 'UHD4K', 'Kun, un niño mimado y consentido de cuatro años al que sus padres dejan de prestar atención cuando nace su hermana Mirai, empieza a sufrir situaciones en casa que nunca había vivido. Pero entonces, la versión adolescente de su hermana viaja en el tiempo desde el futuro para vivir junto a Kun una aventura extraordinaria más allá de lo imaginable.'),
(46, 8421394001943, 'Memorias de África', '9.99', 'http://res.cloudinary.com/diegogmino/image/upload/v1654684764/puf01xm1qtxni6iaef6b.jpg', 'puf01xm1qtxni6iaef6b', 1, 5, 'Romance', 'Bluray', 'Libremente inspirada en la obra homónima de la escritora danesa Isak Dinesen. A principios del siglo XX, Karen (Streep) contrae un matrimonio de conveniencia con el barón Blixen (Brandauer), un mujeriego empedernido. Ambos se establecen en Kenia con el propósito de explotar una plantación de café. En Karen Blixen nace un apasionado amor por la tierra y por las gentes de Kenia. Pero también se enamora pérdidamente de Denys Finch-Hatton (Redford), un personaje aventurero y romántico a la antigua usanza, que ama la libertad por encima de todas las cosas.');

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
(2, 2),
(12, 3),
(16, 10),
(17, 11),
(18, 15),
(19, 12),
(20, 14),
(21, 11),
(22, 13),
(23, 13),
(24, 13),
(25, 16),
(26, 16),
(27, 17),
(28, 2),
(29, 18),
(29, 19),
(30, 20),
(31, 21),
(34, 16),
(43, 21),
(44, 23),
(45, 22),
(46, 24);

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
(47, 17, 31),
(48, 26, 31),
(49, 19, 32),
(50, 16, 33),
(51, 34, 33),
(52, 22, 34),
(53, 23, 34),
(54, 24, 34),
(55, 17, 35),
(56, 28, 35),
(57, 25, 36),
(58, 44, 36);

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
(2, 'USUARIO');

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
(1, 'Diego', 'García Miño', 'admin@gmail.com', '$2a$10$axAloj7Y3sQsN0vgRueio.douKauU2xpKN5l42x3WdjsGu3qfiQle', '681236963', 'Luis Freire García nº 8', 'España', '2022-04-09', 1, 1),
(28, 'Sherlock', 'Holmes', 'sherlock@gmail.com', '$2a$10$1IQdLnZRyY2BhdPuB/7la.2WUgZzUqL2Ixhfb2II4CzUTDUHa0giy', '123456789', '221B Baker Street', 'Reino Unido', '2022-06-01', 1, 2),
(29, 'Sauron', 'Lord', 'sauron@gmail.com', '$2a$10$2RctCuLjq74iH8ntVioijeDJRos7Kc1WEmIRgOC4ORboShm8UJ9gW', '987654321', 'Monte del destino, ático', 'Mordor', '2022-06-03', 1, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `directores`
--
ALTER TABLE `directores`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `peliculas_pedidos`
--
ALTER TABLE `peliculas_pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Restricciones para tablas volcadas
--

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
