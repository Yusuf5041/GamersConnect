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
import com.sun.java.accessibility.util.SwingEventMonitor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
import javafx.scene.paint.Paint;

/**
 *
 * @author Main
 */
public class FXMLDocumentController implements Callback, Initializable {

    @FXML
    private AnchorPane gc_login, gc_main, gc_register, gc_guest_search, gc_create_form, gc_profile, gc_lobby;

    @FXML
    private GridPane game_details;
    @FXML
    private JFXButton loginButton, signupButton, registerButton, backButton, backButton2, guestButton, createButton, createLobbyButton, saveProfileButton, addGameButton, addButton, createBack, addBack,
            searchUserButton, profileBack, guestSearchButton, guestBack, confirmLobbyButton, removePlayerButton, viewButton, deleteButton;

    @FXML
    private Button signupButton2, viewProfileButton;

    @FXML
    private JFXTextField usernameField, passwordField, newUsernameField, newPasswordField, newDiscordField, guestSearchField, formLobbyTitle, searchField, userSearchField;

    @FXML
    private TextField profileUsernameField, profileDiscordField, profileUniversityField, formGameID, displayUserField, lobbyTitleField;

    @FXML
    private ComboBox<String> formGame, formRank, formSize, formMode, searchGameBox, searchModeBox, searchRankBox, searchUniversityBox, registerUniBox;

    @FXML
    private TextArea suggestedUsersArea, suggestedUsersArea2;

    @FXML
    private TableView<Lobby> lobbyTable, myLobbyTable;

    @FXML
    private TableView<Player> lobbyPlayerTable;

    @FXML
    private TableColumn titleCol, gameCol, modeCol, rankCol, sizeCol, idColumn, userColumn, rankColumn, gIDColumn, discordColumn;

    @FXML
    private Label addGameLabel, createLobbyLabel;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab hostLobbies;

    private Player user;
    private ArrayList<String> games;

    //handles the login process once the button is pressed
    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        if (event.getTarget() == loginButton) {
            ArrayList<Player> users = UserAccountList.getInstance().getUserList();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(usernameField.getText())) {
                    if (users.get(i).getPassword().equals(passwordField.getText())) {
                        gc_login.setVisible(false);
                        gc_main.setVisible(true);
                        user = users.get(i); //assigns reference to user object 
                        displayUserField.setText(user.getUsername());
                        if (user.getRole() instanceof HostRole) { //determines weather or not host lobby data neds to be displayed
                            HostRole hr = (HostRole) user.getRole();
                            addLobbyTableData(hr.getUserLobbies(), myLobbyTable);
                            hostLobbies.setDisable(false);
                        } else {
                            hostLobbies.setDisable(true);
                        }
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
        if (event.getTarget() == backButton || event.getTarget() == backButton2 || event.getTarget() == guestBack) {
            gc_login.setVisible(true);
            gc_register.setVisible(false);
            gc_guest_search.setVisible(false);
            gc_profile.setVisible(false);
        } else if (event.getTarget() == createBack) {
            gc_create_form.setVisible(false);
            gc_main.setVisible(true);
        } else if (event.getTarget() == profileBack) {
            gc_profile.setVisible(false);
            gc_main.setVisible(true);
        }

    }

    @FXML
    private void handleRegisterAction(ActionEvent event) {
        if (event.getTarget() == registerButton) {
            ArrayList<Player> users;
            try {
                if (newUsernameField.getText().length() < 8 || newPasswordField.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Username/Password must be longer than 8 letters");
                } else {
                    users = UserAccountList.getInstance().getUserList();
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getUsername().equals(newUsernameField.getText())) {
                            JOptionPane.showMessageDialog(null, "Username taken!");
                            return;
                        }
                    }

                    if (registerUniBox != null && !registerUniBox.equals("")) { //ensures university box has a value selected
                        Register register = new Register(newUsernameField.getText(), newPasswordField.getText(), newDiscordField.getText(), registerUniBox.getValue());
                        user = register.getNewPlayer();
                        displayUserField.setText(user.getUsername());
                        gc_register.setVisible(false);
                        gc_main.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all necessary fields");
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Please fill in all necessary fields");
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
        ArrayList<Player> users;
        int count = 0;
        String text = "";
        if (event.getTarget() == searchField) {

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
        } else if (event.getTarget() == userSearchField) {
            try {
                users = UserAccountList.getInstance().getUserList();
                for (int i = 0; i < users.size(); i++) {
                    if (!(userSearchField.getText().length() < 1)) {
                        if (users.get(i).getUsername().startsWith(userSearchField.getText())) {
                            count++;
                            text += "\n" + users.get(i).getUsername();
                        }
                    }

                }
                if (count > 0) {
                    suggestedUsersArea2.setVisible(true);
                    suggestedUsersArea2.setText(text);
                } else {
                    suggestedUsersArea2.setVisible(false);
                    suggestedUsersArea2.setText("");
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
            formGameID.setVisible(false);

            formMode.setVisible(true);
            formSize.setVisible(true);

            createLobbyButton.setVisible(true);
            addButton.setVisible(false);

        } else if (event.getTarget() == addGameButton) {
            gc_profile.setVisible(false);
            gc_create_form.setVisible(true);
            //for profile
            createLobbyLabel.setVisible(false);
            addGameLabel.setVisible(true);

            formLobbyTitle.setVisible(false);
            formGameID.setVisible(true);

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

        user.createLobby(title, g, gm, rank, user.getUniversity(), Integer.parseInt(size), user.getUsername());
        if (user.getRole() instanceof HostRole) {
            HostRole hr = (HostRole) user.getRole();
            addLobbyTableData(hr.getUserLobbies(), myLobbyTable);
        }
        hostLobbies.setDisable(false);
        gc_create_form.setVisible(false);
        gc_main.setVisible(true);
        addLobbyTableData(LobbyList.getInstance().getLobbyList(), lobbyTable);
    }

    @FXML
    private void handleAddGameAction(ActionEvent event) throws IOException {
        Game g = LobbyList.checkGame(formGame.getValue());
        String rank = formRank.getValue();
        String gID = formGameID.getText();
        if (user.getGameDetails().size() < 3) {
            user.addGameDetails(g, gID, rank);
            updateProfile(user);
        } else {
            JOptionPane.showMessageDialog(null, "Maximum number of games reached. Cannot add anymore!");
        }

        gc_create_form.setVisible(false);
        gc_profile.setVisible(true);
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
    private void handleSearchButton(ActionEvent event) throws IOException {
        ArrayList<Player> playerList = UserAccountList.getInstance().getUserList();
        if (event.getTarget() == searchUserButton) {

            for (int i = 0; i < playerList.size(); i++) {
                if (userSearchField.getText().equals(playerList.get(i).getUsername())) {
                    updateProfile(playerList.get(i));
                    gc_main.setVisible(false);
                    gc_profile.setVisible(true);
                    profileBack.setVisible(true);
                    guestBack.setVisible(false);
                    addGameButton.setVisible(false);
                    saveProfileButton.setVisible(false);
                    break;
                }
            }
        } else if (event.getTarget() == guestSearchButton) {
            for (int i = 0; i < playerList.size(); i++) {
                if (searchField.getText().equals(playerList.get(i).getUsername())) {
                    updateProfile(playerList.get(i));
                    gc_guest_search.setVisible(false);
                    gc_profile.setVisible(true);
                    profileBack.setVisible(false);
                    guestBack.setVisible(true);
                    addGameButton.setVisible(false);
                    saveProfileButton.setVisible(false);
                    break;
                }
            }
        }

    }

    @FXML
    private void handleViewProfileAction(ActionEvent event) throws IOException {
        gc_main.setVisible(false);
        gc_profile.setVisible(true);
        profileBack.setVisible(true);
        guestBack.setVisible(false);
        addGameButton.setVisible(true);
        saveProfileButton.setVisible(true);
        updateProfile(user);
    }

    @FXML
    private void handleDeleteAction(ActionEvent e) {
        Lobby l = myLobbyTable.getSelectionModel().getSelectedItem();
        HostRole hr = (HostRole) user.getRole();
        hr.removeUserLobby(l);
        addLobbyTableData(LobbyList.getInstance().getLobbyList(), lobbyTable);
        addLobbyTableData(hr.getUserLobbies(), myLobbyTable);

        if (hr.getUserLobbies().size() == 0) {
            user.resetRole();
            hostLobbies.setDisable(true);
        }
    }

    @FXML
    private void handleViewLobbyAction(ActionEvent event) {
        Lobby l = myLobbyTable.getSelectionModel().getSelectedItem();
        lobbyTitleField.setText(l.getLobbyTitle());
        addPlayerTableData(lobbyPlayerTable, l);
        gc_lobby.setVisible(true);
        gc_main.setVisible(false);
    }

    @FXML
    private void handleRemovePlayerAction(ActionEvent event) {

    }

    @FXML
    private void handleConfirmLobbyAction(ActionEvent event) throws IOException {
        Runtime runtime = Runtime.getRuntime();     //getting Runtime object

        try {
            runtime.exec("C:\\Users\\Main\\AppData\\Local\\Discord\\app-0.0.300\\Discord.exe");        //opens new notepad instance

        } catch (IOException e) {

            Desktop.getDesktop().browse(URI.create("https://discordapp.com/"));
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFilterAction(ActionEvent event) {
        try {
            LobbyList.filterList(searchGameBox.getValue(), searchModeBox.getValue(), searchRankBox.getValue(), searchUniversityBox.getValue());
            addLobbyTableData(LobbyList.getInstance().getFilteredList(), lobbyTable);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Fill in all parts of search filter");
        }

    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        addLobbyTableData(LobbyList.getInstance().getLobbyList(), lobbyTable);
        searchGameBox.getSelectionModel().clearSelection();
        searchModeBox.getSelectionModel().clearSelection();
        searchRankBox.getSelectionModel().clearSelection();
        searchUniversityBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void updateProfile(Player p) throws IOException {
        Paint paint = Paint.valueOf("#e9f814");

        profileUsernameField.setText(p.getUsername());
        profileDiscordField.setText(p.getDiscordID());
        profileUniversityField.setText(p.getUniversity());

        game_details.getChildren().remove(3, game_details.getChildren().size());
        for (int i = 0; i < p.getGameDetails().size(); i++) {

            game_details.add(new Label(p.getGameDetails().get(i).getGame().getGameName()), 0, i + 1);
            game_details.add(new Label(p.getGameDetails().get(i).getGamerID()), 1, i + 1);
            game_details.add(new Label(p.getGameDetails().get(i).getUserRank()), 2, i + 1);

        }
        ObservableList<Node> ol = game_details.getChildren();
        for (Node node : ol) {
            if (node instanceof Label) {
                Label l = (Label) node;
                l.setTextFill(paint);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //gameBox.setItems();
        addLobbyTableData(LobbyList.getInstance().getLobbyList(), lobbyTable);

        games = new ArrayList<>();
        for (Game game : GameList.getInstance().getGameList()) {
            games.add(game.getGameName());
        }
        addFormData();
    }

    @Override
    public Object call(Object param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addLobbyTableData(ArrayList<Lobby> lobList, TableView<Lobby> tab) {
        if (lobList.size() > 0) {
            final ObservableList<Lobby> data = FXCollections.observableArrayList();
            TableColumn gameC = tab.getColumns().get(1);
            TableColumn modeC = tab.getColumns().get(2);
            data.addAll(lobList);

            tab.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("lobbyTitle"));
            gameC.setCellValueFactory(new Callback<CellDataFeatures<Lobby, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Lobby, String> p) {
                    return new SimpleStringProperty(p.getValue().getLobbyGame().getGameName());
                }
            });
            modeC.setCellValueFactory(new Callback<CellDataFeatures<Lobby, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Lobby, String> p) {
                    return new SimpleStringProperty(p.getValue().getLobby().getLobbyMode().getModeName());
                }
            });
            tab.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lobbyRank"));
            tab.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("lobbyUniversity"));
            tab.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("size"));

            tab.setItems(data);
        } else {
            JOptionPane.showMessageDialog(null, "No lobbies available");
        }
    }

    @FXML
    private void addPlayerTableData(TableView<Player> tab, Lobby lob) {
        final ObservableList<Player> data = FXCollections.observableArrayList();

        TableColumn rankCol = tab.getColumns().get(1);
        TableColumn gIDCol = tab.getColumns().get(2);

        data.addAll(lob.getPlayerList());

        tab.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("username"));
        rankCol.setCellValueFactory(new Callback<CellDataFeatures<Player, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Player, String> p) {
                return new SimpleStringProperty(p.getValue().getRank(lob.getLobbyGame()));
            }
        });

        gIDCol.setCellValueFactory(new Callback<CellDataFeatures<Player, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Player, String> p) {
                return new SimpleStringProperty(p.getValue().getGamerID(lob.getLobbyGame()));
            }
        });

        tab.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("discordID"));

        tab.setItems(data);
    }

    //adds initial set of data to form
    @FXML
    private void addFormData() {

        ObservableList<String> gameOptions = FXCollections.observableArrayList(games);
        ObservableList<String> unis = FXCollections.observableArrayList(UniversityList.getInstance().getUniversityList());
        formGame.setItems(gameOptions);
        searchGameBox.setItems(gameOptions);

        searchUniversityBox.setItems(unis);
        searchUniversityBox.getItems().add("Any");

        registerUniBox.setItems(unis);
        registerUniBox.getItems().add("None");
    }

    //allows dynamic changing of combo boxes when creating lobby
    @FXML
    private void updateCreateFormData() {
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

    //allows dynamic changing of combo boxes when filtering lobbies
    @FXML
    private void updateFilterFormData() {
        String game = searchGameBox.getValue();
        if (game != null) {
            searchRankBox.getItems().clear();
            searchModeBox.getItems().clear();

            ArrayList<Game> gl = GameList.getInstance().getGameList();
            ArrayList<GameMode> gm = null;
            RankSystem temp = null;
            for (int i = 0; i < gl.size(); i++) {
                if (game.equals(gl.get(i).getGameName())) {
                    //fill rank combo box
                    temp = gl.get(i).getRankSystem();

                    for (Object val : temp.getRanks().values()) {
                        searchRankBox.getItems().add((String) val);
                    }
                    searchRankBox.setVisibleRowCount(temp.getRanks().values().size());
                    //fill mode combo box
                    gm = gl.get(i).getModes();
                    for (int j = 0; j < gm.size(); j++) {
                        searchModeBox.getItems().add(gm.get(j).getModeName());
                    }
                    searchModeBox.setVisibleRowCount(gm.size());
                }
            }
        }

    }

    //chnages items in combo box depending on game mode chosen
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
