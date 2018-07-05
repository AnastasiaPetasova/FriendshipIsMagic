package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable{

    @FXML
    TextField firstIdTextField;

    @FXML
    TextField secondIdTextField;

    @FXML
    Button searchButton;

    @FXML
    TableView friendsTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchButton.setOnAction(event -> searchFriends());
    }

    private void searchFriends(){
        String firstId = firstIdTextField.getText();
        String secondId = secondIdTextField.getText();

        VK_API.doIt();

//        List<String> friendsIds = getFriendsIds(firstId, secondId);
//
//        List<Friend> friends = getFriendsInfo(friendsIds);
//
//        printFriends(friends);
    }

    private void printFriends(List<Friend> friends) {
        throw new UnsupportedOperationException();
    }

    private List<Friend> getFriendsInfo(List<String> friendsIds) {
        Friend support = new Friend("Mipo Shavkatovich Eul", "males");
        Friend carry = new Friend("Ogre Magi", "males");
        List<Friend> team = new ArrayList<>(Arrays.asList(support, carry));
        return team;
    }

    private List<String> getFriendsIds(String firstId, String secondId) {
        return new ArrayList<>();
    }
}
