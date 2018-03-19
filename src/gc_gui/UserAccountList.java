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
        FileReader in = new FileReader("C://Users/Main/Documents/GamersConnect/lib/userlist.txt");
        BufferedReader br = new BufferedReader(in);
        String line;
        while((line=br.readLine()) != null){
            addPlayers(line);
        }
        return;
        
    }

    private void addPlayers(String line){
        String parts[] = line.split(" ");
        /*ArrayList<UserGame> ug = new ArrayList<UserGame>();
            ug.add(new UserGame(parts[2],parts[3],parts[4]));
        */
        userList.add(new Player(parts[0], "test", "test"));
        return;
    }
    
    
}
