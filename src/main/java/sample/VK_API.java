package sample;

import com.vk.api.sdk.client.ClientResponse;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.ServiceClientCredentialsFlowResponse;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anastasia on 03.07.2018.
 */
public class VK_API {

    static final int APP_ID = 6623270;
    static final String CLIENT_SECRET = "jVjLf7RP31NzgTNtbTOr";
    static final String SERVICE_KEY = "7092c71b7092c71b7092c71bea70f7d73d770927092c71b2ba97f558a5c77b9556f3c72";

    static TransportClient httpClient = HttpTransportClient.getInstance();
    static VkApiClient vk = new VkApiClient(httpClient);

    static List<Friend> getFriendsInfo(List<Integer> friendIds) {

        ServiceActor actor = new ServiceActor(APP_ID, CLIENT_SECRET, SERVICE_KEY);

        List<Friend> friends = null;
        try {
            List<UserXtrCounters> usersResponse = vk.users().get(actor)
                    .userIds(friendIds.stream().map(i -> i.toString()).collect(Collectors.toList()))
                    .fields(UserField.BOOKS, UserField.INTERESTS, UserField.SEX)
                    .execute();

            friends = new LinkedList<>();

            for (UserXtrCounters user : usersResponse) {
                friends.add(new Friend(user.getId(), user.getFirstName(), user.getSex(), user.getLastName(), user.getBooks(), user.getInterests()));
            }


        } catch (ApiException e) {
//            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return friends;
    }

    static List<Integer> getFriendIds(int id) {
        ServiceActor actor = new ServiceActor(APP_ID, CLIENT_SECRET, SERVICE_KEY);

        try {
            GetResponse friendsResponse = vk.friends().get(actor)
                    .userId(id)
                    .execute();

            List<Integer> friendIds = friendsResponse.getItems();
            return friendIds;
        } catch (ApiException e) {
//            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static List<String> getPhotoLinks(List<Integer> ids) {
        ServiceActor actor = new ServiceActor(APP_ID, CLIENT_SECRET, SERVICE_KEY);

        List<String> photoLinks = null;
        try {
            List<UserXtrCounters> usersResponse = vk.users().get(actor)
                    .userIds(ids.stream().map(i -> i.toString()).collect(Collectors.toList()))
                    .fields(UserField.PHOTO_100)
                    .execute();

            photoLinks = new LinkedList<>();

            for (UserXtrCounters user : usersResponse) {
                photoLinks.add(user.getPhoto100());
            }


        } catch (ApiException e) {
//            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return photoLinks;
    }

}
