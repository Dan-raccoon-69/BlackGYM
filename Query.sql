CREATE DATABASE GYM;
use GYm;
show databases;
CREATE TABLE socios (
    fol INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(50),
    Eda VARCHAR(2),
    Tel VARCHAR(10),
    CorElec VARCHAR(50),
    Cal VARCHAR(50),
    Num INT,
    Col VARCHAR(50),
    Cp VARCHAR(6),
    Ent VARCHAR(50),
    Est VARCHAR(50),
    NumPlan INT,
    Inp DATE,
    FiP DATE,
    FOREIGN KEY (NumPlan) REFERENCES Planes(NumPlan)
);
update socios set Inp = '2023-12-10', FiP = '2024-01-10' where NumPlan = 6;
select * from socios;
select * from socios order by fol asc;
drop table socios;
use gym;
select * from socios where (Nom like ? or ApePa like ?);
select * from socios where (Nom like 'juan perez') order by fol asc;
SELECT * FROM socios WHERE Nom LIKE '%juan perez%' ORDER BY fol ASC;

INSERT INTO socios (Nom, Eda, Tel, CorElec, Cal, Num, Col, Cp, Ent, Est, NumPlan, Inp, FiP)
VALUES ('Juan Perez Martinez', '25', 5517182030, 'MARTo12@example.com', 'Sanchez', 123, 'Col olimpica', '55040', 'Mexico', 'Mexico', 1, '2023-11-01', '2024-11-01');
INSERT INTO socios (Nom, Eda, Tel, CorElec, Cal, Num, Col, Cp, Ent, Est, NumPlan, Inp, FiP)
VALUES ('Mariana Rodriguez Guerrero', '23', 5512658510, 'MARIA23@Gmail.com', 'Resina', 123, 'Col olimpica', '565047', 'Mexico', 'Mexico', 1, '2023-06-18', '2024-06-18');
INSERT INTO socios (Nom, Eda, Tel, CorElec, Cal, Num, Col, Cp, Ent, Est, NumPlan, Inp, FiP)
VALUES ('Avril Gonzale Martinez', '21', 5566887744, 'Avg12@yahoo.com', 'Te', 123, 'Col olimpica', '55041', 'Mexico', 'Mexico', 2, '2023-11-26', '2023-12-26');
INSERT INTO socios (Nom, Eda, Tel, CorElec, Cal, Num, Col, Cp, Ent, Est, NumPlan, Inp, FiP)
VALUES ('Paola Garza Rodriguez', '25', 5500112244, 'Paol4@outlook.com', 'Pedregal', 123, 'Col alpha', '014589', 'Mexico', 'Mexico', 3 ,'2023-06-26', '2023-09-26');
INSERT INTO socios (Nom, Eda, Tel, CorElec, Cal, Num, Col, Cp, Ent, Est, NumPlan, Inp, FiP)
VALUES ('Carlos Chagolla Hernandez', '33', 5517182032, 'CCHO32@outloo.com', 'Sanchez', 123, 'Nogal', '551636', 'Guanajuato', 'GTO', 1, '2023-11-11', '2024-11-11');
INSERT INTO socios (Nom, Eda, Tel, CorElec, Cal, Num, Col, Cp, Ent, Est, NumPlan, Inp, FiP)
VALUES ('Daniel Gustavo De La Cruz Bautista', '21', 5512453095, 'gustavodlc21@gmail.com', 'Ramon', 7, 'Texalpa', '55123', 'Mexico', 'Mexico', 1, '2023-11-12', '2024-11-12');
select * from socios;
delete from socios where fol = 6;
use gym;
select * from Planes where Nom like "%" 'anu' "%";
CREATE TABLE login (
	idR INT AUTO_INCREMENT PRIMARY KEY,
    Clav varchar(50) ,
    Cont varchar(50),
    Nom varchar(50),
    ApePa VARCHAR(50),
    ApeMa VARCHAR(50),
    Edad VARCHAR(2),
    Tele VARCHAR (10),  
    Corr VARCHAR(50),
    Calle VARCHAR(50),
    Num INT,
    Colo VARCHAR(50),
    CP VARCHAR(6),
    Enti VARCHAR(50),
    Esta VARCHAR(50)
    );
select * from login where Clav='gabimaez' and Cont = 'abcdef' limit 1;
 drop table login;
 select * from login;
INSERT INTO login (Clav, Cont, Nom,ApePa, ApeMa, Edad, Tele, Corr, Calle, Num, Colo, Cp, Enti, Esta) VALUES ('gabimaez', 'abcdef', 'Gabriela','Suarez', 'Maldonado', '34', 5525478963, 'GMB@outloo.com', 'Ecat', 12, 'Jardines', '55045', 'MEXICO', 'MEX');
INSERT INTO login (Clav, Cont, Nom,ApePa, ApeMa, Edad, Tele, Corr, Calle, Num, Colo, Cp, Enti, Esta) VALUES ('natarodri', 'fedcba', 'Natalia','Rodriguez', 'Lugo', '24', 5525478947, 'nattt@outloo.com', 'Ecat', 78, 'Americas', '55044', 'MEXICO', 'MEX');

select * from login;
CREATE TABLE Planes (
    NumPlan INT AUTO_INCREMENT PRIMARY KEY,
    Nom varchar(50),
    P INT
    );
    use gym;
    select * from Planes order by NumPlan asc;
    insert into planes (NumPlan, Nom, P) values (1,'anualidad',2500);
	insert into planes (NumPlan, Nom, P) values (2,'mes',350);
	insert into planes (NumPlan, Nom, P) values (3,'trimestre',900);
    
    update Planes set Nom = 'Trimestre', P = '900' where NumPlan = 3;
    -- Cambia el valor de la columna "Nom" a "NuevoNombre" en el registro con NumPlan igual a 1
UPDATE Planes SET Nom = 'Trimestre' WHERE NumPlan = 3;
delete from Planes where NumPlan = 5;

select * from Planes;
use gym;
select * from Planes where Nom like 'anualidad';
/************************************************************************************************ */
    CREATE TABLE Ventas (
    FolV INT AUTO_INCREMENT PRIMARY KEY,
    CanP int,
    DesV varchar(50),
    CosV double,
    FecV date,
    Hor time,
    ForP varchar (50)
    );
    use gym;
    drop table Ventas;
 insert into ventas (CanP, Desv, CosV,FecV, Hor, ForP) 
 values(1,'Botella Agua', 20,'2023-11-29','14:22:00','Efectivo');	
 insert into ventas (CanP, Desv, CosV,FecV, Hor, ForP) 
 values(2,'Botella de agua, 1 sobre de preenteno',20,'2023-11-29','19:11:55','Efectivo');	
 insert into ventas (CanP, Desv, CosV,FecV, Hor, ForP) 
 values(1,'Proteina whey',1200,'2023-11-30','20:22:00','Efectivo');	
 insert into ventas (CanP, Desv, CosV,FecV, Hor, ForP) 
 values(2,'1 Creatina, 1 sobre de preentreno', 580,'2023-11-29','14:22:00','Efectivo');	
 select * from Ventas;
 delete from Ventas where FolV = 9;
 
 CREATE TABLE Productos (
    NumProd INT AUTO_INCREMENT PRIMARY KEY,
    NomProd varchar(50),
    DesProd varchar(50),
    Exi int,
    CosProdu double 
    );
    use gym;
    select * from Productos;
    select * from ventas;
    drop table Productos;
    select * from Productos order by NumProd asc;
    INSERT INTO ventas (CanP, DesV, CosV, FecV, Hor, ForP) VALUES (1, 'creatina', '50', '2023-11-29', '14:22:00', 'Efectivo');
    
    insert into productos(NomProd,DesProd,Exi,CosProdu)
    values ('Proteina whey','iso',4,1200);
    insert into productos(NomProd,DesProd,Exi,CosProdu)
    values ('Oxido','oxido v8',3,600);
    insert into productos(NomProd,DesProd,Exi,CosProdu)
    values ('Botella Agua','1.5L',50,15);
    insert into productos(NomProd,DesProd,Exi,CosProdu)
    values ('Creatina Birdman','monohidratada UN',3,500);
    insert into productos(NomProd,DesProd,Exi,CosProdu)
    values ('Quemador','quemador BlackSpider',3,450);
    
DELIMITER //

CREATE TRIGGER despues_de_insertar_venta
AFTER INSERT ON Ventas
FOR EACH ROW
BEGIN
    -- Desincrementar las existencias
    UPDATE Productos
    SET Exi = Exi - NEW.CanP
    WHERE NomProd = NEW.DesV;
END;

// DELIMITER ;
CREATE TABLE VentasPlanes (
    Num_Plan int,   
    Nom VARCHAR(50),
    CosP VARCHAR(50),
    CorP VARCHAR(50),
    FOREIGN KEY (Num_Plan) REFERENCES Planes(NumPlan)
);
insert into VentasPlanes(Num_Plan,Nom,CosP,CorP)
Values (1,'Anualidad','2500','anual');
insert into VentasPlanes(Num_Plan,Nom,CosP,CorP)
Values (2,'Mensualidad','350','mensual');
insert into VentasPlanes(Num_Plan,Nom,CosP,CorP)
Values (3,'Trimestre','900','trimestral');
insert into VentasPlanes(Num_Plan,Nom,CosP,CorP)
Values (1,'Anualidad','2500','anual');
insert into VentasPlanes(Num_Plan,Nom,CosP,CorP)
Values (1,'Anualidad','2500','anual');