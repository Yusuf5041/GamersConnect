package gc_gui;

import java.io.FileReader;
import java.util.*;

public class Game {

    ArrayList<GameMode> modes;
    private String gameName;
    //private GameMode mode;
    private RankSystem rs;

    public Game(String gameName) {
        this.gameName = gameName;
        this.modes = new ArrayList<>();
        rs = new RankSystem();
        
    }

    public RankSystem getRankSystem() {
        
        return this.rs;
    }
    
    public ArrayList<GameMode> getModes(){
        return this.modes;
    }
    
    public void addMode(int size, String name){
        modes.add(new GameMode(size, name));
    }

    public String getGameName() {
        return gameName;
    }
    
}
