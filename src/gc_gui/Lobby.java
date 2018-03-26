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
    private String host;
    private LobbyList ll;

    /**
     *
     * @param title
     * @param game
     * @param gmode
     * @param rank
     */
    public Lobby(String title, Game game, GameMode gmode, String rank, String uni, int size, String host) {
        // TODO - implement Lobby.Lobby
        this.lobbyTitle = title;
        this.lobbyGame = game;
        this.lobbyMode = gmode;
        this.lobbyRank = rank;
        this.lobbyUniversity = uni;
        this.size = size;
        this.playerList = new ArrayList<>();
        this.host = host;
        
    }

    public int getSize() {
        return size;
    }
    
    public void reduceSize(){
        size = size - 1;
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

    public String getHost() {
        return host;
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

    private boolean checkFull() {
        LobbyList ll = LobbyList.getInstance();
        if (this.playerList.size() == this.size) {
            ll.getFullList().add(this);
            ll.getLobbyList().remove(this);
            return true;
        }
        return false;
    }

    public void removeLobbyPlayer(Player user) {
        
    }

}
