import org.junit.Assert;
import org.junit.Test;
import sample.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anastasia on 06.07.2018.
 */
public class CommonFriendTest {

    static void assertCommonIdsEquals(List<Integer> expected, List<Integer> actual) {
        Collections.sort(expected);
        Collections.sort(actual);

        Assert.assertTrue("Списки не равны", expected.equals(actual));

    }

    void testIds(List<Integer> firstIds, List<Integer> secondIds, List<Integer> expectedCommonIds) {
        List<Integer> actualCommonIds = Controller.getCommonFriends(firstIds, secondIds);
        assertCommonIdsEquals(expectedCommonIds, actualCommonIds);

        actualCommonIds = Controller.getCommonFriends(secondIds, firstIds);
        assertCommonIdsEquals(expectedCommonIds, actualCommonIds);
    }

    @Test
    public void testCommonFriends() {
        List<Integer> firstIds = Arrays.asList(1, 3, 5, 6);
        List<Integer> secondIds = Arrays.asList(6, 4, 3, 2);

        List<Integer> expectedCommonIds = Arrays.asList(3, 6);

        testIds(firstIds, secondIds, expectedCommonIds);
    }

    @Test
    public void testCommonFriendsSame() {
        List<Integer> firstIds = Arrays.asList(1, 3, 5, 6);
        List<Integer> secondIds = firstIds;

        List<Integer> expectedCommonIds = Arrays.asList(1, 3, 5, 6);

        testIds(firstIds, secondIds, expectedCommonIds);
    }

    @Test
    public void testCommonFriendsEmpty() {
        List<Integer> firstIds = Arrays.asList(1, 3, 5, 6);
        List<Integer> secondIds = Arrays.asList();

        List<Integer> expectedCommonIds = Arrays.asList();

        testIds(firstIds, secondIds, expectedCommonIds);
    }

    @Test
    public void testCommonFriendsNoCommon() {
        List<Integer> firstIds = Arrays.asList(1, 3, 5, 6);
        List<Integer> secondIds = Arrays.asList(2, 4);

        List<Integer> expectedCommonIds = Arrays.asList();

        testIds(firstIds, secondIds, expectedCommonIds);
    }
}
