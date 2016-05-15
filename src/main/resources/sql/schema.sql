SET DATABASE SQL SYNTAX ORA TRUE/;

DROP TABLE ENV_PROP IF EXISTS/;
CREATE TABLE ENV_PROP 
   (	ENV_PROP_ID NUMBER NOT NULL , 
	ENV_NM VARCHAR2(100 ), 
	ENV_VAL VARCHAR2(3000 ), 
	ACTV_IND CHAR(1 ), 
	CAN_CACHED CHAR(1 ), 
	CREA_BY VARCHAR2(30 ), 
	CREA_DT DATE, 
	MDFY_BY VARCHAR2(30 ), 
	MDFY_DT DATE
   )/;

DROP TABLE TEST IF EXISTS/;
CREATE TABLE
    TEST
    (
        REPORT_ID VARCHAR2(100),
        REPORT_NAME VARCHAR2(100),
        CREATED_BY VARCHAR2(25 CHAR) NOT NULL,
        CREATION_TIME TIMESTAMP(6) WITH LOCAL TIME ZONE DEFAULT SYSTIMESTAMP NOT NULL
    )/;