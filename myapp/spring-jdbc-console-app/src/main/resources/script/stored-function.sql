DELIMITER //
CREATE FUNCTION getFirstNameById(in_id INT)
    RETURNS VARCHAR(60)
BEGIN
    RETURN (SELECT FIRST_NAME FROM SINGER WHERE ID = in_id);
END //
DELIMITER ;


DELIMITER //
CREATE FUNCTION getLastNameById(in_id INT)
    RETURNS VARCHAR(60)
BEGIN
    RETURN (SELECT LAST_NAME FROM SINGER WHERE ID = in_id);
END //
DELIMITER ;
