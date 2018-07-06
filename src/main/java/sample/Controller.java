package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;


public class Controller implements Initializable{

    @FXML
    TextField firstIdTextField;

    @FXML
    TextField secondIdTextField;

    @FXML
    Button searchButton;

    @FXML
    TableView<Friend> friendsTableView;

    @FXML
    TableColumn<Friend, Integer> userIdColumn;

    @FXML
    TableColumn<Friend, String> userFirstNameColumn;

    @FXML
    TableColumn<Friend, String> userLastNameColumn;

    @FXML
    TableColumn<Friend, String> userSexColumn;

    @FXML
    TableColumn<Friend, String> userInterestsColumn;

    @FXML
    TableColumn<Friend, String> userBooksColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int defFirstId = 77903915;
        int defSecondId = 864318;

        firstIdTextField.setText(Integer.toString(defFirstId));
        secondIdTextField.setText(Integer.toString(defSecondId));

        searchButton.setOnAction(event -> searchFriends());

        friendsTableView.setPrefWidth(500);

        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        userLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userSexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        userInterestsColumn.setCellValueFactory(new PropertyValueFactory<>("interests"));
        userBooksColumn.setCellValueFactory(new PropertyValueFactory<>("books"));
    }

    private void searchFriends(){
        int firstId = Integer.parseInt(firstIdTextField.getText());
        int secondId = Integer.parseInt(secondIdTextField.getText());

        List<Integer> firstFriendIds =  VK_API.getFriendIds(firstId);
        List<Integer> secondFriendIds = VK_API.getFriendIds(secondId);
        List<Integer> commonFriendIds = getCommonFriends(firstFriendIds, secondFriendIds);

        List<Friend> commonFriends = VK_API.getFriendsInfo(commonFriendIds);
        printFriends(commonFriends);
    }

    public static List<Integer> getCommonFriends(List<Integer> firstFriends, List<Integer> secondFriends){
        List<Integer> out = new ArrayList<>();
        for (int tmp = firstFriends.size() - 1; tmp >= 0; tmp--){
            if(secondFriends.contains(firstFriends.get(tmp)))out.add(firstFriends.get(tmp));
        }
        return out;
    }

    private void printFriends(List<Friend> friends) {
        ObservableList<Friend> friendsList = FXCollections.observableArrayList(friends);
        friendsTableView.setItems(friendsList);
    }
}
