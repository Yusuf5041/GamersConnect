package gc_gui;

import java.util.ArrayList;

public class Player {

	private String username;
	private String password;
	//private ArrayList<UserGame> gameDetails;
	private String discordID;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param gameDetails
	 * @param discordID
	 */
 	public Player(String username, String password, /*ArrayList<UserGame> gameDetails,*/ String discordID) {
		// TODO - implement Player.Player
		this.username = username;
                this.password = password;
                this.discordID = discordID;
	}

	/**
	 * 
	 * @param uName
	 */
	public void setUsername(String uName) {
		this.username = uName;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param pWord
	 */
	public void setPassword(String pWord) {
		this.password = pWord;
	}

	public String getPassword() {
		// TODO - implement Player.getPassword
		return this.password;
	}

	/**
	 * 
	 * @param dID
	 */
	public void setDiscordID(String dID) {
		this.discordID = dID;
	}

	public String getDiscordID() {
		return this.discordID;
	}

	/**
	 * 
	 * @param gName
	 * @param gamerID
	 * @param userRank
	 */
	
        /*public void addGameDetails(Game gName, String gamerID, String userRank) {
		// TODO - implement Player.addGameDetails
		throw new UnsupportedOperationException();
	}*/

	/**
	 * 
	 * @param gName
	 */
        /*
	public void removeGameDetails(Game gName) {
		// TODO - implement Player.removeGameDetails
		throw new UnsupportedOperationException();
	}
        */
	/**
	 * 
	 * @param gName
	 * @param gameID
	 * @param userRank
	 */
	/*
        public void changeGameDetails(Game gName, String gameID, String userRank) {
		// TODO - implement Player.changeGameDetails
		throw new UnsupportedOperationException();
	}
        */
        /*
	public ArrayList getGameDetails() {
		// TODO - implement Player.getGameDetails
		throw new UnsupportedOperationException();
	}
        */

	/**
	 * 
	 * @param user
	 */
	public void deleteAccount(Player user) {
		// TODO - implement Player.deleteAccount
		throw new UnsupportedOperationException();
	}
        /*
	public HostRole createLobby() {
		// TODO - implement Player.createLobby
		throw new UnsupportedOperationException();
	}
        */
	/**
	 * 
	 * @param lobbyName
	 */
        /*
	public void joinLobby(Lobby lobbyName) {
		// TODO - implement Player.joinLobby
		throw new UnsupportedOperationException();
	}
           */

}