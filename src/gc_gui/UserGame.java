/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gc_gui;

/**
 *
 * @author Yusuf
 */
public class UserGame {

    private final Game game;
    private final String gamerID;
    private final String userRank;

    public UserGame(Game game, String gamerID, String userRank) {
        this.game = game;
        this.gamerID = gamerID;
        this.userRank = userRank;
    }

    public Game getGame() {
        return game;
    }

    public String getGamerID() {
        return gamerID;
    }

    public String getUserRank() {
        return userRank;
    }
    
    
    
}
