 package gc_gui;

public class GameMode {

    
    private int teamLimit;
    private String modeName;

    public GameMode(int teamLimit, String modeName) {
        this.teamLimit = teamLimit;
        this.modeName = modeName;
    }

    
    public String getModeName() {
        return this.modeName;
    }

    public int getTeamLimit() {
        return this.teamLimit;
    }

}
