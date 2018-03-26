package gc_gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Register {

    private Player newUser;

    public Register(String username, String password, String discordID, String uni) {
        // TODO - implement Register.Register
        newUser = createUserAccount(username, password, discordID);
        newUser.setUniversity(uni);
        addUser(newUser);
        
    }

    private Player createUserAccount(String username, String password, String discordID) {
        // TODO - implement Register.createUserAccount
        return new Player(username, password, discordID);

    }

    /**
     *
     * @param newUser
     */
    private void addUser(Player newUser) {
        try {
            // TODO - implement Register.addUser
            UserAccountList.getInstance().getUserList().add(newUser);
            String line = newUser.getUsername() + " " + newUser.getPassword() + " " + newUser.getDiscordID();
            writeFile(line);
            writeFile(newUser.getUniversity());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error handling file");
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Player getNewPlayer() {
        return newUser;
    }

    private void writeFile(String line) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("lib/userlist.txt", true));
            writer.newLine();
            writer.append(line);
            writer.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error handling file");
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
