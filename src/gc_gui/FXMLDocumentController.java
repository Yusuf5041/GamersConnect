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

/**
 *
 * @author Main
 */
public class FXMLDocumentController implements Callback, Initializable {

    @FXML
    private AnchorPane gc_login, gc_main, gc_register, gc_guest_search;

    @FXML
    private JFXButton loginButton, signupButton, registerButton, backButton, backButton2, guestButton;

    @FXML
    private JFXTextField usernameField, passwordField, newUsernameField, newPasswordField, newDiscordField, searchField;

    @FXML
    private JFXComboBox gameBox;

    @FXML
    private TextArea suggestedUsersArea;

    @FXML
    private TableView<Lobby> lobbyTable;

    @FXML
    private TableColumn titleCol, gameCol, modeCol, rankCol, sizeCol;

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        if (event.getTarget() == loginButton) {
            ArrayList<Player> users = UserAccountList.getInstance().getUserList();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(usernameField.getText())) {
                    if (users.get(i).getPassword().equals(passwordField.getText())) {
                        gc_login.setVisible(false);
                        gc_main.setVisible(true);
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
        if (event.getTarget() == signupButton) {
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

                new Register(newUsernameField.getText(), newPasswordField.getText(), newDiscordField.getText());
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //gameBox.setItems();

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
                return new SimpleStringProperty(p.getValue().getLobbyGame().getGameMode().getModeName());
            }
        });
        rankCol.setCellValueFactory(new PropertyValueFactory<>("lobbyRank"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));

        lobbyTable.setItems(data);
    }

    @Override
    public Object call(Object param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
