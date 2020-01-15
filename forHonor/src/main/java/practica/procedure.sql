DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Change_Faction`(IN `id_personaje` INT(32), IN `id_faccion_destino` INT(32))
    NO SQL
UPDATE personaje SET personaje.faccion_id = id_faccion_destino WHERE personaje.personaje_id = id_personaje$$
DELIMITER ;
