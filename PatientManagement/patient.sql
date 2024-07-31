CREATE TABLE mycompany.Patient(
	number 		TINYINT,
    code		CHAR(2) NOT NULL,
    days		SMALLINT NOT NULL,	
    age			TINYINT NOT NULL,
    dept		VARCHAR(20),
    operfee		INT,
    hospitalfee	INT,
    money 		INT,
    CONSTRAINT patient_number_pk PRIMARY KEY(number)
)DEFAULT CHARSET utf8;

-- Patient Management Select All Procedure
Delimiter $$
CREATE PROCEDURE sp_select_all_patient(
)
BEGIN
	SELECT number, dept, operfee, hospitalfee, money
    FROM Patient
    ORDER BY number DESC;
END
$$
Delimiter ;


-- Select One Patient Procedure
Delimiter //
CREATE PROCEDURE sp_select_one_patient( IN v_number TINYINT ) 
BEGIN
	SELECT *
    FROM Patient
    WHERE number = v_number;
END
//
Delimiter ;