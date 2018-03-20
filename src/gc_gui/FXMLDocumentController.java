/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gc_gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Main
 */
public class FXMLDocumentController implements Callback, Initializable {

    @FXML
    private AnchorPane gc_login, gc_main, gc_register, gc_guest_search, gc_create_form, gc_profile;

    @FXML
    private GridPane game_details;
    @FXML
    private JFXButton loginButton, signupButton, registerButton, backButton, backButton2, guestButton, createButton, createLobbyButton, saveProfileButton, addGameButton, addButton, createBack, addBack, searchUserButton;

    @FXML
    private Button signupButton2;

    @FXML
    private JFXTextField usernameField, passwordField, newUsernameField, newPasswordField, newDiscordField, guestSearchField, formLobbyTitle, searchField;

    @FXML
    private TextField profileUsernameField, profileDiscordField, gameIdField;

    @FXML
    private ComboBox<String> formGame, formRank, formSize, formMode;

    @FXML
    private TextArea suggestedUsersArea;

    @FXML
    private TableView<Lobby> lobbyTable;

    @FXML
    private TableColumn titleCol, gameCol, modeCol, rankCol, sizeCol, idColumn;

    @FXML
    private Label addGameLabel, createLobbyLabel;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab hostLobbies;

    private Player user;

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        if (event.getTarget() == loginButton) {
            ArrayList<Player> users = UserAccountList.getInstance().getUserList();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(usernameField.getText())) {
                    if (users.get(i).getPassword().equals(passwordField.getText())) {
                        gc_login.setVisible(false);
                        gc_main.setVisible(true);
                        user = users.get(i);
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Password");
                        break;
                    }
                }
            }

        }
    }

    @FXML
    private void handleSignupAction(ActionEvent event) {
        if (event.getTarget() == signupButton || event.getTarget() == signupButton2) {
            gc_login.setVisible(false);
            gc_register.setVisible(true);
        }
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
        if (event.getTarget() == backButton || event.getTarget() == backButton2) {
            gc_login.setVisible(true);
            gc_register.setVisible(false);
            gc_guest_search.setVisible(false);
        } else if (event.getTarget() == createBack) {
            gc_create_form.setVisible(false);
            gc_main.setVisible(true);
        }

    }

    @FXML
    private void handleRegisterAction(ActionEvent event) {
        if (event.getTarget() == registerButton) {
            ArrayList<Player> users;
            try {
                users = UserAccountList.getInstance().getUserList();
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(newUsernameField.getText())) {
                        JOptionPane.showMessageDialog(null, "Username taken!");
                        return;
                    }
                }

                Register register = new Register(newUsernameField.getText(), newPasswordField.getText(), newDiscordField.getText());
                user = register.getNewPlayer();
                gc_register.setVisible(false);
                gc_main.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void handleGuestButton(ActionEvent event) {
        if (event.getTarget() == guestButton) {
            gc_login.setVisible(false);
            gc_guest_search.setVisible(true);
        }
    }

    @FXML
    private void handleAutoComplete(KeyEvent event) {
        if (event.getTarget() == searchField) {
            ArrayList<Player> users;
            int count = 0;
            String text = "";
            try {
                users = UserAccountList.getInstance().getUserList();
                for (int i = 0; i < users.size(); i++) {
                    if (!(searchField.getText().length() < 1)) {
                        if (users.get(i).getUsername().startsWith(searchField.getText())) {
                            count++;
                            text += "\n" + users.get(i).getUsername();
                        }
                    }

                }
                if (count > 0) {
                    suggestedUsersArea.setVisible(true);
                    suggestedUsersArea.setText(text);
                } else {
                    suggestedUsersArea.setVisible(false);
                    suggestedUsersArea.setText("");
                }
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleCreationButton(ActionEvent event) {
        if (event.getTarget() == createButton) {
            gc_main.setVisible(false);
            gc_create_form.setVisible(true);
            //for lobby
            createLobbyLabel.setVisible(true);
            addGameLabel.setVisible(false);

            formLobbyTitle.setVisible(true);
            gameIdField.setVisible(false);

            formMode.setVisible(true);
            formSize.setVisible(true);

            createLobbyButton.setVisible(true);
            addButton.setVisible(false);

        } else if (event.getTarget() == addGameButton) {
            gc_main.setVisible(false);
            gc_create_form.setVisible(true);
            //for profile
            createLobbyLabel.setVisible(false);
            addGameLabel.setVisible(true);

            formLobbyTitle.setVisible(false);
            gameIdField.setVisible(true);

            formMode.setVisible(false);
            formSize.setVisible(false);

            createLobbyButton.setVisible(false);
            addButton.setVisible(true);
        }
    }

    @FXML
    public void handleCreateLobbyAction(ActionEvent event) {
        
        String title = formLobbyTitle.getText();
        String game = formGame.getValue();
        String mode = formMode.getValue();
        String rank = formRank.getValue();
        String size = formSize.getValue();

        Game g = LobbyList.checkGame(game);
        GameMode gm = LobbyList.checkMode(g, mode);
        
        user.createLobby(title, g, gm, rank, Integer.parseInt(size));
        
        gc_create_form.setVisible(false);
        gc_main.setVisible(true);
        addTableData();
    }

    @FXML
    private void handleJoinButton(ActionEvent event) {
        Lobby l = lobbyTable.getSelectionModel().getSelectedItem();
        boolean flag = true;
        for (int i = 0; i < l.getPlayerList().size(); i++) {
            if (l.getPlayerList().get(i) == user) {
                flag = false;
                break;
            }
        }
        if (flag) {
            user.joinLobby(l);
            JOptionPane.showMessageDialog(null, "Joined Lobby!");
        } else {
            JOptionPane.showMessageDialog(null, "Already in lobby!");
        }
        
    }

    @FXML
    private void handleSearchAction(MouseEvent event) throws IOException {
        if (event.getTarget() == searchUserButton) {
            ArrayList<Player> playerList = UserAccountList.getInstance().getUserList();
            for (int i = 0; i < playerList.size(); i++) {
                if (searchField.getText().equals(playerList.get(i).getUsername())) {
                    updateProfile(playerList.get(i));
                    break;
                }
            }
        } else {
            updateProfile(user);
        }
        
    }

    @FXML
    private void updateProfile(Player p) throws IOException {

        profileUsernameField.setText(p.getUsername());
        profileDiscordField.setText(p.getDiscordID());
        /*for (int j = 1; j <= p.getGameDetails().size(); j++) {
            for (int k = 0; k < 3; k++) {
                game_details.add(new Label("Label "+k+" "+j), k, j);
            }

        }*/

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //gameBox.setItems();
        addTableData();
        addFormData();
    }

    @Override
    public Object call(Object param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addTableData() {
        final ObservableList<Lobby> data = FXCollections.observableArrayList();
        data.addAll(LobbyList.getInstance().getLobbyList());
        titleCol.setCellValueFactory(new PropertyValueFactory<>("lobbyTitle"));
        gameCol.setCellValueFactory(new Callback<CellDataFeatures<Lobby, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Lobby, String> p) {
                return new SimpleStringProperty(p.getValue().getLobbyGame().getGameName());
            }
        });
        modeCol.setCellValueFactory(new Callback<CellDataFeatures<Lobby, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Lobby, String> p) {
                return new SimpleStringProperty(p.getValue().getLobby().getLobbyMode().getModeName());
            }
        });
        rankCol.setCellValueFactory(new PropertyValueFactory<>("lobbyRank"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));

        lobbyTable.setItems(data);
    }

    @FXML
    private void addFormData() {
        ObservableList<String> gameOptions = FXCollections.observableArrayList(
                "Overwatch",
                "CSGO"
        );

        formGame.setItems(gameOptions);

        formRank.getItems().add("Any");
        formMode.getItems().add("Any");
    }

    @FXML
    private void updateFormData() {
        String game = formGame.getValue();
        formRank.getItems().clear();
        formMode.getItems().clear();

        ArrayList<Game> gl = GameList.getInstance().getGameList();
        ArrayList<GameMode> gm = null;
        RankSystem temp = null;
        for (int i = 0; i < gl.size(); i++) {
            if (game.equals(gl.get(i).getGameName())) {
                //fill rank combo box
                temp = gl.get(i).getRankSystem();
                formRank.setValue("Any");
                for (Object val : temp.getRanks().values()) {
                    formRank.getItems().add((String) val);
                }
                formRank.setVisibleRowCount(temp.getRanks().values().size());
                //fill mode combo box
                gm = gl.get(i).getModes();
                for (int j = 0; j < gm.size(); j++) {
                    formMode.getItems().add(gm.get(j).getModeName());
                }
                formMode.setVisibleRowCount(gm.size());
            }
        }

    }

    @FXML
    private void updateSizeList() {
        String game = formGame.getValue();
        String mode = formMode.getValue();
        formSize.getItems().clear();

        ArrayList<Game> gl = GameList.getInstance().getGameList();
        ArrayList<GameMode> gm = null;
        for (int i = 0; i < gl.size(); i++) {
            if (game.equals(gl.get(i).getGameName())) {
                gm = gl.get(i).getModes();
                for (int j = 0; j < gm.size(); j++) {
                    if (gm.get(j).getModeName().equals(mode)) {
                        for (int k = 1; k <= gm.get(j).getTeamLimit(); k++) {
                            formSize.getItems().add(k + "");
                        }
                        formSize.setVisibleRowCount(gm.get(j).getTeamLimit());
                    }
                }
            }
        }

    }


    
}
