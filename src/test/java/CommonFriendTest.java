import org.junit.Test;
import sample.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anastasia on 06.07.2018.
 */
public class CommonFriendTest {

    static List<Integer> toList(int... array) {
        List<Integer> list = new ArrayList<>();
        for (int value : array) {
            list.add(value);
        }
        return list;
    }


    static void assertCommonIdsEquals(List<Integer> expected, List<Integer> actual) {
        Collections.sort(expected);
        Collections.sort(actual);

        if (!expected.equals(actual)) {
            throw new AssertionError("Списки не равны");
        }
    }

    void testIds(List<Integer> firstIds, List<Integer> secondIds, List<Integer> expectedCommonIds) {
        List<Integer> actualCommonIds = Controller.getCommonFriends(firstIds, secondIds);
        assertCommonIdsEquals(expectedCommonIds, actualCommonIds);

        actualCommonIds = Controller.getCommonFriends(secondIds, firstIds);
        assertCommonIdsEquals(expectedCommonIds, actualCommonIds);
    }

    @Test
    public void testCommonFriends() {
        List<Integer> firstIds = toList(1, 3, 5, 6);
        List<Integer> secondIds = toList(6, 4, 3, 2);

        List<Integer> expectedCommonIds = toList(3, 6);

        testIds(firstIds, secondIds, expectedCommonIds);
    }

    @Test
    public void testCommonFriendsSame() {
        List<Integer> firstIds = toList(1, 3, 5, 6);
        List<Integer> secondIds = firstIds;

        List<Integer> expectedCommonIds = toList(1, 3, 5, 6);

        testIds(firstIds, secondIds, expectedCommonIds);
    }

    @Test
    public void testCommonFriendsEmpty() {
        List<Integer> firstIds = toList(1, 3, 5, 6);
        List<Integer> secondIds = toList();

        List<Integer> expectedCommonIds = toList();

        testIds(firstIds, secondIds, expectedCommonIds);
    }

    @Test
    public void testCommonFriendsNoCommon() {
        List<Integer> firstIds = toList(1, 3, 5, 6);
        List<Integer> secondIds = toList(2, 4);

        List<Integer> expectedCommonIds = toList();

        testIds(firstIds, secondIds, expectedCommonIds);
    }
}
