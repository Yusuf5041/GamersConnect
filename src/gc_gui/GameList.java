package gc_gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameList {

    private static GameList instance;
    private ArrayList<Game> listOfGames;

    private GameList() {
        // TODO - implement GameList.GameList
        listOfGames = new ArrayList<>();
        readFile();
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
        listOfGames.add(gameName);
    }

    private void readFile() {
        FileReader in = null;
        try {
            in = new FileReader("C://Users/Main/Documents/GamersConnect/lib/gamelist.txt");
            BufferedReader br = new BufferedReader(in);
            String name, modes, ranks;
            Game temp;
            String[] s;
            while((name=br.readLine()) != null){
                addGame(new Game(name));
                temp = listOfGames.get(listOfGames.size()-1);
                modes=br.readLine();
                s = modes.split(" ");
                for (int i = 0; i < s.length; i+=2) {
                    temp.addMode(Integer.parseInt(s[i+1]), s[i]);
                }
                
               ranks = br.readLine();
               s = ranks.split(" ");
                for (int i = 0; i < s.length; i++) {
                    temp.getRankSystem().setRanks(s[i]);
                }
                
                
            }   return;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(GameList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
