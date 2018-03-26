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

    private LobbyList() {
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

    public ArrayList<Lobby> getLobbyList() {
        return this.lobbyList;
    }

    public ArrayList<Lobby> getFilteredList() {
        return filteredList;
    }

    public ArrayList<Lobby> getFullList() {
        return fullList;
    }

    private void readFile() {
        FileReader in = null;
        try {
            in = new FileReader("lib/lobbylist.txt");
            BufferedReader br = new BufferedReader(in);
            String name, game, mode, rank, uni, size, users;
            UserAccountList ual = UserAccountList.getInstance();
            String[] u;
            Game gameName;
            GameMode gMode;
            Player p;
            Lobby lob;
            while ((name = br.readLine()) != null) {
                game = br.readLine();
                mode = br.readLine();
                gameName = checkGame(game);
                gMode = checkMode(gameName, mode);
                rank = br.readLine();
                uni = br.readLine();
                size = br.readLine();
                int s = Integer.parseInt(size);
                users = br.readLine();
                u = users.split(" ");
                p = ual.findPlayer(u[0]);
                lob = new Lobby(name, gameName, gMode, rank, p.getUniversity(), s, p.getUsername());
                addLobby(lob, p);
                for (int i = 1; i < u.length; i++) {
                    lob.addPlayer(ual.findPlayer(u[i]));
                }
                checkFull(lob);

            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error: File not found");
            Logger.getLogger(LobbyList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: File could not be read");
            Logger.getLogger(LobbyList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: File could not be closed");
                Logger.getLogger(GameList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     *
     * @param lobbyName
     * @param p
     */
    public void addLobby(Lobby lobbyName, Player p) {
        // TODO - implement LobbyList.addLobby
        lobbyList.add(lobbyName);
        p.addLobby(lobbyName);
    }

    public static Game checkGame(String g) {
        ArrayList<Game> gl = GameList.getInstance().getGameList();
        for (int i = 0; i < gl.size(); i++) {
            if (g.equals(gl.get(i).getGameName())) {
                return gl.get(i);
            }
        }
        return null;
    }

    public static GameMode checkMode(Game g, String mode) {
        ArrayList<GameMode> m = g.getModes();
        for (int j = 0; j < m.size(); j++) {
            if (mode.equals(m.get(j).getModeName())) {
                return m.get(j);
            }
        }
        return null;
    }

    public boolean checkList(Lobby lob) {
        for (int i = 0; i < filteredList.size(); i++) {
            if (lob == filteredList.get(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkFull(Lobby lob) {
        if (lob.getPlayerList().size() == lob.getSize()) {
            fullList.add(lob);
            lobbyList.remove(lob);
            return true;
        } else {
            if (!lobbyList.contains(lob)) {
                lobbyList.add(lob);
                fullList.remove(lob);
            }
        }
        return false;
    }

    /**
     *
     * @param lobbyName
     */
    public void removeLobby(Lobby lobbyName) {
        // TODO - implement LobbyList.removeLobby
        try {
            lobbyList.remove(lobbyName);
        } catch (NullPointerException e) {

        }
        try {
            fullList.remove(lobbyName);
        } catch (NullPointerException e) {

        }
        try {
            filteredList.remove(lobbyName);
        } catch (NullPointerException e) {

        }
    }

    public static void filterList(String game, String mode, String rank, String uni) {
        LobbyList.getInstance().getFilteredList().clear();
        LobbyList inst = LobbyList.getInstance();
        if (game.equals("Any")) {

        } else {

        }
        Game g = checkGame(game);
        GameMode gm = checkMode(g, mode);

        for (Lobby lobby : inst.getLobbyList()) {
            if (g == lobby.getLobbyGame() || game.equals("Any")) {
                if (gm == lobby.getLobbyMode() || mode.equals("Any")) {
                    if (rank.equals(lobby.getLobbyRank()) || rank.equals("Any")) {
                        if (uni.equals(lobby.getLobbyUniversity()) || uni.equals("Any")) {

                            inst.getFilteredList().add(lobby);

                        }
                    }
                }
            }
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
