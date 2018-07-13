package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.Queue;


public class Controller implements Initializable{

    @FXML
    ImageView imageFirstFriend;

    @FXML
    ImageView imageSecondFriend;

    @FXML
    TextField firstIdTextField;

    @FXML
    TextField secondIdTextField;

    @FXML
    javafx.scene.control.Label label;

    @FXML
    Button searchButton;

    @FXML
    public TableView<Friend> friendsTableView;

    @FXML
    TableView<Friend> startUsersTableView;

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

    @FXML
    TableColumn<Friend, Integer> startUserIdColumn;

    @FXML
    TableColumn<Friend, String> startUserFirstNameColumn;

    @FXML
    TableColumn<Friend, String> startUserLastNameColumn;

    @FXML
    TableColumn<Friend, String> startUserSexColumn;

    @FXML
    TableColumn<Friend, String> startUserInterestsColumn;

    @FXML
    TableColumn<Friend, String> startUserBooksColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int defFirstId = 100296488;//77903915;
        int defSecondId = 189186510;//864318;

        firstIdTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    firstIdTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        secondIdTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    secondIdTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        firstIdTextField.setText(Integer.toString(defFirstId));
        secondIdTextField.setText(Integer.toString(defSecondId));

        searchButton.setOnAction(event -> searchFriends());
        startUsersTableViewInitialize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        friendsTableView.setPrefSize(
                screenSize.width * Main.SCENE_SIZE_COEFF * 0.9,
                screenSize.height * Main.SCENE_SIZE_COEFF * 0.6
        );

        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        userLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userSexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        userInterestsColumn.setCellValueFactory(new PropertyValueFactory<>("interests"));
        userBooksColumn.setCellValueFactory(new PropertyValueFactory<>("books"));

        double tableWidth = friendsTableView.getPrefWidth();

        userIdColumn.setPrefWidth(tableWidth * 0.1);
        userFirstNameColumn.setPrefWidth(tableWidth * 0.15);
        userLastNameColumn.setPrefWidth(tableWidth * 0.15);
        userSexColumn.setPrefWidth(tableWidth * 0.1);

        double leftWidth = userIdColumn.getPrefWidth() + userFirstNameColumn.getPrefWidth() + userLastNameColumn.getPrefWidth() + userSexColumn.getPrefWidth();

        double rightWidth = tableWidth - leftWidth;

        userInterestsColumn.setPrefWidth(rightWidth / 2);
        userBooksColumn.setPrefWidth(rightWidth / 2);
    }

    private void startUsersTableViewInitialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        startUsersTableView.setPrefSize(
                screenSize.width * Main.SCENE_SIZE_COEFF * 0.9,
                screenSize.height * Main.SCENE_SIZE_COEFF * 0.6
        );

        startUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        startUserFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startUserLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        startUserSexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        startUserInterestsColumn.setCellValueFactory(new PropertyValueFactory<>("interests"));
        startUserBooksColumn.setCellValueFactory(new PropertyValueFactory<>("books"));

        double tableWidth = startUsersTableView.getPrefWidth();

        startUserIdColumn.setPrefWidth(tableWidth * 0.1);
        startUserFirstNameColumn.setPrefWidth(tableWidth * 0.15);
        startUserLastNameColumn.setPrefWidth(tableWidth * 0.15);
        startUserSexColumn.setPrefWidth(tableWidth * 0.1);

        double leftWidth = startUserIdColumn.getPrefWidth() + startUserFirstNameColumn.getPrefWidth() + startUserLastNameColumn.getPrefWidth() + startUserSexColumn.getPrefWidth();

        double rightWidth = tableWidth - leftWidth;

        startUserInterestsColumn.setPrefWidth(rightWidth / 2);
        startUserBooksColumn.setPrefWidth(rightWidth / 2);
    }

    private void searchFriends(){
        int firstId = Integer.parseInt(firstIdTextField.getText());
        int secondId = Integer.parseInt(secondIdTextField.getText());

        List<Integer> firstFriendIds =  VK_API.getFriendIds(firstId);
        List<Integer> secondFriendIds = VK_API.getFriendIds(secondId);
        List<Integer> commonFriendIds = getCommonFriends(firstFriendIds, secondFriendIds);

        List<Friend> baseUsers = VK_API.getFriendsInfo(Arrays.asList(firstId, secondId));
        List<String> photoLinks = VK_API.getPhotoLinks(Arrays.asList(firstId, secondId));
        printImage(photoLinks);
        printBaseUsers(baseUsers);



        if (commonFriendIds.size() != 0){
            List<Friend> commonFriends = VK_API.getFriendsInfo(commonFriendIds);
            printFriends(commonFriends);
            label.setText("Mutual Friends");
        }else {
            List<Integer> listChain = searchChain(firstId, secondId);
            List<Friend> chainFriends = VK_API.getFriendsInfo(listChain);
            if (chainFriends == null)label.setText("There is no Chain of Friends");
            else label.setText("Chain of Friends");
            printFriends(chainFriends);
        }
    }

    private List<Integer> searchChain(int firstId, int secondId) {
        List<Integer> chainFriends = new ArrayList<>();
        Map<Integer, Integer> parentById = new HashMap<>();
        Map<Integer, Integer> depthById = new HashMap<>();
        Queue<Integer> queueIds = new ArrayDeque<>();

        queueIds.add(firstId);
        parentById.put(firstId, null);
        depthById.put(firstId, 0);
        boolean flag = false;

        while (!flag && queueIds.size() != 0){
            int curId = queueIds.poll();
            if(curId == secondId) {
                flag = true;
                break;
            }

            int curDepth = depthById.get(curId);
            List<Integer> friends = VK_API.getFriendIds(curId);
            for (Integer friend : friends){
                if (depthById.containsKey(friend))continue;

                if (curDepth < 2 || friend == secondId) {
                    parentById.put(friend, curId);
                    depthById.put(friend, curDepth + 1);
                    queueIds.add(friend);
                }
            }
        }
        if(!flag){
            return null;
        }
        for (int j = secondId; j != firstId && parentById.get(j) != firstId; ){
            chainFriends.add(parentById.get(j));
            j = parentById.get(j);
        }
        Collections.reverse(chainFriends);
        return chainFriends;
    }

    private void printImage(List<String> photoLinks) {
        Image imageFirstFriendImage = new Image(photoLinks.get(0));
        Image imageSecondFriendImage = new Image(photoLinks.get(1));
        imageFirstFriend.setImage(imageFirstFriendImage);
        imageSecondFriend.setImage(imageSecondFriendImage);
    }

    private void printBaseUsers(List<Friend> baseUsers) {
        ObservableList<Friend> friendsList = FXCollections.observableArrayList(baseUsers);
        startUsersTableView.setItems(friendsList);
    }

    public static List<Integer> getCommonFriends(List<Integer> firstFriends, List<Integer> secondFriends){
        List<Integer> out = new ArrayList<>();
        for(Integer tmp : firstFriends){
            if(secondFriends.contains(tmp))out.add(tmp);
        }
        return out;
    }

    public void printFriends(List<Friend> friends) {
        ObservableList<Friend> friendsList = FXCollections.observableArrayList(friends);
        friendsTableView.setItems(friendsList);
    }
}
