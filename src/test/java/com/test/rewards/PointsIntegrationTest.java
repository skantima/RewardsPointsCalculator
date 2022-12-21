package com.test.rewards;

import com.test.rewards.model.Money;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PointsIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    int[] userIds = {42,41,69,18,42,63,53,48,20,71,41,11,31,41,52,24,98,66,100,69,77,33,28,41,41,39,41,82,69,60,93,19,21,69,81,65,5,61,87,47,45,11,81,11,89,2,47,93,58,88,55,14,45,18,86,48,77,82,96,92,70,96,12,16,39,8,62,87,99,78,78,57,10,14,93,80,6,82,32,69,96,40,83,65,55,75,19,84,96,50};
    int[] priceList = {12,90,47,20,10,2,9,14,14,12,13,13,20,9,7,14,20,6,10,12,6,16,5,1,3,11,15,17,14,20,20,8,10,15,18,10,2,2,5,6,18,19,7,14,20,20,20,3,4,7,5,19,9,1,1,10,18,16,5,17,8,15,6,7,6,20,2,14,10,3,13,15,10,10,15,10,4,6,8,6,8,1,19,11,5,3,9,5,8,18};
    String[] dates = {"03-11-2022 20:06:10", "04-11-2022 20:07:10", "05-11-2022 20:08:10", "06-11-2022 20:09:10", "07-11-2022 20:10:10", "08-11-2022 20:11:10", "09-11-2022 20:12:10", "10-11-2022 20:13:10", "11-11-2022 20:14:10", "12-11-2022 20:15:10", "13-11-2022 20:16:10", "14-11-2022 20:17:10", "15-11-2022 20:18:10", "16-11-2022 20:19:10", "17-11-2022 20:20:10", "18-11-2022 20:21:10", "19-11-2022 20:22:10", "20-11-2022 20:23:10", "21-11-2022 20:24:10", "22-11-2022 20:25:10", "23-11-2022 20:26:10", "24-11-2022 20:27:10", "25-11-2022 20:28:10", "26-11-2022 20:29:10", "27-11-2022 20:30:10", "28-11-2022 20:31:10", "29-11-2022 20:32:10", "30-11-2022 20:33:10", "01-12-2022 20:34:10", "02-12-2022 20:35:10", "03-12-2022 20:36:10", "04-12-2022 20:37:10", "05-12-2022 20:38:10", "06-12-2022 20:39:10", "07-12-2022 20:40:10", "08-12-2022 20:41:10", "09-12-2022 20:42:10", "10-12-2022 20:43:10", "11-12-2022 20:44:10", "12-12-2022 20:45:10", "13-12-2022 20:46:10", "14-12-2022 20:47:10", "15-12-2022 20:48:10", "16-12-2022 20:49:10", "17-12-2022 20:50:10", "18-12-2022 20:51:10", "19-12-2022 20:52:10", "20-12-2022 20:53:10", "21-12-2022 20:54:10", "22-12-2022 20:55:10", "23-12-2022 20:56:10", "24-12-2022 20:57:10", "25-12-2022 20:58:10", "26-12-2022 20:59:10", "27-12-2022 21:00:10", "28-12-2022 21:01:10", "29-12-2022 21:02:10", "30-12-2022 21:03:10", "31-12-2022 21:04:10", "01-01-2023 21:05:10", "02-01-2023 21:06:10", "03-01-2023 21:07:10", "04-01-2023 21:08:10", "05-01-2023 21:09:10", "06-01-2023 21:10:10", "07-01-2023 21:11:10", "08-01-2023 21:12:10", "09-01-2023 21:13:10", "10-01-2023 21:14:10", "11-01-2023 21:15:10", "12-01-2023 21:16:10", "13-01-2023 21:17:10", "14-01-2023 21:18:10", "15-01-2023 21:19:10", "16-01-2023 21:20:10", "17-01-2023 21:21:10", "18-01-2023 21:22:10", "19-01-2023 21:23:10", "20-01-2023 21:24:10", "21-01-2023 21:25:10", "22-01-2023 21:26:10", "23-01-2023 21:27:10", "24-01-2023 21:28:10", "25-01-2023 21:29:10", "26-01-2023 21:30:10", "27-01-2023 21:31:10", "28-01-2023 21:32:10", "29-01-2023 21:33:10", "30-01-2023 21:34:10", "31-01-2023 21:35:10", "01-02-2023 21:36:10", "02-02-2023 21:37:10", "03-02-2023 21:38:10", "04-02-2023 21:39:10", "05-02-2023 21:40:10", "06-02-2023 21:41:10", "07-02-2023 21:42:10", "08-02-2023 21:43:10", "09-02-2023 21:44:10", "10-02-2023 21:45:10", "11-02-2023 21:46:10", "12-02-2023 21:47:10", "13-02-2023 21:48:10", "14-02-2023 21:49:10", "15-02-2023 21:50:10", "16-02-2023 21:51:10", "17-02-2023 21:52:10", "18-02-2023 21:53:10", "19-02-2023 21:54:10", "20-02-2023 21:55:10", "21-02-2023 21:56:10", "22-02-2023 21:57:10", "23-02-2023 21:58:10", "24-02-2023 21:59:10", "25-02-2023 22:00:10", "26-02-2023 22:01:10", "27-02-2023 22:02:10", "28-02-2023 22:03:10", "01-03-2023 22:04:10", "02-03-2023 22:05:10", "03-03-2023 22:06:10", "04-03-2023 22:07:10", "05-03-2023 22:08:10", "06-03-2023 22:09:10", "07-03-2023 22:10:10", "08-03-2023 22:11:10", "09-03-2023 22:12:10", "10-03-2023 22:13:10", "11-03-2023 22:14:10", "12-03-2023 22:15:10", "13-03-2023 22:16:10", "14-03-2023 22:17:10", "15-03-2023 22:18:10", "16-03-2023 22:19:10", "17-03-2023 22:20:10", "18-03-2023 22:21:10", "19-03-2023 22:22:10", "20-03-2023 22:23:10", "21-03-2023 22:24:10", "22-03-2023 22:25:10", "23-03-2023 22:26:10", "24-03-2023 22:27:10", "25-03-2023 22:28:10", "26-03-2023 22:29:10", "27-03-2023 22:30:10", "28-03-2023 22:31:10", "29-03-2023 22:32:10", "30-03-2023 22:33:10", "31-03-2023 22:34:10", "01-04-2023 22:35:10"};

    @Test
    public void testE2ETransactionInputAndRewards() throws Exception {
        for (int i=0; i<userIds.length; i++) {
            UserPayment userPayment = new UserPayment();
            userPayment.setUserId(String.valueOf(userIds[i]));
            userPayment.setPayment(new Money((double) priceList[i]));
            userPayment.setDateTime(dates[i]);
            HttpEntity<UserPayment> entity = new HttpEntity<>(userPayment, headers);

            ResponseEntity<UserPayment> response = restTemplate.exchange(
                    createURLWithPort("/points"), HttpMethod.POST, entity, UserPayment.class);

            assertEquals(response.getStatusCode().value(), 200);
        }
        restTemplate = new TestRestTemplate();
        ResponseEntity<UserPoints> rewardResponse = restTemplate.getForEntity(createURLWithPort("/points/41/last/3"), UserPoints.class);
        assertNotNull(rewardResponse);
        assertEquals(rewardResponse.getStatusCode().value(), 200);
        assertEquals(rewardResponse.getBody().getPoints().getPoints(), 112);
        assertEquals(rewardResponse.getBody().getTotalPoints().getPoints(), 112);

        restTemplate = new TestRestTemplate();
        ResponseEntity<UserPoints> rewardResponse2 = restTemplate.getForEntity(createURLWithPort("/points/69/last/2"), UserPoints.class);
        assertNotNull(rewardResponse2);
        assertEquals(rewardResponse2.getStatusCode().value(), 200);
        assertEquals(rewardResponse2.getBody().getPoints().getPoints(), 38);
        assertEquals(rewardResponse2.getBody().getTotalPoints().getPoints(), 44);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
