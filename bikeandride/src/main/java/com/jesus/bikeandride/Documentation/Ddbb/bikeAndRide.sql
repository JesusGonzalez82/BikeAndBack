CREATE TABLE `actividades` (
  `id_actividad` INT AUTO_INCREMENT NOT NULL,
  `fecha` DATE NOT NULL,
  `duracion` TIME NOT NULL,
  `velocidad_media` DECIMAL(5,2) NOT NULL,
  `velocidad_max` DECIMAL(5,2) NOT NULL,
  `calorias` DECIMAL(7,2) NULL,
  `id_usuario` INT NULL,
  `id_bici` INT NULL,
  `id_ruta` INT NULL,
   PRIMARY KEY (`id_actividad`)
)
ENGINE = InnoDB;
CREATE TABLE `bicicletas` (
  `id_bici` INT AUTO_INCREMENT NOT NULL,
  `tipo_bici` ENUM('road','mtb','gravel') NOT NULL,
  `marca` VARCHAR(255) NOT NULL,
  `modelo` VARCHAR(255) NOT NULL,
  `anio` YEAR NULL,
  `peso` DECIMAL(5,2) NULL,
  `material` VARCHAR(255) NOT NULL,
  `status` ENUM('en uso','vendida','en mantenimiento') NOT NULL DEFAULT 'en uso' ,
  `id_usuario` INT NULL,
   PRIMARY KEY (`id_bici`)
)
ENGINE = InnoDB;
CREATE TABLE `comentarios` (
  `id_comentario` INT AUTO_INCREMENT NOT NULL,
  `comentario` VARCHAR(255) NOT NULL,
  `fecha` DATE NOT NULL,
  `id_usuario` INT NULL,
  `id_actividad` INT NULL,
   PRIMARY KEY (`id_comentario`)
)
ENGINE = InnoDB;
CREATE TABLE `emails` (
  `id_email` INT AUTO_INCREMENT NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `id_usuario` INT NULL,
   PRIMARY KEY (`id_email`),
  CONSTRAINT `email` UNIQUE (`email`)
)
ENGINE = InnoDB;
CREATE TABLE `imagenes` (
  `id_imagen` INT AUTO_INCREMENT NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  `tipo` ENUM('usuario','bicicleta','ruta','actividad') NOT NULL,
  `id_usuario` INT NULL,
  `id_bici` INT NULL,
  `id_ruta` INT NULL,
  `id_actividad` INT NULL,
   PRIMARY KEY (`id_imagen`)
)
ENGINE = InnoDB;
CREATE TABLE `rutas` (
  `id_ruta` INT AUTO_INCREMENT NOT NULL,
  `nombre_ruta` VARCHAR(255) NOT NULL,
  `distancia` DECIMAL(7,2) NOT NULL,
  `desnivel` INT NOT NULL,
  `tipo_terreno` ENUM('asfalto','montaña','mixto') NOT NULL,
  `descripcion_ruta` VARCHAR(255) NULL,
   PRIMARY KEY (`id_ruta`)
)
ENGINE = InnoDB;
CREATE TABLE `usuarios` (
  `id_usuario` INT AUTO_INCREMENT NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `fecha_nac` DATE NOT NULL,
  `status` ENUM('activo','inactivo','baneado') NOT NULL DEFAULT 'activo' ,
   PRIMARY KEY (`id_usuario`)
)
ENGINE = InnoDB;
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (1, '2022-01-18', '02:41:42', '16.80', '42.80', '1251.00', 1, 3, 4);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (2, '2023-10-01', '06:09:20', '24.80', '69.60', '2595.00', 1, 4, 3);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (3, '2019-05-02', '05:07:23', '18.20', '45.40', '834.00', 1, 2, 2);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (4, '2021-05-29', '04:19:40', '20.02', '67.30', '1977.00', 1, 4, 1);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (5, '2024-10-10', '04:19:40', '20.02', '67.30', '1977.00', 1, 1, 1);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (6, '2022-06-12', '03:30:15', '22.30', '58.20', '1850.00', 2, 5, 5);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (7, '2021-09-15', '02:50:30', '19.50', '55.40', '1600.00', 3, 6, 1);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (8, '2023-02-10', '01:45:12', '15.80', '47.20', '1350.00', 4, 7, 2);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (9, '2023-07-19', '04:00:00', '21.00', '60.50', '2000.00', 5, 8, 6);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (10, '2022-11-03', '05:12:22', '17.50', '50.30', '1400.00', 6, 9, 3);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (11, '2024-03-25', '03:10:40', '20.70', '65.40', '1900.00', 7, 10, 4);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (12, '2023-04-20', '02:30:00', '18.00', '45.00', '1200.00', 8, 11, 5);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (13, '2022-08-22', '04:20:10', '19.80', '53.20', '1700.00', 9, 12, 2);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (14, '2021-12-05', '03:40:50', '22.10', '62.50', '2100.00', 10, 13, 1);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (15, '2023-01-14', '03:05:30', '20.30', '59.80', '1800.00', 11, 14, 6);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (16, '2023-06-25', '02:45:00', '18.50', '55.50', '1450.00', 12, 15, 4);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (17, '2022-09-30', '04:10:30', '20.00', '50.00', '1600.00', 13, 27, 5);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (18, '2023-03-15', '03:20:40', '22.40', '62.00', '2000.00', 14, 28, 2);
INSERT INTO `actividades` (`id_actividad`, `fecha`, `duracion`, `velocidad_media`, `velocidad_max`, `calorias`, `id_usuario`, `id_bici`, `id_ruta`) VALUES (19, '2024-01-05', '04:00:10', '19.30', '58.70', '1750.00', 15, 29, 6);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (1, 'road', 'Orbea', 'Orca M20 iTeam', 2022, '7.98', 'carbono', 'en uso', 1);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (2, 'mtb', 'Orbea', 'Alma H20', 2018, '13.50', 'aluminio', 'vendida', 1);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (3, 'gravel', 'Cube', 'Analog', 2014, '10.90', 'aluminio', 'vendida', 1);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (4, 'road', 'Specialized', 'Tarmcac', 2010, '6.90', 'Carbono', 'vendida', 1);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (5, 'mtb', 'Megamo', 'Track 04 cw', 2025, '11.80', 'carbono', 'en uso', 1);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (6, 'road', 'Trek', 'Emonda SL', 2020, '7.50', 'carbono', 'en uso', 2);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (7, 'mtb', 'Cannondale', 'Scalpel SE', 2021, '12.00', 'aluminio', 'en uso', 3);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (8, 'gravel', 'Bianchi', 'Intenso', 2017, '9.50', 'acero', 'en uso', 4);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (9, 'road', 'Merida', 'Scultura 4000', 2019, '8.30', 'carbono', 'en uso', 5);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (10, 'mtb', 'Scott', 'Spark 930', 2021, '11.50', 'aluminio', 'en uso', 6);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (11, 'road', 'Pinarello', 'Dogma F10', 2018, '6.80', 'carbono', 'en uso', 7);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (12, 'gravel', 'Felt', 'FX 2', 2016, '10.20', 'aluminio', 'en uso', 8);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (13, 'road', 'Giant', 'Defy Advan', 2020, '7.80', 'carbono', 'en uso', 9);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (14, 'mtb', 'Trek', 'Marlin 7', 2022, '12.50', 'aluminio', 'en uso', 10);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (15, 'road', 'Canyon', 'Endurace CF SL', 2021, '7.60', 'carbono', 'en uso', 11);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (16, 'gravel', 'BMC', 'URS 01', 2020, '9.40', 'carbono', 'en uso', 12);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (17, 'mtb', 'GT', 'Force Carbon', 2021, '12.80', 'carbono', 'en uso', 2);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (18, 'gravel', 'Cannondale', 'Topstone Carbon', 2020, '9.90', 'carbono', 'en uso', 3);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (19, 'road', 'Cervélo', 'R5', 2022, '7.10', 'carbono', 'en uso', 4);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (20, 'mtb', 'Trek', 'Fuel EX 9.9', 2020, '12.00', 'carbono', 'en uso', 5);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (21, 'gravel', 'Specialized', 'Sequoia', 2018, '10.40', 'aluminio', 'en uso', 6);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (22, 'road', 'Bianchi', 'Oltre XR4', 2021, '6.90', 'carbono', 'en uso', 7);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (23, 'mtb', 'Marin', 'Mount Vision', 2022, '13.20', 'aluminio', 'en uso', 8);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (24, 'gravel', 'Orbea', 'Terra', 2021, '9.00', 'aluminio', 'en uso', 9);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (25, 'road', 'Scott', 'Addict RC', 2022, '7.50', 'carbono', 'en uso', 10);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (26, 'mtb', 'Cube', 'Stereo Hybrid', 2021, '13.00', 'aluminio', 'en uso', 11);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (27, 'gravel', 'Felt', 'VR3', 2019, '10.10', 'carbono', 'en uso', 12);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (28, 'road', 'Bianchi', 'Oltre XR4', 2021, '6.90', 'carbono', 'en uso', 13);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (29, 'mtb', 'Cannondale', 'Jekyll 29', 2020, '14.00', 'carbono', 'en uso', 14);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (30, 'gravel', 'Trek', 'Checkpoint ALR 5', 2021, '10.30', 'aluminio', 'en uso', 15);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (31, 'gravel', 'Trek', 'X-Caliber', 2017, '12.00', 'aluminio', 'en uso', 16);
INSERT INTO `bicicletas` (`id_bici`, `tipo_bici`, `marca`, `modelo`, `anio`, `peso`, `material`, `status`, `id_usuario`) VALUES (37, 'mtb', 'Orbea', 'Oiz 2018 m20', 2018, '10.00', 'carbono', 'en uso', 16);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (1, 'Ruta muy dura, solo para usuarios expertos', '2024-10-10', 1, 5);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (2, 'Espectaculares vistas de la Sierra Norte de Madrid', '2019-05-02', 1, 2);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (3, 'Si realmente quieres sentir el ciclismo en Madrid tienes que hacer esta ruta, imprescindible', '2021-05-29', 1, 4);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (4, 'Recorrido muy técnico, ideal para los amantes del MTB', '2022-06-12', 2, 6);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (5, 'Una ruta muy bonita, aunque algo exigente en algunos tramos', '2021-09-15', 3, 7);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (6, 'Perfecta para un día soleado, me encantaron los paisajes', '2023-02-10', 4, 8);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (7, 'Desafiante pero muy gratificante, las vistas desde la cima son impresionantes', '2023-07-19', 5, 9);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (8, 'Ruta relajada, ideal para un paseo tranquilo', '2022-11-03', 6, 10);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (9, 'Excelente para principiantes, se disfruta mucho', '2024-03-25', 7, 11);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (10, 'Muy recomendable, aunque hay que estar preparado físicamente', '2023-04-20', 8, 12);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (11, 'El terreno es variado, me encantó la combinación de asfalto y caminos', '2022-08-22', 9, 13);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (12, 'Un recorrido increíble con vistas panorámicas', '2021-12-05', 10, 14);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (13, 'Un poco de todo, desde senderos fáciles hasta tramos difíciles', '2023-01-14', 11, 15);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (14, 'La ruta es fácil de seguir y muy agradable', '2023-06-25', 12, 16);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (15, 'Excelente experiencia para toda la familia', '2022-09-30', 13, 17);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (16, 'Un recorrido largo pero satisfactorio', '2023-03-15', 14, 18);
INSERT INTO `comentarios` (`id_comentario`, `comentario`, `fecha`, `id_usuario`, `id_actividad`) VALUES (17, 'Muy variada y divertida, me encantó', '2024-01-05', 15, 19);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (1, 'chuso1982@gmail.com', 1);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (2, 'jesusgonzalezblanco82@outlook.com', 1);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (3, 'anamg@example.com', 2);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (4, 'carloslopez@example.com', 3);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (5, 'laura_fer@example.com', 4);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (6, 'pedrosanchez@example.com', 5);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (7, 'davidromero@example.com', 6);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (8, 'sofia.perez@example.com', 7);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (9, 'manuelgarcia@example.com', 8);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (10, 'lucia.martinez@example.com', 9);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (11, 'miguel.torres@example.com', 10);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (12, 'alba.rodriguez@example.com', 11);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (13, 'javier.fernandez@example.com', 12);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (14, 'raquel.lopez@example.com', 13);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (15, 'luis.gonzalez@example.com', 14);
INSERT INTO `emails` (`id_email`, `email`, `id_usuario`) VALUES (16, 'cristina.sanchez@example.com', 15);
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (1, 'Triple Corona', '87.45', 1888, 'asfalto', 'Ruta de alta montaña por la sierra de Madrid en la que ascendemos los puertos de la Morcuera, Navafria y Canencia');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (2, 'Horizontal', '93.35', 834, 'montaña', 'Ruta por la cuerda Horizontal de la Sierra Norte, desde Somosierra a Navafria ida y vuelta');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (3, 'L\'etape 2023', '152.49', 2584, 'asfalto', 'Ruta muy dura por la Sierra de Gredos');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (4, 'El Caseton', '45.31', 631, '', 'Ruta por los caminos del canal hasta el Caseton del Canal');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (5, 'Triple Corona', '87.45', 1888, 'asfalto', 'Ruta de alta montaña por la sierra de Madrid en la que ascendemos los puertos de la Morcuera, Navafria y Canencia');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (6, 'Horizontal', '93.35', 834, 'montaña', 'Ruta por la cuerda Horizontal de la Sierra Norte, desde Somosierra a Navafria ida y vuelta');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (7, 'L\'etape 2023', '152.49', 2584, 'asfalto', 'Ruta muy dura por la Sierra de Gredos');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (8, 'El Caseton', '45.31', 631, 'mixto', 'Ruta por los caminos del canal hasta el Caseton del Canal');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (9, 'La Vuelta a la Comunidad', '112.50', 1500, 'asfalto', 'Ruta en la que se recorre toda la comunidad de Madrid por carreteras secundarias');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (10, 'Desierto de las Bardenas', '75.00', 500, 'mixto', 'Ruta por las desérticas llanuras de las Bardenas Reales');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (11, 'Sierra de Guadarrama', '120.30', 1800, 'montaña', 'Ruta exigente por la Sierra de Guadarrama, incluyendo algunos de los picos más altos');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (12, 'La Senda de Cervantes', '98.20', 900, 'asfalto', 'Ruta tranquila por caminos y carreteras secundarias, ideal para iniciados');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (13, 'Vía Verde del Tajuña', '75.00', 400, 'asfalto', 'Ruta muy fácil, ideal para todos los niveles a lo largo del río Tajuña');
INSERT INTO `rutas` (`id_ruta`, `nombre_ruta`, `distancia`, `desnivel`, `tipo_terreno`, `descripcion_ruta`) VALUES (14, 'El Camino del Cid', '150.00', 1600, 'mixto', 'Ruta larga por el interior de Castilla, siguiendo los pasos de El Cid');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (1, 'Jesus Gonzalez Blanco', '$2y$10$WylK6tNZNqnBHAya02ReOuAtNnio9bmHyEyLrJlA.A2Ckgie57Q7m', '1982-03-09', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (2, 'Ana Maria Gonzalez', '$2y$10$EnbOHfoIkpwDm2EYnLpAGeZZoeLtWWw.gEwZ7WMUoZVj3vDeuPqaa', '1990-05-12', 'inactivo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (3, 'Carlos Lopez', '$2y$10$/QH8ygp4lgsszzuSivHUc.FOzqRReZcsjhDzE27F34aaiOmwwRi0m', '1985-07-25', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (4, 'Laura Fernandez', '$2y$10$lVNJzFykw8vzIbA4EHatIOgF1KKr35vSg3/bVLTV1l9N5J2ntB5o2', '1992-01-18', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (5, 'Pedro Sanchez', '$2y$10$UGBeHmR/65XrdI6OYLE2iOlImk8a4pOXLOJiOmaxWb7CDJYucLsH6', '1988-09-30', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (6, 'David Romero', '$2y$10$IRw3wPwrOXKKatE73ADhPuJSZ6I9VlBRlkHsHqyFTiF8Q.Bd9tRHy', '1995-11-03', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (7, 'Sofia Perez', '$2y$10$7K4S6yTeRi5Nzb4cJ8HWa.QFGuYMVVXD8f4P45.vug9vWAAAHba5y', '1994-02-20', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (8, 'Manuel Garcia', '$2y$10$mVWlDrMztEGPaOA44Y8wv.mQmMWvo9F47S/lDIK55hd.EOKw2w0jK', '1983-08-14', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (9, 'Lucia Martinez', '$2y$10$o3U.mbsqKYtRpOGWgcCD4u6DvXGDRIv1vLa2EBTljNlI3STmGZ0G2', '1991-04-11', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (10, 'Miguel Torres', '$2y$10$imOZDYt8yVKIhO.5Mtq6fOjEKY0iV57CD665LFG17THDX8RHX65vq', '1987-10-23', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (11, 'Alba Rodriguez', '$2y$10$DQ8IaeMwbzo3E6u7yESlaOzP6qb34PJpcvZhy3OHgJNUhtEqSULk2', '1986-06-05', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (12, 'Javier Fernandez', '$2y$10$WBXN7kcNWQCTTMWRdFLjLuSaEijanlypHCjv7.ud5jveYiL/ynxl2', '1990-12-14', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (13, 'Raquel Lopez', '$2y$10$USYYhePcWOhUTzxHIu7WWuTNJSsqnCEveUVJ93gIgdlbIJMoLer.2', '1993-03-22', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (14, 'Luis Gonzalez', '$2y$10$OQaMqw0JQQa/0iZhKtgtfeW1THuO1VTT/kDuIyOBoxhgsE7iRpKLq', '1992-09-29', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (15, 'Cristina Sanchez', '$2y$10$XOVy3Bdy2Nw5knIs6XOWv.pD3rwyqca52PMPJ2ObbIxC.b.Xbde8.', '1990-04-16', 'inactivo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (16, 'Pepito Juarez', 'esto_es_una_pass_MUY_difiCil', '1995-01-02', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (17, 'Pepito Perez', 'esto_es_una_pass_MUY_difiCil', '1992-01-01', 'activo');
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `password`, `fecha_nac`, `status`) VALUES (18, 'Pepito Perez', 'esto_es_una_pass_MUY_difiCil', '1975-06-16', 'activo');
SET FOREIGN_KEY_CHECKS = 1;
ALTER TABLE `actividades` ADD CONSTRAINT `fk_actividades_bici` FOREIGN KEY (`id_bici`) REFERENCES `bicicletas` (`id_bici`) ON DELETE SET NULL ON UPDATE NO ACTION;
ALTER TABLE `actividades` ADD CONSTRAINT `fk_actividades_ruta` FOREIGN KEY (`id_ruta`) REFERENCES `rutas` (`id_ruta`) ON DELETE SET NULL ON UPDATE NO ACTION;
ALTER TABLE `actividades` ADD CONSTRAINT `fk_actividades_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL ON UPDATE NO ACTION;
ALTER TABLE `bicicletas` ADD CONSTRAINT `fk_bicicleta_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL ON UPDATE NO ACTION;
ALTER TABLE `comentarios` ADD CONSTRAINT `fk_comentario_actividad` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`) ON DELETE SET NULL ON UPDATE NO ACTION;
ALTER TABLE `comentarios` ADD CONSTRAINT `fk_comentario_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL ON UPDATE NO ACTION;
ALTER TABLE `emails` ADD CONSTRAINT `fk_email_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE `imagenes` ADD CONSTRAINT `fk_imagen_bici` FOREIGN KEY (`id_bici`) REFERENCES `bicicletas` (`id_bici`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE `imagenes` ADD CONSTRAINT `fk_imagen_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE `imagenes` ADD CONSTRAINT `imagenes_ibfk_1` FOREIGN KEY (`id_ruta`) REFERENCES `rutas` (`id_ruta`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE `imagenes` ADD CONSTRAINT `imagenes_ibfk_2` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`) ON DELETE CASCADE ON UPDATE NO ACTION;
