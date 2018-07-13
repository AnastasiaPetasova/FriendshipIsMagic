import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import sample.Controller;
import sample.Friend;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    ImageView imageFirstFriend;

    @Mock
    ImageView imageSecondFriend;

    @Mock
    TextField firstIdTextField;

    @Mock
    TextField secondIdTextField;

    @Mock
    javafx.scene.control.Label label;

    @Mock
    Button searchButton;

    @Mock
    public TableView<Friend> friendsTableView;

    @Mock
    TableView<Friend> startUsersTableView;

    @Mock
    TableColumn<Friend, Integer> userIdColumn;

    @Mock
    TableColumn<Friend, String> userFirstNameColumn;

    @Mock
    TableColumn<Friend, String> userLastNameColumn;

    @Mock
    TableColumn<Friend, String> userSexColumn;

    @Mock
    TableColumn<Friend, String> userInterestsColumn;

    @Mock
    TableColumn<Friend, String> userBooksColumn;

    @Mock
    TableColumn<Friend, Integer> startUserIdColumn;

    @Mock
    TableColumn<Friend, String> startUserFirstNameColumn;

    @Mock
    TableColumn<Friend, String> startUserLastNameColumn;

    @Mock
    TableColumn<Friend, String> startUserSexColumn;

    @Mock
    TableColumn<Friend, String> startUserInterestsColumn;

    @Mock
    TableColumn<Friend, String> startUserBooksColumn;

    @InjectMocks
    Controller controller;

    @Before
    public void initController() throws MalformedURLException {
        URL location = new URL("localhost");
        ResourceBundle rb = null;

        controller.initialize(location, rb);
    }

    @Test
    public void testPrintFriends(){

        List<Friend> expectedFriends = new ArrayList<>();
        int size = 2;
        for (int i = 0; i < size; ++i) {
            expectedFriends.add(new Friend(i, null, null, null, null, null));
        }

        controller.printFriends(expectedFriends);

        List<Friend> actualFriends = controller.friendsTableView.getItems();
        Assert.assertEquals("Friends size не равны", expectedFriends.size(), actualFriends.size());
        for (int i = 0; i < expectedFriends.size(); ++i) {
            Assert.assertEquals(String.format("Friend[%d] id не равны", i), expectedFriends.get(i).getId(), actualFriends.get(i).getId());
        }
    }
}
