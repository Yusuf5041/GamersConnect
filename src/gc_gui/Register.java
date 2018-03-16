package gc_gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register {

	public Register(String username, String password, String discordID) {
		// TODO - implement Register.Register
		          addUser(createUserAccount(username, password, discordID));
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
                String line = newUser.getUsername()+" "+newUser.getPassword()+" "+newUser.getDiscordID();
                writeFile(line);
            } catch (IOException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
        
        private void writeFile(String line){
         BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter("D://Uni Work/GamersConnect/userlist.txt",true));
                writer.newLine();
                writer.append(line);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }

}