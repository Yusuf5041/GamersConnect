package gc_gui;

import java.util.ArrayList;

public class Player {

    private String username;
    private String password;
    private String university;
    private ArrayList<UserGame> gameDetails;
    private String discordID;
    private Role role;

    /**
     *
     * @param username
     * @param password
     * @param gameDetails
     * @param discordID
     */
    public Player(String username, String password, String discordID) {
        // TODO - implement Player.Player
        this.username = username;
        this.password = password;
        this.discordID = discordID;
        this.gameDetails = new ArrayList<UserGame>();
        this.role = new UserRole();
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

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUniversity() {
        return university;
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

    public String getGamerID(Game g) {
        for (UserGame gameDetail : gameDetails) {
            if (gameDetail.getGame() == g) {
                return gameDetail.getGamerID();
            }
        }
        return null;
    }

    public String getRank(Game g) {
        for (UserGame gameDetail : gameDetails) {
            if (gameDetail.getGame() == g) {
                return gameDetail.getUserRank();
            }
        }
        return null;
    }

    public Role getRole() {
        return this.role;
    }

    public void resetRole() {
        role = new UserRole();
    }

    /**
     *
     * @param gName
     * @param gamerID
     * @param userRank
     */

    public void addGameDetails(Game gName, String gamerID, String userRank) {
        // TODO - implement Player.addGameDetails
        this.gameDetails.add(new UserGame(gName, gamerID, userRank));
    }

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
    public ArrayList<UserGame> getGameDetails() {
        // TODO - implement Player.getGameDetails
        return this.gameDetails;
    }

    /**
     *
     * @param user
     */
    public void deleteAccount(Player user) {
        // TODO - implement Player.deleteAccount
        throw new UnsupportedOperationException();
    }

    public void createLobby(String title, Game game, GameMode gmode, String rank, int size) {
        // TODO - implement Player.createLobby
        ArrayList<Lobby> list = LobbyList.getInstance().getLobbyList();
        list.add(new Lobby(title, game, gmode, rank, this.university, size));
        list.get(list.size() - 1).addPlayer(this);

        if (role instanceof UserRole) {
            role = new HostRole();
        }
        HostRole hr = (HostRole) role;
        hr.addUserLobby(list.get(list.size() - 1));

    }

    /**
     *
     * @param lobbyName
     */
    public void joinLobby(Lobby lobbyName) {
        // TODO - implement Player.joinLobby
        lobbyName.addPlayer(this);
    }

}
