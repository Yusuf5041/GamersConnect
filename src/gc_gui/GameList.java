package gc_gui;

import java.util.ArrayList;

public class GameList {

    private static GameList instance;
    private ArrayList<Game> listOfGames;

    private GameList() {
        // TODO - implement GameList.GameList
        listOfGames = new ArrayList<>();
        listOfGames.add(new Game("Overwatch", new GameMode(6, "Competitive")));
        listOfGames.add(new Game("League of Legends", new GameMode(4, "Competitive")));
    }

    public static GameList getInstance() {
        if (instance == null) {
            instance = new GameList();
        }
        return instance;
    }

    public ArrayList getGameList() {
        // TODO - implement GameList.getGameList
        return this.listOfGames;
    }

    /**
     *
     * @param gameName
     */
    public void addGame(Game gameName) {
        // TODO - implement GameList.addGame
        throw new UnsupportedOperationException();
    }

}
