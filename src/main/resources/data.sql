drop table if exists variant;

CREATE TABLE variant
(
    variant_id INT NOT NULL auto_increment KEY,
    chromosome VARCHAR(2),
    position INT,
    ref TEXT,
    alt TEXT,
    amc VARCHAR(20),
    erasmus VARCHAR(20),
    lumc VARCHAR(20),
    nki VARCHAR(20),
    radboud VARCHAR(20),
    umcg VARCHAR(20),
    umcu VARCHAR(20),
    vumc VARCHAR(20)
);


SET GLOBAL local_infile = 'ON';

# This path should point at your consensus.txt file.
LOAD DATA LOCAL INFILE '/Users/alu23868/Documents/VKGL/consensus_part.txt'
    INTO TABLE variant
    COLUMNS TERMINATED BY '\t'
    IGNORE 1 LINES
    (chromosome, position, ref, alt, amc, erasmus, lumc, nki, radboud, umcg, umcu, vumc)
    SET variant_id = NULL;

SET GLOBAL local_infile = 'OFF';