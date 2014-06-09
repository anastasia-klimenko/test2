 DROP TABLE IF EXISTS USER;
 CREATE TABLE USER(
   "ID" bigint auto_increment NOT NULL,
   "FIRST_NAME" VARCHAR2(32),
   "LAST_NAME" VARCHAR2(32)
 );
INSERT INTO USER(FIRST_NAME,LAST_NAME) values ('firstname1','lastname1');
INSERT INTO USER(FIRST_NAME,LAST_NAME) values ('firstname2','lastname2');