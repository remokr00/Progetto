DROP SCHEMA sito;
CREATE SCHEMA sito;
USE sito;

-- Tabelle create da Irtuso Remo

CREATE TABLE utente
(

    id_utente       INTEGER  PRIMARY KEY,
    codice_fiscale  VARCHAR (15),
    nome            VARCHAR(50),
    cognome         VARCHAR(50),
    data_di_nascita VARCHAR(10),
    indirizzo       VARCHAR(50),
    e_mail          VARCHAR(50)

);

CREATE TABLE opera
(

    codice      INTEGER  PRIMARY KEY,
    nome        VARCHAR(50),
    descrizione VARCHAR(500),
    prezzo      FLOAT,
    artista     INTEGER,
    tipologia   VARCHAR(20),
    FOREIGN KEY (artista) REFERENCES artista(id_artista)

);


-- Tabelle create da Gallo Ilaria
CREATE TABLE artista
(
    id_artista     INTEGER PRIMARY KEY,
    codice_fiscale VARCHAR(15) PRIMARY KEY,
    nome           VARCHAR(50),
    cognome        VARCHAR(50),
    bio            VARCHAR(500)
);

CREATE TABLE ordine
(
    codice               INTEGER PRIMARY KEY,
    utente               VARCHAR(15),
    opera                INTEGER,
    nome_intestatario    VARCHAR(50),
    cognome_intestatario VARCHAR(50),
    data                 DATETIME DEFAULT CURRENT_TIMESTAMP,
    numero_carta         VARCHAR(20),
    scadenza             VARCHAR(5),
    CVV                  VARCHAR(3),
    FOREIGN KEY (utente) REFERENCES utente (id_utente),
    FOREIGN KEY (opera) REFERENCES opera(codice)
);

