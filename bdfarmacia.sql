-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2020 a las 01:52:58
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdfarmacia`
--
CREATE DATABASE IF NOT EXISTS `bdfarmacia` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bdfarmacia`;

DELIMITER $$
--
-- Procedimientos
--
CREATE  PROCEDURE `sp_actualizar_cliente` (IN `_cedula` INT(11), IN `_nombre` VARCHAR(30), IN `_telefonos` VARCHAR(20), IN `_direccion` VARCHAR(20))  BEGIN 

     UPDATE tblclientes 

	  SET nombre=_nombre,

	      telefonos=_telefonos,

			direccion=_direccion

	  WHERE cedula=_cedula;

END$$

CREATE  PROCEDURE `sp_actualizar_compra` (IN `_idcompra` INT(10), IN `_fechacompra` DATE)  BEGIN 
     UPDATE tblcompras
     SET fechacompra=_fechacompra
     	WHERE idcompra=_idcompra;
END$$

CREATE  PROCEDURE `sp_actualizar_detallecompra` (IN `_codcompra` INT(10), IN `_cantidadcompra` INT(11), IN `_preciocompra` FLOAT(11))  BEGIN 
     UPDATE tbldetallecompras
     SET cantidadcompra=_cantidadcompra,
         preciocompra=_preciocompra
     WHERE codcompra=_codcompra;
END$$

CREATE  PROCEDURE `sp_actualizar_imagen` (IN `_idimagen` INT(11), IN `_imagen` LONGBLOB, IN `_tipoimagen` VARCHAR(10), IN `_codusuario` INT(11))  BEGIN 
     UPDATE tblimagenes
     SET imagen=_imagen,
         tipoimagen=_tipoimagen,
         codusuario=_codusuario
     WHERE idimagen=_idimagen;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_imagenmedicamento` (IN `_idimagenmedicamento` INT, IN `_imagen` VARCHAR(260), IN `_tipoimagen` VARCHAR(10), IN `_codmedicamento` INT(11))  BEGIN 
     UPDATE tblimagenmedicamentos
     SET 
     		codmedicamento=_codmedicamento,
	  		imagen=_imagen,
         tipoimagen=_tipoimagen
     WHERE tblimagenmedicamentos.idimagenmedicamento=_idimagenmedicamento;
END$$

CREATE  PROCEDURE `sp_actualizar_laboratorio` (IN `_idlaboratorio` INT(10), IN `_nombrelaboratorio` VARCHAR(30), IN `_direccion` VARCHAR(30), IN `_telefonos` VARCHAR(20), IN `_email` VARCHAR(30))  BEGIN 

     UPDATE tbllaboratorios

     SET nombrelaboratorio=_nombrelaboratorio,

         direccion=_direccion,

			telefonos=_telefonos,

			email=_email

		WHERE  idlaboratorio=_idlaboratorio;

END$$

CREATE  PROCEDURE `sp_actualizar_medicamento` (IN `_idmedicamento` INT(10), IN `_nombre` VARCHAR(30), IN `_principioactivo` VARCHAR(30), IN `_grupofarmacologico` VARCHAR(260), IN `_codlaboratorio` INT(10), IN `_concentracion` VARCHAR(30), IN `_formafarmaceutica` VARCHAR(30), IN `_fechavencimiento` DATE, IN `_cantidadmedicamento` INT(8), IN `_preciounidad` DOUBLE, IN `_preciocompra` DOUBLE, IN `_seguimiento` VARCHAR(30))  BEGIN

     UPDATE tblmedicamentos

     SET nombre=_nombre,

         principioactivo=_principioactivo,

         grupofarmacologico=_grupofarmacologico,

			codlaboratorio=_codlaboratorio,

			concentracion=_concentracion,

			formafarmaceutica=_formafarmaceutica,

			fechavencimiento=_fechavencimiento,

			cantidadmedicamento=_cantidadmedicamento,

			preciounidad=_preciounidad,
			
			preciocompra=_preciocompra,

			seguimiento=_seguimiento

	  WHERE idmedicamento=_idmedicamento;

END$$

CREATE  PROCEDURE `sp_actualizar_usuario` (IN `_codempleado` INT(11), IN `_usuario` VARCHAR(40), IN `_contraseña` VARCHAR(20))  BEGIN 
     UPDATE tblusuarios
     SET usuario=_usuario,
         contraseña=MD5(_contraseña)
     WHERE codempleado=_codempleado;
END$$

CREATE  PROCEDURE `sp_actualizar_vendedor` (IN `_cc` INT(11), IN `_nombres` VARCHAR(30), IN `_apellidos` VARCHAR(30), IN `_cargo` VARCHAR(30), IN `_telefonos` VARCHAR(20), IN `_fechanacimiento` DATE, IN `_direccion` VARCHAR(30), IN `_correoelectronico` VARCHAR(30))  BEGIN 

     UPDATE tblvendedores

     SET nombres=_nombres,

         apellidos=_apellidos,

         cargo=_cargo,

			telefonos=_telefonos,

			fechanacimiento=_fechanacimiento,

			direccion=_direccion,

			correoelectronico=_correoelectronico

		WHERE cc=_cc;

END$$

CREATE  PROCEDURE `sp_atualizar_proveedor` (IN `_idproveedor` INT(11), IN `_nombre` VARCHAR(30), IN `_direccion` VARCHAR(20), IN `_telefonos` VARCHAR(20), IN `_email` VARCHAR(20))  BEGIN 

     UPDATE tblproveedores 

     SET nombre=_nombre,

         direccion=_direccion,

         telefonos=_telefonos,

         email=_email

     WHERE idproveedor=_idproveedor;

END$$

CREATE  PROCEDURE `sp_buscar_cliente` (IN `_cedula` INT(11))  BEGIN

     SELECT * FROM tblclientes WHERE cedula=_cedula;

END$$

CREATE  PROCEDURE `sp_buscar_compra` (IN `_codcompra` INT(10), IN `_codmedic` INT(10))  BEGIN 
      SELECT tblcompras.fechacompra,tblmedicamentos.idmedicamento, tbldetallecompras.cantidadcompra,tbldetallecompras.preciocompra
      FROM tbldetallecompras
      INNER JOIN tblcompras ON tblcompras.idcompra=_codcompra
      INNER JOIN tblmedicamentos ON tblmedicamentos.idmedicamento=_codmedic;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_id_imagenmedicamento` (IN `_codmedicamento` VARCHAR(50))  BEGIN 
	  SELECT tblimagenmedicamentos.idimagenmedicamento
	  FROM tblimagenmedicamentos
     WHERE tblimagenmedicamentos.codmedicamento=_codmedicamento;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_imagenmedicamento` (IN `_codimagen` INT(10))  BEGIN
		SELECT tblimagenmedicamentos.imagen
		FROM tblimagenmedicamentos
		WHERE tblimagenmedicamentos.codmedicamento=_codimagen;
END$$

CREATE  PROCEDURE `sp_buscar_imagenventa` (IN `_codusuario` INT(11))  BEGIN
     SELECT i.imagen
	  FROM tblimagenes as i
	  INNER JOIN tblusuarios as u ON u.codempleado=i.codusuario
	  WHERE i.codusuario=_codusuario;
END$$

CREATE  PROCEDURE `sp_buscar_laboratorio` (IN `_idlaboratorio` INT(10))  BEGIN

     SELECT * FROM tbllaboratorios WHERE idlaboratorio=_idlaboratorio;

END$$

CREATE  PROCEDURE `sp_buscar_medicamento` (IN `_idmedicamento` INT(10))  BEGIN

     SELECT * FROM tblmedicamentos WHERE idmedicamento=_idmedicamento;

END$$

CREATE  PROCEDURE `sp_buscar_proveedores` (IN `_idproveedor` INT(11))  BEGIN 

     SELECT * FROM tblproveedores WHERE idproveedor=_idproveedor;

END$$

CREATE  PROCEDURE `sp_buscar_vendedor` (IN `_cc` INT(11))  BEGIN 
     SELECT tblvendedores.cc,tblvendedores.nombres,tblvendedores.apellidos,tblvendedores.cargo,
     tblvendedores.telefonos,tblvendedores.fechanacimiento,tblvendedores.direccion,
     tblvendedores.correoelectronico,tblusuarios.usuario,tblusuarios.`contraseña`,tblusuarios.tipousuario,tblimagenes.imagen
     FROM tblusuarios
     INNER JOIN tblimagenes ON tblusuarios.codempleado=tblimagenes.codusuario
     INNER JOIN tblvendedores ON tblusuarios.codempleado=tblvendedores.cc
     WHERE tblusuarios.codempleado=_cc;
END$$

CREATE  PROCEDURE `sp_buscar_venta` (IN `_idventa` VARCHAR(6))  BEGIN

     SELECT * FROM tblventas WHERE idventa=_idventa;

END$$

CREATE  PROCEDURE `sp_eliminar_cliente` (IN `_cedula` INT(11))  BEGIN 

     DELETE FROM tblclientes WHERE cedula=_cedula;

END$$

CREATE  PROCEDURE `sp_eliminar_laboratorio` (IN `_idlaboratorio` INT(10))  BEGIN

     DELETE FROM tbllaboratorios WHERE idlaboratorio=_idlaboratorio;

END$$

CREATE  PROCEDURE `sp_eliminar_medicamento` (IN `_idmedicamento` INT(11))  BEGIN

     DELETE FROM tblmedicamentos WHERE idmedicamento=_idmedicamento;

END$$

CREATE  PROCEDURE `sp_eliminar_proveedor` (IN `_idproveedor` INT(11))  BEGIN 

     DELETE FROM tblproveedores WHERE idproveedor=_idproveedor;

END$$

CREATE  PROCEDURE `sp_eliminar_usuarios` (IN `_codempleado` INT(11))  BEGIN
     DELETE FROM tblusuarios WHERE codempleado=_codempleado;
END$$

CREATE  PROCEDURE `sp_eliminar_vendedor` (IN `_cc` INT(11))  BEGIN

     DELETE FROM tblvendedores WHERE cc=_cc;

END$$

CREATE  PROCEDURE `sp_guardar_clientes` (IN `_cedula` INT(11), IN `_nombre` VARCHAR(30), IN `_telefonos` VARCHAR(20), IN `_direccion` VARCHAR(20))  BEGIN 

     INSERT INTO tblclientes VALUES(_cedula,_nombre,_telefonos,_direccion);

END$$

CREATE  PROCEDURE `sp_guardar_compra` (IN `_idcompra` INT(10), IN `_fechacompra` DATE, IN `_codproveedor` INT(11))  BEGIN 

     INSERT INTO tblcompras VALUES(_idcompra,_fechacompra,_codproveedor);

END$$

CREATE  PROCEDURE `sp_guardar_detalle` (IN `_codventa` VARCHAR(6), IN `_codmedicamento` INT(10), IN `_cantidad` INT(11), IN `_preciounidad` DOUBLE)  BEGIN 

     INSERT INTO tbldetalleventas VALUES (_codventa,_codmedicamento,_cantidad,_preciounidad);

END$$

CREATE  PROCEDURE `sp_guardar_detallecompra` (IN `_codcompra` INT(10), IN `_codmedic` INT(10), IN `_cantidadcompra` INT(11), IN `_preciocompra` FLOAT)  BEGIN

     INSERT INTO tbldetallecompras VALUES(_codcompra,_codmedic,_cantidadcompra,_preciocompra);

END$$

CREATE  PROCEDURE `sp_guardar_imagen` (IN `_idimagen` INT(11), IN `_imagen` LONGBLOB, IN `_tipoimagen` VARCHAR(50), IN `_codusuario` INT(11))  BEGIN
     INSERT INTO tblimagenes VALUES(_idimagen,_imagen,_tipoimagen,_codusuario);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_guardar_imagenmedicamento` (IN `_idimagenmedicamento` INT(11), IN `_imagen` VARCHAR(260), IN `_tipoimagen` VARCHAR(30), IN `_codimagen` INT(10))  BEGIN
		INSERT INTO tblimagenmedicamentos VALUES(_idimagenmedicamento, _imagen,_tipoimagen,_codimagen);
END$$

CREATE  PROCEDURE `sp_guardar_laboratorios` (IN `_idlaboratorio` INT(10), IN `_nombrelaboratorio` VARCHAR(30), IN `_direccion` VARCHAR(30), IN `_telefonos` VARCHAR(20), IN `_email` VARCHAR(30))  BEGIN 

     INSERT INTO tbllaboratorios

	  VALUES (_idlaboratorio,_nombrelaboratorio,_direccion,_telefonos,_email);

END$$

CREATE  PROCEDURE `sp_guardar_medicamento` (IN `_idmedicamento` INT(10), IN `_nombre` VARCHAR(30), IN `_principioactivo` VARCHAR(30), IN `_grupofarmacologico` VARCHAR(260), IN `_codlaboratorio` INT(10), IN `_concentracion` VARCHAR(30), IN `_formafarmaceutica` VARCHAR(30), IN `_fechavencimiento` DATE, IN `_cantidadmedicamento` INT(8), IN `_preciounidad` DOUBLE, IN `_preciocompra` DOUBLE, IN `_seguimiento` VARCHAR(30))  BEGIN

     INSERT INTO tblmedicamentos VALUES

     (_idmedicamento,_nombre,_principioactivo,_grupofarmacologico,_codlaboratorio,_concentracion,_formafarmaceutica,

	  _fechavencimiento,_cantidadmedicamento,_preciounidad,_preciocompra,_seguimiento);

END$$

CREATE  PROCEDURE `sp_guardar_proveedor` (IN `_idproveedor` INT(11), IN `_nombre` VARCHAR(30), IN `_direccion` VARCHAR(20), IN `_telefonos` VARCHAR(20), IN `_email` VARCHAR(20))  BEGIN

     INSERT INTO tblproveedores VALUES(_idproveedor,_nombre,_direccion,_telefonos,_email);

END$$

CREATE  PROCEDURE `sp_guardar_usuario` (IN `_codempleado` INT(11), IN `_usuario` VARCHAR(40), IN `_contraseña` VARCHAR(20), IN `_tipousuario` VARCHAR(20))  BEGIN 
     INSERT INTO tblusuarios VALUES(_codempleado,_usuario,md5(_contraseña),_tipousuario);
END$$

CREATE  PROCEDURE `sp_guardar_vendedor` (IN `_cc` INT(11), IN `_nombres` VARCHAR(30), IN `_apellidos` VARCHAR(30), IN `_cargo` VARCHAR(30), IN `_telefonos` VARCHAR(20), IN `_fechanacimiento` DATE, IN `_direccion` VARCHAR(30), IN `_correoelectronico` VARCHAR(30))  BEGIN

    INSERT INTO tblvendedores VALUES(_cc,_nombres,_apellidos,_cargo,_telefonos,_fechanacimiento,_direccion,_correoelectronico);

END$$

CREATE  PROCEDURE `sp_guardar_venta` (IN `_idventa` VARCHAR(6), IN `_fechaventa` DATE, IN `_codvendedor` INT(11), IN `_codcliente` INT(11))  BEGIN

     INSERT INTO tblventas VALUES (_idventa,_fechaventa,_codvendedor,_codcliente);

END$$

CREATE  PROCEDURE `sp_iniciar_sesion` (IN `_usuario` VARCHAR(40), IN `_contraseña` VARCHAR(20))  BEGIN
     SELECT tblvendedores.nombres,tblvendedores.apellidos,tblusuarios.usuario,tblusuarios.`contraseña`,tblusuarios.tipousuario,tblvendedores.cc
	  FROM tblusuarios
	  INNER JOIN tblvendedores ON tblvendedores.cc=tblusuarios.codempleado
	  WHERE tblusuarios.usuario=_usuario 
	  AND tblusuarios.`contraseña`=MD5(_contraseña);
END$$

CREATE  PROCEDURE `sp_seguimiento_med` ()  BEGIN 

     UPDATE tblmedicamentos 

     SET seguimiento=IF(DATEDIFF(fechavencimiento,CURDATE())>91,'BIEN',

	                  IF(DATEDIFF(fechavencimiento,CURDATE())>61,'ESTAR PENDIENTE',

							IF(DATEDIFF(fechavencimiento,CURDATE())>31,'SACAR EL PROXIMO MES',

							IF(DATEDIFF(fechavencimiento,CURDATE())>=0,'SACAR DE LOS ESTANTES','VENCIDO'

							))));

END$$

--
-- Funciones
--
CREATE  FUNCTION `sf_total` (`_codventa` VARCHAR(6)) RETURNS FLOAT BEGIN

     DECLARE total INT(8) DEFAULT 0;

     SET total=(SELECT SUM(tbldetalleventas.cantidad*tbldetalleventas.preciounidad) FROM tbldetalleventas WHERE tbldetalleventas.codventa=_codventa);

RETURN total;

END$$

CREATE  FUNCTION `sf_totalventapordia` (`_fechaventa` DATE) RETURNS DOUBLE BEGIN 

     DECLARE totalventa DOUBLE DEFAULT 0;

     SET totalventa=(SELECT SUM(tbldetalleventas.cantidad*tbldetalleventas.preciounidad) as totaldia 

	                 FROM tbldetalleventas 

						  INNER JOIN tblventas ON tblventas.idventa=tbldetalleventas.codventa 

						  WHERE tblventas.fechaventa=_fechaventa);

RETURN totalventa;

END$$

CREATE  FUNCTION `sf_totalventaporperiodo` (`_fechainicial` DATE, `_fechafinal` DATE) RETURNS FLOAT BEGIN 

     DECLARE totalperiodo FLOAT DEFAULT 0;

     SET totalperiodo=(SELECT SUM(tbldetalleventas.cantidad*tbldetalleventas.preciounidad) AS Totalventaperiodo

                       FROM tbldetalleventas

                       INNER JOIN tblventas ON tblventas.idventa=tbldetalleventas.codventa

                       WHERE tblventas.fechaventa BETWEEN _fechainicial AND _fechafinal);

      IF totalperiodo=(null) THEN

      SET totalperiodo=0;

      END IF;

RETURN totalperiodo;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblclientes`
--

CREATE TABLE `tblclientes` (
  `cedula` int(11) UNSIGNED NOT NULL DEFAULT '0',
  `nombre` varchar(30) NOT NULL DEFAULT '',
  `telefonos` varchar(20) NOT NULL DEFAULT '',
  `direccion` varchar(20) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblclientes`
--

INSERT INTO `tblclientes` (`cedula`, `nombre`, `telefonos`, `direccion`) VALUES
(106, 'ESPORADICO', '3015173066', 'CLL 2 N°3- 4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcompras`
--

CREATE TABLE `tblcompras` (
  `idcompra` int(10) UNSIGNED NOT NULL,
  `fechacompra` date NOT NULL,
  `codproveedor` int(11) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblcompras`
--

INSERT INTO `tblcompras` (`idcompra`, `fechacompra`, `codproveedor`) VALUES
(1, '2020-06-21', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbldetallecompras`
--

CREATE TABLE `tbldetallecompras` (
  `codcompra` int(10) UNSIGNED NOT NULL,
  `codmedic` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `cantidadcompra` int(11) NOT NULL DEFAULT '0',
  `preciocompra` float NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tbldetallecompras`
--

INSERT INTO `tbldetallecompras` (`codcompra`, `codmedic`, `cantidadcompra`, `preciocompra`) VALUES
(1, 1, 40, 150),
(1, 2, 40, 1900);

--
-- Disparadores `tbldetallecompras`
--
DELIMITER $$
CREATE TRIGGER `tg_aumentar_cantidad` AFTER INSERT ON `tbldetallecompras` FOR EACH ROW BEGIN 
     UPDATE tblmedicamentos
     SET cantidadmedicamento=cantidadmedicamento+NEW.cantidadcompra
     WHERE idmedicamento=NEW.codmedic;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbldetalleventas`
--

CREATE TABLE `tbldetalleventas` (
  `codventa` varchar(6) NOT NULL DEFAULT '',
  `codmedicamento` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `cantidad` int(11) NOT NULL DEFAULT '0',
  `preciounidad` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tbldetalleventas`
--

INSERT INTO `tbldetalleventas` (`codventa`, `codmedicamento`, `cantidad`, `preciounidad`) VALUES
('CD0001', 1, 10, 200);

--
-- Disparadores `tbldetalleventas`
--
DELIMITER $$
CREATE TRIGGER `tg_disminuir_cantidad` AFTER INSERT ON `tbldetalleventas` FOR EACH ROW BEGIN
    UPDATE tblmedicamentos
    SET cantidadmedicamento=cantidadmedicamento-NEW.cantidad
    WHERE idmedicamento=NEW.codmedicamento;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblimagenes`
--

CREATE TABLE `tblimagenes` (
  `idimagen` int(11) NOT NULL,
  `imagen` varchar(250) NOT NULL,
  `tipoimagen` varchar(50) NOT NULL,
  `codusuario` int(11) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblimagenes`
--

INSERT INTO `tblimagenes` (`idimagen`, `imagen`, `tipoimagen`, `codusuario`) VALUES
(1, 'recursos\\vendedores\\1avatar.jpg', 'jpg', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblimagenmedicamentos`
--

CREATE TABLE `tblimagenmedicamentos` (
  `idimagenmedicamento` int(11) NOT NULL,
  `imagen` varchar(260) NOT NULL,
  `tipoimagen` varchar(30) NOT NULL,
  `codmedicamento` int(10) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblimagenmedicamentos`
--

INSERT INTO `tblimagenmedicamentos` (`idimagenmedicamento`, `imagen`, `tipoimagen`, `codmedicamento`) VALUES
(6, 'recursos\\medicamentos\\1592778321582acetaminofen-tab-500.jpg', 'jpg', 1),
(7, 'recursos\\medicamentos\\1592778764586verelis.jpg', 'jpg', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbllaboratorios`
--

CREATE TABLE `tbllaboratorios` (
  `idlaboratorio` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `nombrelaboratorio` varchar(30) NOT NULL DEFAULT '',
  `direccion` varchar(30) NOT NULL DEFAULT '',
  `telefonos` varchar(20) NOT NULL DEFAULT '',
  `email` varchar(30) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tbllaboratorios`
--

INSERT INTO `tbllaboratorios` (`idlaboratorio`, `nombrelaboratorio`, `direccion`, `telefonos`, `email`) VALUES
(1, 'GSK ', 'CLL 2 N° 3-5 ', '676437', 'INFORMACION1234@GSK.COM'),
(2, 'MK', 'AVENIDA OCCIDENTE ', '323323', 'CLIENTES@MK.COM'),
(3, 'ABBOTT ', 'CL 100 9 A-45 P-14 BOG', '6285600', 'INFORMACION@ABBOTT.COM.CO'),
(4, 'BAYER S.A', 'CR  58 N°10 - 76', '4142277', 'BAYERINFORMES@GMAIL.COM'),
(5, 'BUSSIE', 'DG 19 D 39-41', '3351135 ', 'BUSSIE123@GMAIL.COM'),
(6, 'COASPHARMA', 'CALLE: 18A NO 28A-43', '3351000', 'COASPHARMA_SA@GMAIL.COM'),
(7, 'ECAR', 'R44 27-50 MEDELLÍN, COLOMBIA', '4483227', 'ECAR123@GMAIL.COM'),
(8, 'GARMISCH', 'CALLE 100 # 19A-10 OFC. 301 ', '01 8000 184 847', 'INFO@GARMISCH.COM'),
(9, 'HUMAX', 'CALLE 97B SUR NO. 50 - 95', '(4)444 8629 ', 'INFORMACION@HUMAX.COM'),
(10, 'LA SANTE VITAL', 'AV. CALLE 24 # 95-12 BODEGA 25', '018000118899', 'INFO@LASANTE.COM'),
(11, 'MERCK S.A', 'CARRERA 9 #101 - 67, PISO 5 ', '4254770', 'INFO@MERCK.COM'),
(12, 'NATURAL FRESHLY', 'VÍA COTA CHÍA KM 1 VDA', '5951313', 'INFO@NATURAL.COM'),
(13, 'NESTLE', 'DG92 17 A-42', '4946556', 'INFO@NESTLE.COM'),
(14, 'NOVAMED', 'CL 79 B 78 C-21', '3304200', 'INFO@NOVAMED.COM'),
(15, 'NOVARTIS', 'CALLE 93B N° 16-31', '018000916029', 'PRIMERO.ELCLIENTE@NOVARTIS.COM'),
(16, 'PFIZER', 'AV SUBA 95-66', '6002300', 'BOG-INFOMEDICA@PFIZER.COM'),
(17, 'PROCAPS', 'CL. 80 #78B-201', '3719000', 'INFOR@PROCAPS.COM'),
(18, 'ROCHE', 'CR44 20-21 BOGOTÁ', '1 417 8860', 'INFO@ROCHE.COM'),
(19, 'SANOFI AVENTIS', 'TR23 97-73 P 9', '6214400', 'INFO@SANOFIAVENTIS.COM'),
(20, 'SIEGFRIED', 'CL 17 42-09 BOGOTÁ', ' 2086262', 'INFO@SIEGFRIED.COM'),
(21, 'VITALIS', 'CL 98 CR51 ESQ L-105', '3112010', 'INFO@VITALIS.COM'),
(22, 'ICOM', 'CLL 2N 3-107', '71276124', 'INFORMES@ICOM.COM'),
(23, 'JHOINPHARMA', 'CLL 11- N° 2 30', '123214', 'ASUNTOS@JHOINPHARMA.COM'),
(24, 'BIOQUIFAR', 'CR 37 #25 - 98 ', '2686887', 'ASESORIA@BIOQUIFAR.SA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblmedicamentos`
--

CREATE TABLE `tblmedicamentos` (
  `idmedicamento` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `nombre` varchar(30) NOT NULL DEFAULT '',
  `principioactivo` varchar(30) NOT NULL DEFAULT '',
  `grupofarmacologico` varchar(260) NOT NULL DEFAULT '',
  `codlaboratorio` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `concentracion` varchar(30) NOT NULL DEFAULT '',
  `formafarmaceutica` varchar(30) NOT NULL DEFAULT '',
  `fechavencimiento` date NOT NULL,
  `cantidadmedicamento` int(8) NOT NULL DEFAULT '0',
  `preciounidad` double NOT NULL,
  `preciocompra` double NOT NULL,
  `seguimiento` varchar(30) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblmedicamentos`
--

INSERT INTO `tblmedicamentos` (`idmedicamento`, `nombre`, `principioactivo`, `grupofarmacologico`, `codlaboratorio`, `concentracion`, `formafarmaceutica`, `fechavencimiento`, `cantidadmedicamento`, `preciounidad`, `preciocompra`, `seguimiento`) VALUES
(1, 'ACETAMINOFEN', 'PARACENTAMOL', 'ANTIINFLAMATORIOS NO ESTEROIDEOS (AINE)', 2, '500 MG', 'TABLETAS', '2024-06-21', 30, 0, 0, 'BIEN'),
(2, 'VERELIS', 'TADALAFILO', 'INHIBIDOR SELECTIVO DE LA FOSFODIESTERASA-5', 1, '20 MG', 'TABLETAS', '2022-06-01', 40, 0, 0, 'BIEN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblproveedores`
--

CREATE TABLE `tblproveedores` (
  `idproveedor` int(11) UNSIGNED NOT NULL DEFAULT '0',
  `nombre` varchar(30) NOT NULL DEFAULT '',
  `direccion` varchar(20) NOT NULL DEFAULT '',
  `telefonos` varchar(20) NOT NULL DEFAULT '',
  `email` varchar(20) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblproveedores`
--

INSERT INTO `tblproveedores` (`idproveedor`, `nombre`, `direccion`, `telefonos`, `email`) VALUES
(0, 'INVENTARIO INICIAL', 'INVENTARIO INICIAL', 'INVENTARIO INICIAL', 'INVENTARIO INICIAL'),
(1, 'LA REBAJA', 'CLL 2 N 123', '7872732', 'LAREBAJA@COM.CO'),
(2, 'ETICO SERRANO GOMEZ', 'TRONCAL DE OCCIDENTE', '737344', 'REGITROS@ETICO.COM'),
(3, 'COPI DROGAS', 'TRONCAL DE OCCIDENTE', '21553', 'COPIDROGAS@GMAIL.COM');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblusuarios`
--

CREATE TABLE `tblusuarios` (
  `codempleado` int(11) UNSIGNED NOT NULL DEFAULT '0',
  `usuario` varchar(40) NOT NULL DEFAULT '',
  `contraseña` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `tipousuario` varchar(20) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblusuarios`
--

INSERT INTO `tblusuarios` (`codempleado`, `usuario`, `contraseña`, `tipousuario`) VALUES
(1, 'ADMIN@GENESIS.COM', 'c4ca4238a0b923820dcc509a6f75849b', 'ADMINISTRADOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblvendedores`
--

CREATE TABLE `tblvendedores` (
  `cc` int(11) UNSIGNED NOT NULL DEFAULT '0',
  `nombres` varchar(30) NOT NULL DEFAULT '',
  `apellidos` varchar(30) NOT NULL DEFAULT '',
  `cargo` varchar(30) NOT NULL DEFAULT '',
  `telefonos` varchar(20) NOT NULL DEFAULT '',
  `fechanacimiento` date NOT NULL,
  `direccion` varchar(30) NOT NULL DEFAULT '',
  `correoelectronico` varchar(30) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblvendedores`
--

INSERT INTO `tblvendedores` (`cc`, `nombres`, `apellidos`, `cargo`, `telefonos`, `fechanacimiento`, `direccion`, `correoelectronico`) VALUES
(1, 'ALDAIR', 'DE HOYOS TERAN', 'QUIMICO FARMACEUTICO', '3015173066', '1998-04-01', 'CLL FALSA 1 2 3', 'DEHOYOS9804@GMAIL.COM');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblventas`
--

CREATE TABLE `tblventas` (
  `idventa` varchar(6) NOT NULL DEFAULT '',
  `fechaventa` date NOT NULL,
  `codvendedor` int(11) UNSIGNED NOT NULL DEFAULT '0',
  `codcliente` int(11) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblventas`
--

INSERT INTO `tblventas` (`idventa`, `fechaventa`, `codvendedor`, `codcliente`) VALUES
('CD0001', '2020-06-21', 1, 106);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tblclientes`
--
ALTER TABLE `tblclientes`
  ADD PRIMARY KEY (`cedula`);

--
-- Indices de la tabla `tblcompras`
--
ALTER TABLE `tblcompras`
  ADD PRIMARY KEY (`idcompra`),
  ADD KEY `codproveedor` (`codproveedor`);

--
-- Indices de la tabla `tbldetallecompras`
--
ALTER TABLE `tbldetallecompras`
  ADD PRIMARY KEY (`codcompra`,`codmedic`),
  ADD KEY `codmedicamentos` (`codmedic`);

--
-- Indices de la tabla `tbldetalleventas`
--
ALTER TABLE `tbldetalleventas`
  ADD PRIMARY KEY (`codventa`,`codmedicamento`),
  ADD KEY `codmedicamento` (`codmedicamento`);

--
-- Indices de la tabla `tblimagenes`
--
ALTER TABLE `tblimagenes`
  ADD PRIMARY KEY (`idimagen`),
  ADD KEY `codusuario` (`codusuario`);

--
-- Indices de la tabla `tblimagenmedicamentos`
--
ALTER TABLE `tblimagenmedicamentos`
  ADD PRIMARY KEY (`idimagenmedicamento`),
  ADD KEY `codmedicamento` (`codmedicamento`);

--
-- Indices de la tabla `tbllaboratorios`
--
ALTER TABLE `tbllaboratorios`
  ADD PRIMARY KEY (`idlaboratorio`);

--
-- Indices de la tabla `tblmedicamentos`
--
ALTER TABLE `tblmedicamentos`
  ADD PRIMARY KEY (`idmedicamento`),
  ADD KEY `codlaboratorio` (`codlaboratorio`);

--
-- Indices de la tabla `tblproveedores`
--
ALTER TABLE `tblproveedores`
  ADD PRIMARY KEY (`idproveedor`);

--
-- Indices de la tabla `tblusuarios`
--
ALTER TABLE `tblusuarios`
  ADD PRIMARY KEY (`codempleado`);

--
-- Indices de la tabla `tblvendedores`
--
ALTER TABLE `tblvendedores`
  ADD PRIMARY KEY (`cc`);

--
-- Indices de la tabla `tblventas`
--
ALTER TABLE `tblventas`
  ADD PRIMARY KEY (`idventa`),
  ADD KEY `codvendedor` (`codvendedor`),
  ADD KEY `codcliente` (`codcliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tblcompras`
--
ALTER TABLE `tblcompras`
  MODIFY `idcompra` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tbldetallecompras`
--
ALTER TABLE `tbldetallecompras`
  MODIFY `codcompra` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tblimagenes`
--
ALTER TABLE `tblimagenes`
  MODIFY `idimagen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tblimagenmedicamentos`
--
ALTER TABLE `tblimagenmedicamentos`
  MODIFY `idimagenmedicamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tblcompras`
--
ALTER TABLE `tblcompras`
  ADD CONSTRAINT `tblcompras_ibfk_1` FOREIGN KEY (`codproveedor`) REFERENCES `tblproveedores` (`idproveedor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tbldetallecompras`
--
ALTER TABLE `tbldetallecompras`
  ADD CONSTRAINT `tbldetallecompras_ibfk_1` FOREIGN KEY (`codcompra`) REFERENCES `tblcompras` (`idcompra`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbldetallecompras_ibfk_2` FOREIGN KEY (`codmedic`) REFERENCES `tblmedicamentos` (`idmedicamento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tbldetalleventas`
--
ALTER TABLE `tbldetalleventas`
  ADD CONSTRAINT `tbldetalleventas_ibfk_1` FOREIGN KEY (`codmedicamento`) REFERENCES `tblmedicamentos` (`idmedicamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbldetalleventas_ibfk_2` FOREIGN KEY (`codventa`) REFERENCES `tblventas` (`idventa`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tblimagenes`
--
ALTER TABLE `tblimagenes`
  ADD CONSTRAINT `tblimagenes_ibfk_1` FOREIGN KEY (`codusuario`) REFERENCES `tblusuarios` (`codempleado`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tblimagenmedicamentos`
--
ALTER TABLE `tblimagenmedicamentos`
  ADD CONSTRAINT `tblimagenmedicamentos_ibfk_1` FOREIGN KEY (`codmedicamento`) REFERENCES `tblmedicamentos` (`idmedicamento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tblmedicamentos`
--
ALTER TABLE `tblmedicamentos`
  ADD CONSTRAINT `tblmedicamentos_ibfk_1` FOREIGN KEY (`codlaboratorio`) REFERENCES `tbllaboratorios` (`idlaboratorio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tblusuarios`
--
ALTER TABLE `tblusuarios`
  ADD CONSTRAINT `tblusuarios_ibfk_1` FOREIGN KEY (`codempleado`) REFERENCES `tblvendedores` (`cc`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tblventas`
--
ALTER TABLE `tblventas`
  ADD CONSTRAINT `tblventas_ibfk_1` FOREIGN KEY (`codvendedor`) REFERENCES `tblvendedores` (`cc`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tblventas_ibfk_2` FOREIGN KEY (`codcliente`) REFERENCES `tblclientes` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

ALTER TABLE tblcompras AUTO_INCREMENT = 0;
ALTER TABLE tblimagenes AUTO_INCREMENT = 0;
ALTER TABLE tblimagenmedicamentos AUTO_INCREMENT = 0;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
