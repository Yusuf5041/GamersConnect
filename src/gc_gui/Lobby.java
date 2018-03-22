package gc_gui;

import java.util.ArrayList;

public class Lobby {

    private String lobbyTitle;
    private Game lobbyGame;
    private GameMode lobbyMode;
    private String lobbyRank;
    private String lobbyUniversity;
    private int size;
    private ArrayList<Player> playerList;

    /**
     *
     * @param title
     * @param game
     * @param gmode
     * @param rank
     */
    public Lobby(String title, Game game, GameMode gmode, String rank, String uni, int size) {
        // TODO - implement Lobby.Lobby
        this.lobbyTitle = title;
        this.lobbyGame = game;
        this.lobbyMode = gmode;
        this.lobbyRank = rank;
        this.lobbyUniversity = uni;
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

    public String getLobbyUniversity() {
        return lobbyUniversity;
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
        checkFull();
    }

    public Lobby getLobby() {
        // TODO - implement Lobby.getLobby
        return this;
    }

    private void checkFull() {
        if (this.playerList.size() == this.size) {
            LobbyList.getInstance().getFullList().add(this);
            LobbyList.getInstance().getLobbyList().remove(this);
        }
    }

}
