package gc_gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LobbyList {

    private static LobbyList instance;
    private ArrayList<Lobby> lobbyList;
    private ArrayList<Lobby> filteredList;
    private ArrayList<Lobby> fullList;

    protected LobbyList() {
        this.lobbyList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        this.fullList = new ArrayList<>();
        readFile();
    }

    public static LobbyList getInstance() {
        if (instance == null) {
            instance = new LobbyList();
        }
        return instance;
    }

    public ArrayList getLobbyList() {
        return this.lobbyList;
    }

    public ArrayList<Lobby> getFullList() {
        return fullList;
    }

    private void readFile(){
        FileReader in = null;
        try {
            in = new FileReader("C://Users/Main/Documents/GamersConnect/lib/lobbylist.txt");
            BufferedReader br = new BufferedReader(in);
            String name, game, mode, rank, size;
            Game gameName;
            GameMode gMode;
            while((name=br.readLine()) != null){
                game=br.readLine();
                mode=br.readLine();
                gameName=checkGame(game);
                gMode = checkMode(gameName, mode);
                rank=br.readLine();
                size=br.readLine();
                int s = Integer.parseInt(size);
                addLobby(new Lobby(name, gameName, gMode, rank, s ));
            }   
            return;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LobbyList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LobbyList.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    /**
     *
     * @param lobbyName
     */
    public void addLobby(Lobby lobbyName) {
        // TODO - implement LobbyList.addLobby
        lobbyList.add(lobbyName);
        
    }
    
    public static Game checkGame(String g){
        ArrayList<Game> gl = GameList.getInstance().getGameList();
        for (int i = 0; i < gl.size(); i++) {
            if(g.equals(gl.get(i).getGameName())) {
                return gl.get(i);
            }
        }
        return null;
    }
    
    public static GameMode checkMode(Game g, String mode){
        ArrayList<GameMode> m = g.getModes();
        for (int j = 0; j < m.size(); j++) {
                     if(mode.equals(m.get(j).getModeName())) return m.get(j);
                }
        return null;
    }
    
    /**
     *
     * @param lobbyName
     */
    public void removeLobby(Lobby lobbyName) {
        // TODO - implement LobbyList.removeLobby
        try{
            lobbyList.remove(lobbyName);
        }catch(NullPointerException e){

        }
        try{
            fullList.remove(lobbyName);
        } catch(NullPointerException e){
            
        }
        try{
            filteredList.remove(lobbyName);
        }catch(NullPointerException e){
            
        }
        
            
    }

    public Integer findLobbySize() {
        // TODO - implement LobbyList.findLobbySize
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param game
     * @param rank
     * @param university
     * @param gmode
     */
    public ArrayList searchLobbies(int game, int rank, int university, int gmode) {
        // TODO - implement LobbyList.searchLobbies
        throw new UnsupportedOperationException();
    }

}
