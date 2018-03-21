package gc_gui;

import java.util.ArrayList;

public class HostRole extends Role {

    private ArrayList<Lobby> userLobbies;

    public HostRole() {
        
        userLobbies = new ArrayList<>();
    }

    public ArrayList<Lobby> getUserLobbies() {
        // TODO - implement HostRole.getUserLobbies
        return userLobbies;
    }

    /**
     *
     * @param newLobby
     */
    public void addUserLobby(Lobby newLobby) {
        // TODO - implement HostRole.addUserLobby
        userLobbies.add(newLobby);
    }

    /**
     *
     * @param lobbyName
     */
    public void removeUserLobby(Lobby lobbyName) {
        // TODO - implement HostRole.removeUserLobby
        userLobbies.remove(lobbyName);
        LobbyList.getInstance().removeLobby(lobbyName);
          
    }

    /**
     *
     * @param lobbyName
     * @param user
     */
    public void removeUser(Lobby lobbyName, Player user) {
        // TODO - implement HostRole.removeUser
        for (int i = 0; i < userLobbies.size(); i++) {
            if(lobbyName==userLobbies.get(i)){
                for (int j = 0; j < userLobbies.get(i).getPlayerList().size(); j++) {
                    if(user==userLobbies.get(i).getPlayerList().get(j)){
                        userLobbies.get(i).getPlayerList().remove(j);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     *
     * @param lobbyName
     */
    /*
    public ConfirmedLobby confirmLobby(Lobby lobbyName) {
        // TODO - implement HostRole.confirmLobby
        throw new UnsupportedOperationException();
    }*/

}
