package unitjon.th10.team4.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unitjon.th10.team4.dto.FCMMessage;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class FcmService {
    private String API_URL = "https://fcm.googleapis.com/v1/projects/unithon10th/messages:send";
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void sendMessageTo(String targetToken, String title, String body){
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        log.info(response.body().string());
    }

    private String makeMessage(String fcmToken,String title,String contents) throws JsonProcessingException {
        System.out.println("FCM="+fcmToken);
        FCMMessage fcmMessage = FCMMessage.builder()
                .message(FCMMessage.Message.builder()
                        .token(fcmToken)
                        .notification(FCMMessage.Notification.builder()
                                .title(title)
                                .body(contents)
                                .image(null)
                                .build()
                        )
                        .build()
                )
                .validate_only(false)
                .build();
        return objectMapper.writeValueAsString(fcmMessage);
    }

    @SneakyThrows
    public void send(Message message){
        try{
            System.out.println("Message="+message.toString());
            FirebaseMessaging.getInstance().send(message);
        }catch (Exception e){
            System.out.println("FCM error="+e.getMessage());
            System.out.println("FCM error typex="+e.getClass());

        }
    }

    private String getAccessToken() throws IOException{
        String firebaseConfigPath = "/unithon10th.json";
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
