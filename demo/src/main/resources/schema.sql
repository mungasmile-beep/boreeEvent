-- Création de la table couple
CREATE TABLE IF NOT EXISTS couple (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom_partenaire1 VARCHAR(255),
    nom_partenaire2 VARCHAR(255),
    name VARCHAR(255),
    datemariage DATE,
    photo LONGTEXT
);
-- Création de la table guest avec la structure nécessaire
CREATE TABLE IF NOT EXISTS guest (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    guest_table VARCHAR(255),
    confirmed BOOLEAN DEFAULT FALSE,
    couple_id BIGINT,
    FOREIGN KEY (couple_id) REFERENCES couple(id)
);