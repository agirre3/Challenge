create database Concesionario;
use Concesionario;

drop table if exists Serie;
CREATE TABLE Serie(
idSerie INT(5) primary key auto_increment,
marca VARCHAR(12),
modelo VARCHAR(17),
anio_fabricacion int(4)
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



INSERT INTO CAR VALUES('11122231132', '5555asd','Green', 5, 200, 5, 2000, 1);

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
numBastidor VARCHAR(17) PRIMARY KEY,
matricula VARCHAR(7) NOT NULL,
colour VARCHAR(12) NOT NULL,
carga INT(5),
tipoMercancia VARCHAR(1),
numAsientos INT(1) NOT NULL,
numPuertas INT(1),
capacidadMaletero INT(3),
precio int(6),
idSerie INT(5) NOT NULL,
foreign key (idSerie) references Serie(idSerie)
);

drop table if exists Historic;
CREATE TABLE Historic(
fecha date not null,
accion VARCHAR(10) not null,
numBastidor VARCHAR(17) PRIMARY KEY,
matricula VARCHAR(7) NOT NULL,
colour VARCHAR(12) NOT NULL,
carga INT(5),
tipoMercancia VARCHAR(1),
numAsientos INT(1) NOT NULL,
numPuertas INT(1),
capacidadMaletero INT(3),
precio int(6),
idSerie INT(5) NOT NULL,
foreign key (idSerie) references Serie(idSerie)
); 

drop trigger if exists COMPRAR_COCHES;
DELIMITER |
CREATE TRIGGER COMPRAR_COCHES
AFTER INSERT ON Car 
FOR EACH ROW BEGIN
INSERT INTO Stock (numBastidor, matricula, colour, carga, tipoMercancia, numAsientos, numPuertas, capacidadMaletero, precio, idSerie) 
VALUES(new.numBastidor, new.matricula, new.colour, NULL, NULL, new.numAsientos, new.numPuertas, new.capacidadMaletero, new.precio, new.idSerie);
INSERT INTO historic (fecha, accion, numBastidor, matricula, colour, carga, tipoMercancia, numAsientos, numPuertas, capacidadMaletero, precio, idSerie)
values(curdate(), 'BUY', new.numBastidor, new.matricula, new.colour, NULL, NULL, new.numAsientos, new.numPuertas, new.capacidadMaletero, new.precio);
end
|
DELIMITER ;

drop trigger if exists COMPRAR_CAMIONES;
DELIMITER |
CREATE TRIGGER COMPRAR_CAMIONES
AFTER INSERT ON Truck 
FOR EACH ROW BEGIN
INSERT INTO Stock VALUES(new.numBastidor, new.matricula, new.colour,  new.carga, new.tipoMercancia, new.numAsientos, new.precio);
INSERT INTO historic VALUES(curdate(), 'BUY', new.numBastidor, new.matricula, new.colour,  new.carga, new.tipoMercancia, new.numAsientos, new.precio);
end
|
DELIMITER ;

drop trigger if exists VENDER_COCHES;
DELIMITER |
CREATE TRIGGER VENDER_COCHES
AFTER DELETE ON CAR 
FOR EACH ROW BEGIN
DELETE FROM Stock WHERE numBastidor = old.numBastidor;
INSERT INTO historic VALUES(curdate(), 'SELL', numBastidor, matricula, colour, numAsientos, numPuertas, capacidadMaletero, precio);
end
|
DELIMITER ;

drop trigger if exists VENDER_CAMIONES;
DELIMITER |
CREATE TRIGGER VENDER_CAMIONES
AFTER DELETE ON TRUCK 
FOR EACH ROW BEGIN
DELETE FROM Stock WHERE numBastidor = old.numBastidor;
INSERT INTO historic VALUES(curdate(), 'SELL', numBastidor, matricula, colour,  carga, tipoMercancia, numAsientos, precio);
end
|
DELIMITER ;

insert into serie values( 0,"Ford", "Fiesta", 1999);
insert into serie values( 0,"Ford", "Fiesta", 2000);

INSERT INTO CAR VALUES('11122231132', '5555asd','Green', 5, 200, 5, 2000, 1);


DELETE FROM CAR WHERE numBastidor = 11122231132;