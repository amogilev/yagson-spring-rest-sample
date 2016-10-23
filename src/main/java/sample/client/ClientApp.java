package sample.client;

import com.gilecode.yagson.converters.YaGsonHttpMessageConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.model.POJOPerson;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Andrey Mogilev
 */
public class ClientApp {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate(Collections.singletonList(new YaGsonHttpMessageConverter()));

        ResponseEntity<Void> voidResponseEntity =
                restTemplate.postForEntity("http://localhost:8080/yagson/person",
                        new POJOPerson("foo", "bar"), Void.class);

        // FIXME find out how to set content type for empty get requests
        ResponseEntity<Set> result =
                restTemplate.getForEntity("http://localhost:8080/yagson/persons", Set.class);

        Set<POJOPerson> persons = result.getBody();
        System.out.println("persons = " + persons);
    }

}
