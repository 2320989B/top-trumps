--SCHEMA:
--GameData(gameNo, winnerIsHuman, winnerName, numOfDraws, numOfRounds, numPlayerWonRounds)


--winnerIsHuman: 0 = false, 1 = true
CREATE TABLE GameData
	(
	gameNo SERIAL CONSTRAINT game_pk PRIMARY KEY,
	winnerIsHuman BOOLEAN,
	winnerName VARCHAR(40),
	numOfDraws FLOAT,
	numOfRounds INT,
	numPlayerWonRounds INT
	);


--SQL QUERIES:
--Number of games played overall:
--SELECT COUNT (*) FROM GameData

--How many times the computer has won
--SELECT COUNT (*) FROM GameData WHERE winnerIsHuman = '0'

--How many times the human has won (Could get these three results from above two queries only)
--SELECT COUNT (*) FROM GameData WHERE winnerIsHuman = '1'

--Average number of draws
--SELECT AVG(numOfDraws) FROM GameData

--Largest number of rounds
--SELECT MAX(numOfRounds) FROM GameData

--Commit the data
--INSERT INTO GameData VALUES(gameNo, winnerIsHuman, winnerName, numOfDraws, numOfRounds, numPlayerWonRounds)

-- Grant privileges.
GRANT SELECT, INSERT ON TABLE gamedata TO m_17_2349654m;
GRANT USAGE ON SEQUENCE gamedata_gameno_seq TO m_17_2349654m;
