package gc_gui;

import java.util.ArrayList;

public class Lobby {

	private String lobbyTitle;
	private Game lobbyGame;
	private GameMode lobbyMode;
	private String lobbyRank;
        private int size;
	private ArrayList<Player> playerList;

	/**
	 * 
	 * @param title
	 * @param game
	 * @param gmode
	 * @param rank
	 */
	public Lobby(String title, Game game, GameMode gmode, String rank, int size) {
		// TODO - implement Lobby.Lobby
		this.lobbyTitle = title;
                this.lobbyGame = game;
                this.lobbyMode = gmode;
                this.lobbyRank = rank;
                this.size = size;
                this.playerList = new ArrayList<>();
	}

    public int getSize() {
        return size;
    }

    public String getLobbyTitle() {
        return lobbyTitle;
    }

    public Game getLobbyGame() {
        return lobbyGame;
    }

    public GameMode getLobbyMode() {
        return lobbyMode;
    }

    public String getLobbyRank() {
        return lobbyRank;
    }

	public ArrayList getPlayerList() {
		return this.playerList;
	}

	/**
	 * 
	 * @param user
	 */
	public void addPlayer(Player user) {
		// TODO - implement Lobby.addPlayer
		this.playerList.add(user);
	}

	public Lobby getLobby() {
		// TODO - implement Lobby.getLobby
		return this;
	}

}