import com.vk.api.sdk.objects.base.Sex;
import org.junit.Assert;
import org.junit.Test;
import sample.Friend;

/**
 * Created by Anastasia on 13.07.2018.
 */
public class FriendTest {
    @Test
    public void testGetId(){
        int expectedId = 123;
        Friend friend = new Friend(expectedId, null,null,null,null,null);

        int actualId = friend.getId();
        Assert.assertEquals("id не равны", expectedId, actualId);
    }

    @Test
    public void testGetNameNull(){
        String expectedName = null;
        Friend friend = new Friend(0,expectedName,null,null,null,null);

        String actualName = friend.getName();
        Assert.assertEquals("Name не равны", expectedName, actualName);
    }

    @Test
    public void testGetName(){
        String expectedName = "Name";
        Friend friend = new Friend(0,expectedName,null,null,null,null);

        String actualName = friend.getName();
        Assert.assertEquals("Name не равны", expectedName, actualName);
    }

    @Test
    public void testGetSexNull(){
        Sex expectedSex = null;
        Friend friend = new Friend(0, null,expectedSex,null,null,null);

        Sex actualSex = friend.getSex();
        Assert.assertEquals("Sex не равны", expectedSex, actualSex);
    }

    @Test
    public void testGetSexMale(){
        Sex expectedSex = Sex.MALE;
        Friend friend = new Friend(0, null,expectedSex,null,null,null);

        Sex actualSex = friend.getSex();
        Assert.assertEquals("Sex не равны", expectedSex, actualSex);
    }

    @Test
    public void testGetSexFemale(){
        Sex expectedSex = Sex.FEMALE;
        Friend friend = new Friend(0, null,expectedSex,null,null,null);

        Sex actualSex = friend.getSex();
        Assert.assertEquals("Sex не равны", expectedSex, actualSex);
    }

    @Test
    public void testGetLastName(){
        String expectedLastName = "LastName";
        Friend friend = new Friend(0, null,null,expectedLastName,null,null);

        String actualLastName = friend.getLastName();
        Assert.assertEquals("LastName не равны", expectedLastName, actualLastName);
    }

    @Test
    public void testGetLastNameNull(){
        String expectedLastName = null;
        Friend friend = new Friend(0, null,null,expectedLastName,null,null);

        String actualLastName = friend.getLastName();
        Assert.assertEquals("LastName не равны", expectedLastName, actualLastName);
    }

    @Test
    public void testGetBooks(){
        String expectedBooks = "Books";
        Friend friend = new Friend(0, null,null, null, expectedBooks,null);

        String actualBooks = friend.getBooks();
        Assert.assertEquals("Books не равны", expectedBooks, actualBooks);
    }

    @Test
    public void testGetBooksNull(){
        String expectedBooks = null;
        Friend friend = new Friend(0, null,null, null, expectedBooks,null);

        String actualBooks = friend.getBooks();
        Assert.assertEquals("Books не равны", expectedBooks, actualBooks);
    }

    @Test
    public void testGetInterests(){
        String expectedInterests = "Interests";
        Friend friend = new Friend(0, null,null, null, null, expectedInterests);

        String actualInterests = friend.getInterests();
        Assert.assertEquals("Interests не равны", expectedInterests, actualInterests);
    }


    @Test
    public void testGetInterestsNull(){
        String expectedInterests = null;
        Friend friend = new Friend(0, null,null, null, null, expectedInterests);

        String actualInterests = friend.getInterests();
        Assert.assertEquals("Interests не равны", expectedInterests, actualInterests);
    }
}
