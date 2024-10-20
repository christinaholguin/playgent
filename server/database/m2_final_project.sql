-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS country, league, team, athlete, agent CASCADE;


-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE country (
    country_id SERIAL PRIMARY KEY,
    country_name VARCHAR(50) UNIQUE
);

CREATE TABLE league (
    league_id SERIAL PRIMARY KEY,
    league_name VARCHAR(50) UNIQUE,
    country_id INT REFERENCES country(country_id),
    number_of_teams_in_league INT,
league_division INT
);

CREATE TABLE team (
    team_id SERIAL PRIMARY KEY,
    team_name VARCHAR(50) UNIQUE,
    country_id INT REFERENCES country(country_id),
    rank_from_previous_season INT,
    number_of_players_on_team INT,
    number_of_league_titles_won INT
);

CREATE TABLE athlete (
    athlete_id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    dob DATE,
    height DECIMAL,
    nationality VARCHAR(50),
    visa VARCHAR(50),
    position_played VARCHAR(50),
    current_team_id INT REFERENCES team(team_id),
    current_contract_expiration DATE,
    residing_country_id INT REFERENCES country(country_id)
);

CREATE TABLE agent (
    agent_id SERIAL PRIMARY KEY,
    agency_name VARCHAR(50) UNIQUE,
    years_of_experience INT,
    players_currently_representing VARCHAR(50),
    team_associations VARCHAR(50),
    rating DECIMAL
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN'),
    ('agent','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_AGENT');


INSERT INTO country (country_name) VALUES ('USA');
INSERT INTO country (country_name) VALUES ('Mexico');
INSERT INTO country (country_name) VALUES ('France');


INSERT INTO league (league_name, country_id, number_of_teams_in_league, league_division) VALUES ('NWSL', 1, 14, 1);
INSERT INTO league (league_name, country_id, number_of_teams_in_league, league_division) VALUES ('UWS', 1, 22, 2);
INSERT INTO league (league_name, country_id, number_of_teams_in_league, league_division) VALUES ('Liga Mx Femenil', 2, 18, 1);
INSERT INTO league (league_name, country_id, number_of_teams_in_league, league_division) VALUES ('Premiere Ligue', 3, 12, 1);
INSERT INTO league (league_name, country_id, number_of_teams_in_league, league_division) VALUES ('Seconde Ligue', 3, 12, 2);


insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Angel City FC', 1, 5, 26, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Bay FC', 1, 14, 24, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Chicago Red Stars', 1, 12, 27, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Houston Dash', 1, 10, 23, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Kansas City Current', 1, 11, 24, 2);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('NY/NJ Gotham FC', 1, 6, 25, 1);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Portland Thorns FC', 1, 2, 24, 3);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('North Carolina Courage', 1, 3, 23, 2);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Orlando Pride', 1, 7, 31, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Racing Louisville FC', 1, 9, 26, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('San Diego Wave FC', 1, 1, 23, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Seattle Reign FC', 1, 4, 26, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Utah Royals', 1, 13, 22, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Washington Spirit', 1, 8, 24, 1);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Pumas', 2, 6, 24, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Tigres', 2, 1, 28, 6);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Toluca', 2, 9, 22, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Xolas', 2, 11, 25, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Santos Laguna', 2, 17, 23, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Queretaro', 2, 10, 20, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Puebla', 2, 12, 22, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Pachuca', 2, 2, 32, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Necaxa', 2, 15, 24, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Monterrey', 2, 3, 23, 3);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Mazatlan', 2, 14, 19, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Leon', 2, 7, 24, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Juarez', 2, 8, 25, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Chivas', 2, 5, 25, 2);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Cruz Azul', 2, 16, 20, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Atletico San Luis', 2, 18, 17, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Atlas', 2, 13, 21, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('America', 2, 4, 19, 3);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Stade de Reims', 3, 4, 30, 5);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('RC Strasbourg Alsace', 3, 1, 40, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Paris Saint-Germain', 3, 2, 30, 1);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Paris FC', 3, 3, 40, 6);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Olympique Lyonnais', 3, 1, 34, 17);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Montpellier HSC', 3, 6, 33, 2);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Le Havre AC', 3, 9, 29, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('FC Nantes', 3, 2, 33, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('FC Fleury 91', 3, 5, 30, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('En Avant Guingamp', 3, 10, 33, 1);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Dijon FCO', 3, 8, 28, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('AS Saint-Étienne', 3, 7, 30, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('ASPTT Albi', 3, 10, 30, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('FC Girondins de Bordeaux', 3, 11, 24, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('FC Metz', 3, 8, 26, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Le Mans FC', 3, 9, 24, 2);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('LOSC Lille', 3, 12, 21, 1);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('OGC Nice', 3, 4, 25, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Olympique de Marseille', 3, 3, 30, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('RC lens', 3, 5, 25, 1);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Rodez AF', 3, 6, 29, 2);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Thonon Evian Grand Genève FC', 3, 7, 24, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('Toulouse FC', 3, 2, 25, 0);
insert into team (team_name, country_id, rank_from_previous_season, number_of_players_on_team, number_of_league_titles_won) values ('US Saint-Malo', 3, 1, 12, 1);


INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Christina Holguin', '1996-07-25', 1.75, 'Mexican/American', 'No', 'Goal Keeper', 22, '2024-12-30', 2);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Alex Morgan', '1986-02-21', 1.68, 'French', 'No', 'Forward', 11, '2026-11-11', 1);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Christen Press', '1994-09-25', 1.70, 'American', 'No', 'Midfielder', 1, '2025-09-13', 1);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Sakina Karchaoui', '1996-06-25', 1.60, 'French', 'Work Visa', 'Defender', 35, '2027-04-08', 3);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Debina', '1985-12-22', 1.60, 'Brazilian', 'Unknown', 'Midfielder', 9, '2024-02-03', 1);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Beatriz Zaneratto', '1994-05-04', 1.71, 'Brazilian', 'None', 'Forward', 9, '2029-09-22', 1);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Miah Zuazua', '1999-03-27', 1.70, 'Mexican/American', 'None', 'Attacking Mid', 32, '2028-11-15', 2);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Scarlett Camberos', '1998-04-28', 1.68, 'Mexican/American', 'None', 'Forward', 2, '2028-10-24', 1);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Elisa de Almeida', '2000-01-04', 1.72, 'French', 'Restricted Travel', 'Defender', 35, '2026-07-23', 3);
INSERT INTO athlete (name, dob, height, nationality, visa, position_played, current_team_id, current_contract_expiration, residing_country_id) VALUES ('Melchie Dumornay', '2003-08-17', 1.68, 'French', 'Restricted Travel', 'Forward', 35, '2025-10-05', 3);

INSERT INTO agent (agency_name, years_of_experience, players_currently_representing, team_associations, rating) VALUES ('Fem11', 22, 'Christina Holguin, Miah Zuazua', 'Pachuca, America', 4.9);
INSERT INTO agent (agency_name, years_of_experience, players_currently_representing, team_associations, rating) VALUES ('Uppervathletes', 10, 'Christen Press', 'Angel City', 4.5);
INSERT INTO agent (agency_name, years_of_experience, players_currently_representing, team_associations, rating) VALUES ('CMG', 15, 'Scarlett Camberos', 'Bay FC', 4.2);
INSERT INTO agent (agency_name, years_of_experience, players_currently_representing, team_associations, rating) VALUES ('Sports Management Worldwide', 20, 'Sakina Karchaoui', 'Paris Saint Germain', 4.8);


COMMIT TRANSACTION;
