-- Création de la table couple
CREATE TABLE IF NOT EXISTS couple (
    id BIGSERIAL PRIMARY KEY,
    nom_partenaire1 VARCHAR(255),
    nom_partenaire2 VARCHAR(255),
    name VARCHAR(255),
    datemariage DATE,
    photo TEXT
);

-- Création de la table guest
CREATE TABLE IF NOT EXISTS guest (
    id BIGSERIAL PRIMARY KEY,
    guest_table VARCHAR(255),
    confirmed BOOLEAN DEFAULT FALSE,
    couple_id BIGINT,
    CONSTRAINT fk_couple FOREIGN KEY (couple_id) REFERENCES couple(id)
);
