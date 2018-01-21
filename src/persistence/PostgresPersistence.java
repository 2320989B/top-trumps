package persistence;
import java.sql.*;

public class PostgresPersistence {

	// Declare Connection
	private Connection connection = null;

	// Declare attributes to be written to the database
	private int numOfGames = 0;
	private int numOfRounds = 0;
	private int numPlayerWins = 0;
	private Player gameWinner;
	private double numDraws = 0.0;

	/** 
	*	SQL Queries 
	*/
	String QUERY_getNumOfGames = "SELECT COUNT (*) FROM GameData";
	String QUERY_AIWins = "SELECT COUNT (*) FROM GameData WHERE winnerIsHuman = '0'";
	String QUERY_humanWins = "SELECT COUNT (*) FROM GameData WHERE winnerIsHuman = '1'";
	String QUERY_avgDraws = "SELECT AVG(numOfDraws) FROM GameData";
	String QUERY_maxRounds = "SELECT MAX(numOfRounds) FROM GameData";

	/**
	 * Constructs a postgreSQL connection to the persistence database
	 */
	public PostgresPersistence() {
		//Set up a connection
		try {
			/** PRODUCTION VALUES */
			//String dbURL = "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/"
			//String username = "m_17_2349654m";
			//String password = "2349654m";
			//String dbname = "m_17_2349654m";
			
			/** LOCAL VALUES */
			String username = "postgres";
			String dbname = "dbname";
			String password = "pw";
			String dbURL = "jdbc:postgresql://localhost:5432/";
			
			// Establish connection
			connection = DriverManager.getConnection(dbURL+dbname, username, password);
			System.out.println("Connection established!");

			// Get the total previous games
			this.numOfGames = this.getGameCount();

		} catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	* 	Set the number of draws for the current game
	*	@param num The number of draws
	*/
	public void setGameDraws(int num) {
		this.numDraws = (double)num;
	}

	/**
	* 	Set the winner as a Player object
	*	@param playerObj The winning player
	*/
	public void setGameWinner(Player playerObj) {
		this.gameWinner = playerObj;
	}

	/**
	* 	Set the number of rounds for the current game
	*	@param num The number of rounds
	*/
	public void setNumGameRounds(int num) {
		this.numOfRounds = num;
	}

	/**
	* 	Set the number of rounds the player won
	*	@param num The number of rounds won
	*/
	public void setPlayerRounds(int num) {
		this.numPlayerWins = num;
	}

	/**
	* 	Commit a full game record to the database
	*	@return True if successful
	*/
	public boolean commit() {
		// Get current game number (primary key of DB)
		int gameNo = this.numOfGames + 1;

		// Check if winner was human
		int winnerIsHuman = 0;
		if (this.gameWinner.getIsHuman()) {
			winnerIsHuman = 1;
		}

		// Get winner name
		String winnerName = this.gameWinner.getName();

		Statement statement = null;
		String QUERY_commit = "INSERT INTO GameData VALUES(" + gameNo + ", " + winnerIsHuman + ", " + winnerName + ", " + this.numDraws + ", " + this.numOfRounds + ", " + this.numPlayerWins + ")";
		try {
			statement = connection.createStatement();
			int resSet = statement.executeUpdate(QUERY_commit);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query failed");
			return false;
		}

	}

	/**
	*	Get the total number of games previously played
	*	@return The number of games played
	*/
	public int getGameCount() {
		Statement statement = null;
		int gameNo = 0;
		try {
			statement = connection.createStatement();
			ResultSet resSet = statement.executeQuery(QUERY_getNumOfGames);
			while (resSet.next()) {
				gameNo = resSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query failed");
		}

		return gameNo;
	}

	/**
	* Get the number of previous games won by the AI
	* @return The number of games
	*/
	public int getAIWinCount() {
		Statement statement = null;
		int AIWins = 0;
		try {
			statement = connection.createStatement();
			ResultSet resSet = statement.executeQuery(QUERY_AIWins);
			while (resSet.next()) {
				AIWins = resSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query failed");
		}

		return AIWins;
	}

	/**
	* Get the number of previous games won by humans
	* @return The number of games
	*/
	public int getHumanWinCount() {
		Statement statement = null;
		int humanWins = 0;
		try {
			statement = connection.createStatement();
			ResultSet resSet = statement.executeQuery(QUERY_humanWins);
			while (resSet.next()) {
				humanWins = resSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query failed");
		}

		return humanWins;
	}

	/**
	* Get the average number of draws in previous games
	* @return The number of draws
	*/
	public double getAverageDraws() {
		Statement statement = null;
		double drawCount = 0;
		try {
			statement = connection.createStatement();
			ResultSet resSet = statement.executeQuery(QUERY_avgDraws);
			while (resSet.next()) {
				drawCount = resSet.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query failed");
		}

		return drawCount;
	}

	/**
	* Get the highest number of rounds played in a single game
	* @return The number of rounds
	*/
	public int getMaxRoundCount() {
		Statement statement = null;
		int maxRounds = 0;
		try {
			statement = connection.createStatement();
			ResultSet resSet = statement.executeQuery(QUERY_maxRounds);
			while (resSet.next()) {
				maxRounds = resSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query failed");
		}

		return maxRounds;
	}



}
