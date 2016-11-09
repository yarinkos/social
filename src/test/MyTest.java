
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class MyTest {

    RestTemplate template = new TestRestTemplate();


    @Test
    public void testLogin() {
        RestTemplate template1 =HttpClientFactory.getRestTemplate();
        String s1=template1.exchange("http://localhost:8080/upload",
                HttpMethod.GET, null, String.class).toString();
        System.out.println(s1);
    }


    @Test
    public void testLogin3() {
        }




}