/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gc_gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Main
 */
public class UniversityList {
    private static UniversityList instance;
    private ArrayList<String> universityList;
    
    private UniversityList(){
        universityList = new ArrayList<>();
        readFile();
    }
    
    public static UniversityList getInstance(){
       if(instance == null){
           instance = new UniversityList();
       }
       return instance;
    }
    
    public ArrayList<String> getUniversityList(){
        return universityList;
    }
    
    private void readFile(){
       FileReader in = null;
        try {
            in = new FileReader("lib/unilist.txt");
            BufferedReader br = new BufferedReader(in);
            String name;
            while((name=br.readLine()) != null){            
                universityList.add(name);
            }   return;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error: File could not be found");
            Logger.getLogger(GameList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: File could not be read");
            Logger.getLogger(GameList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error closing file");
                Logger.getLogger(GameList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
