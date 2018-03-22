package gc_gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;


public class UserAccountList {

    private static UserAccountList instance;
    private ArrayList<Player> userList;

    protected UserAccountList() throws IOException {
        this.userList = new ArrayList<>();
        readFile();
        
    }

    public static void main(String[] args) throws IOException{
        ArrayList<Player> userList1 = new UserAccountList().getUserList();
        for(int i=0;i<userList1.size();i++){
            System.out.println(userList1.get(i).getUsername());
        }
    }
    public ArrayList getUserList() throws IOException {
        return this.userList;

    }

    public static UserAccountList getInstance() throws IOException {
        if (instance == null) {
            instance = new UserAccountList();
        }
        return instance;
    }

    private void readFile() throws FileNotFoundException, IOException {
        System.out.println(System.getProperty("user.dir"));
        FileReader in = new FileReader("lib/userlist.txt");
        BufferedReader br = new BufferedReader(in);
        String line;
        while((line=br.readLine()) != null){
            addPlayers(line);
            userList.get(userList.size()-1).setUniversity(br.readLine());
        }
        return;
        
    }

    private void addPlayers(String line){
        String parts[] = line.split(" ");
        Player p = new Player(parts[0], parts[1], parts[2]);
        userList.add(p);
        if(parts[3] != null){
            for (int i = 3; i < parts.length; i+=3) {
                p.addGameDetails(LobbyList.checkGame(parts[i]), parts[i+1], parts[i+2]);
            }
        }
        return;
    }
    
    
}
