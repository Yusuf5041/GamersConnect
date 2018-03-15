package gc_gui;

import java.util.*;

public class Game {

    Collection<GameMode> has;
    private String gameName;
    private GameMode mode;

    public Game(String gameName, GameMode modeName) {
        this.gameName = gameName;
        this.mode = modeName;
    }

    public String getGameName() {
        return this.gameName;
    }
    
    public GameMode getGameMode(){
        return this.mode;
    }

}
