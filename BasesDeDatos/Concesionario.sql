set sql_safe_updates = 0;

create database Concesionario;
use Concesionario;

drop table if exists Serie;
CREATE TABLE Serie(
idSerie INT(5) primary key auto_increment,
marca VARCHAR(12),
modelo VARCHAR(17),
anio_fabricacion VARCHAR(4)
);

drop table if exists Car;
CREATE TABLE Car(
numBastidor VARCHAR(17) PRIMARY KEY,
matricula VARCHAR(7) NOT NULL,
colour VARCHAR(12) NOT NULL,
numPuertas INT(1) NOT NULL,
capacidadMaletero INT(3) NOT NULL,
numAsientos INT(1) NOT NULL,
precio int(5) NOT NULL,
idSerie INT(5) NOT NULL,
foreign key (idSerie) references Serie(idSerie)
);

drop table if exists Truck;
CREATE TABLE Truck(
numBastidor VARCHAR(17) PRIMARY KEY,
matricula VARCHAR(7) NOT NULL,
colour VARCHAR(12) NOT NULL,
carga INT(5) NOT NULL,
tipoMercancia VARCHAR(1) NOT NULL,
numAsientos INT(1) NOT NULL,
precio int(6),
idSerie INT(5) NOT NULL,
foreign key (idSerie) references Serie(idSerie)
);

drop table if exists Stock;
CREATE TABLE Stock(
tipoVehiculo VARCHAR(5) NOT NULL,
numBastidor VARCHAR(17),
matricula VARCHAR(7) NOT NULL,
colour VARCHAR(12) NOT NULL,
carga INT(5),
tipoMercancia VARCHAR(2),
numAsientos INT(1) NOT NULL,
numPuertas INT(1),
capacidadMaletero INT(3),
precio int(6) not null,
idSerie INT(5) NOT NULL,
repintado VARCHAR(3) NOT NULL,
PRIMARY KEY (NUMBASTIDOR, IDSERIE),
foreign key (idSerie) references Serie(idSerie)
);

drop table if exists Historic;
CREATE TABLE Historic(
tipoVehiculo VARCHAR(5) NOT NULL,
fecha date not null,
hora time not null,
accion VARCHAR(10) not null,
numBastidor VARCHAR(17) NOT NULL,
matricula VARCHAR(7) NOT NULL,
colour VARCHAR(12) NOT NULL,
carga INT(5),
tipoMercancia VARCHAR(1),
numAsientos INT(1) NOT NULL,
numPuertas INT(1),
capacidadMaletero INT(3),
precio int(6) not null,
idSerie INT(5) NOT NULL,
repintado VARCHAR(3),
PRIMARY KEY(FECHA, hora, accion, NUMBASTIDOR),
foreign key (idSerie) references Serie(idSerie)
); 

drop trigger if exists COMPRAR_COCHES;
DELIMITER |
CREATE TRIGGER COMPRAR_COCHES
AFTER INSERT ON Car 
FOR EACH ROW BEGIN
INSERT INTO historic (tipoVehiculo, fecha, hora, accion, numBastidor, matricula, colour, carga, tipoMercancia, numAsientos, numPuertas, capacidadMaletero, precio, idSerie, repintado)
values('CAR', curdate(), curtime(), 'BUY', new.numBastidor, new.matricula, new.colour, NULL, NULL, new.numAsientos, new.numPuertas, new.capacidadMaletero, new.precio, new.idSerie, 'NO');
end
|
DELIMITER ;

drop trigger if exists COMPRAR_CAMIONES;
DELIMITER |
CREATE TRIGGER COMPRAR_CAMIONES
AFTER INSERT ON Truck 
FOR EACH ROW BEGIN
INSERT INTO historic (tipoVehiculo, fecha, hora, accion, numBastidor, matricula, colour, carga, tipoMercancia, numAsientos, numPuertas, capacidadMaletero, precio, idSerie, repintado)
values('TRUCK', curdate(), curtime(), 'BUY', new.numBastidor, new.matricula, new.colour, new.carga, new.tipoMercancia, new.numAsientos, NULL, NULL, new.precio, new.idSerie, 'NO');
end
|
DELIMITER ;

drop trigger if exists VENDER_COCHES;
DELIMITER |
CREATE TRIGGER VENDER_COCHES
AFTER DELETE ON CAR 
FOR EACH ROW BEGIN
INSERT INTO historic VALUES('CAR', curdate(), curtime(), 'SELL', OLD.numBastidor, old.matricula, old.colour, NULL, NULL, old.numAsientos, old.numPuertas, old.capacidadMaletero, old.precio, old.idSerie, null);
end
|
DELIMITER ;

drop trigger if exists VENDER_CAMIONES;
DELIMITER |
CREATE TRIGGER VENDER_CAMIONES
AFTER DELETE ON TRUCK 
FOR EACH ROW BEGIN
INSERT INTO historic VALUES('TRUCK', curdate(), curtime(), 'SELL', OLD.numBastidor, OLD.matricula, OLD.colour, OLD.carga, OLD.tipoMercancia, OLD.numAsientos, NULL, NULL, OLD.precio, OLD.idSerie, NULL);
end
|
DELIMITER ;

drop trigger if exists MODIFICAR_COCHES;
DELIMITER |
CREATE TRIGGER MODIFICAR_COCHES
AFTER UPDATE ON CAR 
FOR EACH ROW BEGIN
if new.colour != old.colour then
	INSERT INTO historic VALUES('CAR', curdate(), curtime(), 'MODIFY', NEW.numBastidor, NEW.matricula, NEW.colour, NULL, NULL, NEW.numAsientos, NEW.numPuertas, NEW.capacidadMaletero, NEW.precio, NEW.idSerie, 'YES');
ELSE 
	INSERT INTO historic VALUES('CAR', curdate(), curtime(), 'MODIFY', NEW.numBastidor, NEW.matricula, NEW.colour, NULL, NULL, NEW.numAsientos, NEW.numPuertas, NEW.capacidadMaletero, NEW.precio, NEW.idSerie, 'NO');
end IF;
END;
|
DELIMITER ;

drop trigger if exists MODIFICAR_CAMIONES;
DELIMITER |
CREATE TRIGGER MODIFICAR_CAMIONES
AFTER UPDATE ON TRUCK 
FOR EACH ROW BEGIN
if new.colour != old.colour then 	
    INSERT INTO historic VALUES('TRUCK', curdate(), curtime(), 'MODIFY', NEW.numBastidor, NEW.matricula, NEW.colour, NEW.carga, NEW.tipoMercancia, NEW.numAsientos, NULL, NULL, NEW.precio, NEW.idSerie, 'YES');
else 
    INSERT INTO historic VALUES('TRUCK', curdate(), curtime(), 'MODIFY', NEW.numBastidor, NEW.matricula, NEW.colour, NEW.carga, NEW.tipoMercancia, NEW.numAsientos, NULL, NULL, NEW.precio, NEW.idSerie, 'NO');
END IF;
end
|
DELIMITER ;

DROP PROCEDURE IF EXISTS CLASSIFICATION_COLOURS;
DELIMITER ||
CREATE PROCEDURE CLASSIFICATION_COLOURS
(IN colour_CAR VARCHAR(12))
BEGIN
	SELECT MATRICULA, 'COCHE'
	FROM CAR
	WHERE COLOUR = COLOUR_CAR
    UNION
    SELECT MATRICULA, 'CAMION'
	FROM TRUCK
	WHERE COLOUR = colour_CAR;
END ||
DELIMITER ;

call classification_colours('BLUE');

DROP PROCEDURE IF EXISTS CLASSIFICATION_COLOURS_TRUCKS;
DELIMITER ||
CREATE PROCEDURE CLASSIFICATION_COLOURS_TRUCKS
(IN colour_TRUCK VARCHAR(12))
BEGIN
	SELECT *
	FROM TRUCK
	WHERE COLOUR = COLOUR_TRUCK;
END ||
DELIMITER ;
