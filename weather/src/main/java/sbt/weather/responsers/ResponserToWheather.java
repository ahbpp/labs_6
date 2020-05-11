package sbt.weather.responsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResponserToWheather {
    final static String token = "c27fb993535ea9bcd42a6a2c46d506bd";

    @Autowired
    private RestTemplate restTemplateWhether;

    public String getResponse(long time) {
        String url = "https://api.darksky.net/forecast/" + token + "/55.3601,37.5589," + time +
                "?exclude=currently&units=auto";
        ResponseEntity<String> response = restTemplateWhether.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalStateException();
        }
        return response.getBody();
    }
}
