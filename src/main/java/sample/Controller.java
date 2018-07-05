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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int defFirstId = 77903915;
        int defSecondId = 864318;

        firstIdTextField.setText(Integer.toString(defFirstId));
        secondIdTextField.setText(Integer.toString(defSecondId));

        searchButton.setOnAction(event -> searchFriends());

        friendsTableView.setPrefWidth(500);

        TableColumn<Friend, String> userIdColumn = new TableColumn<>("id");
        TableColumn<Friend, String> userFullNameColumn = new TableColumn<>("FullName");
        TableColumn<Friend, String> userFirstNameColumn = new TableColumn<>("FirstName");
        TableColumn<Friend, String> userLastNameColumn = new TableColumn<>("LastName");
        TableColumn<Friend, String> userBooksColumn = new TableColumn<>("Books");
        TableColumn<Friend, String> userInterestsColumn = new TableColumn<>("Interests");
        TableColumn<Friend, String> sexColumn = new TableColumn<>("sex");

        userFullNameColumn.getColumns().addAll(userFirstNameColumn, userLastNameColumn);
        friendsTableView.getColumns().addAll(userIdColumn, userFullNameColumn, sexColumn, userInterestsColumn, userBooksColumn);

        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        userLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userInterestsColumn.setCellValueFactory(new PropertyValueFactory<>("interests"));
        userBooksColumn.setCellValueFactory(new PropertyValueFactory<>("books"));

//        Friend f1 = new Friend("Sasa", 345);
//        ObservableList<Friend> tableList = FXCollections.observableArrayList();
//        tableList.add(f1);
//        friendsTableView.setItems(tableList);

    }

    private void searchFriends(){
        int firstId = Integer.parseInt(firstIdTextField.getText());
        int secondId = Integer.parseInt(secondIdTextField.getText());


        List<Friend> firstFriends =  VK_API.doIt(firstId);
        List<Friend> secondFriends = VK_API.doIt(secondId);
        List<Friend> commonFriends = getCommonFriends(firstFriends, secondFriends);
        //List<Friend> friends = getFriendsInfo(friendsIds);
        printFriends(commonFriends);
        //printFriends(friends);
    }

    private List<Friend> getCommonFriends(List<Friend> firstFriends, List<Friend> secondFriends){
        List<Friend> out = new ArrayList<>();
        for (int tmp = firstFriends.size() - 1; tmp >= 0; tmp--){
            if(secondFriends.contains(firstFriends.get(tmp)))out.add(firstFriends.get(tmp));
        }
        return out;
    }

    private void printFriends(List<Friend> friends) {
        ObservableList<Friend> friendsList = FXCollections.observableArrayList(friends);
        System.out.println(friends);
        friendsTableView.setItems(friendsList);
//        for (Friend friend : friends){
//            System.out.println(friend.id + " " + friend.name);
//        };
    }

    //private List<Friend> getFriendsInfo(List<String> friendsIds) {
       // Friend support = new Friend("Mipo Shavkatovich Eul", "males");
        //Friend carry = new Friend("Ogre Magi", "males");
        //List<Friend> team = new ArrayList<>(Arrays.asList(support, carry));
        //return team;
    //}
}
