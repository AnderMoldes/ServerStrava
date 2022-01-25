/* CREATE ''accountsDB' DATABASE */
CREATE SCHEMA ServerBBDD;
/* CREATE THE USER 'accounts_user' AT LOCAL SERVER WITH PASSWORD 'password' */
CREATE USER 'accounts_user'@'%' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE FOR THE USER 'accounts_user' AT LOCAL SERVER*/
GRANT ALL ON ServerBBDD.* TO 'accounts_user'@'%';

USE ServerBBDD;
SELECT * FROM usuarios;
SELECT * FROM retos;
SELECT * FROM sesiones;