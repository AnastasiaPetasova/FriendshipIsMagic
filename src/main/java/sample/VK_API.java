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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anastasia on 03.07.2018.
 */
public class VK_API {

    static final int APP_ID = 6623270;
    static final String CLIENT_SECRET = "jVjLf7RP31NzgTNtbTOr";
    static final String SERVICE_KEY = "7092c71b7092c71b7092c71bea70f7d73d770927092c71b2ba97f558a5c77b9556f3c72";

    static void doIt()  {
        TransportClient httpClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(httpClient);

//        ServiceClientCredentialsFlowResponse authResponse = null;
//        try {
//            authResponse = vk.oauth()
//                    .serviceClientCredentialsFlow(APP_ID, CLIENT_SECRET)
//                    .execute();
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//
        ServiceActor actor = new ServiceActor(APP_ID, CLIENT_SECRET, SERVICE_KEY);

        try {
            GetResponse friendsResponse = vk.friends().get(actor)
                .userId(77903915)
                .execute();

            List<Integer> friendIds = friendsResponse.getItems();
            System.out.println(friendIds);

            List<UserXtrCounters> usersResponse = vk.users().get(actor)
                    .userIds(friendIds.stream().map(i -> i.toString()).collect(Collectors.toList()))
                    .fields(UserField.CITY, UserField.PHOTO_MAX_ORIG)
                    .execute();

            for (UserXtrCounters user : usersResponse){
                System.out.println(user);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
