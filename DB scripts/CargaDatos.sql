USE SILBD;

DELETE FROM `TiposInstrumentos` WHERE TRUE;
DELETE FROM `Instrumentos` WHERE TRUE;
DELETE FROM `Calibraciones` WHERE TRUE;
DELETE FROM `Medidas` WHERE TRUE;

LOAD DATA LOCAL INFILE '/home/fabio/Escritorio/Carpeta sin título/DatosTiposInstrumentos.txt'
	REPLACE
    INTO TABLE `TiposInstrumentos`
    FIELDS TERMINATED BY ';'
    LINES TERMINATED BY '\n';
    
LOAD DATA LOCAL INFILE '/home/fabio/Escritorio/Carpeta sin título/DatosInstrumentos.txt'
	REPLACE
    INTO TABLE `Instrumentos`
    FIELDS TERMINATED BY ';'
    LINES TERMINATED BY '\n'
    (serie, tipo, descripcion, minimo, maximo, tolerancia);

LOAD DATA LOCAL INFILE '/home/fabio/Escritorio/Carpeta sin título/DatosCalibraciones.txt'
	REPLACE
    INTO TABLE `Calibraciones`
    FIELDS TERMINATED BY ';'
    LINES TERMINATED BY '\n'
    (numCalibracion, fecha, instrumento);
    
LOAD DATA LOCAL INFILE '/home/fabio/Escritorio/Carpeta sin título/DatosMedidas.txt'
	REPLACE
    INTO TABLE `Medidas`
    FIELDS TERMINATED BY ';'
    LINES TERMINATED BY '\n'
    (numero, referencia, lectura,calibracion);
    
