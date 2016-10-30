package sample.client;

import com.gilecode.yagson.converters.YaGsonHttpMessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import sample.model.POJOPerson;
import sample.model.PersonEx;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

/**
 * @author Andrey Mogilev
 */
public class ClientApp {

    public static void main(String[] args) throws Exception {

        //
        // demo for YaGson
        //

        // this RestTemplate reads and writes application/yagson data through YaGson
        RestTemplate restTemplate1 = new RestTemplate(Collections.singletonList(new YaGsonHttpMessageConverter()));

        POJOPerson p1 = new POJOPerson("Sample", "Person1");
        PersonEx p2 = new PersonEx("Mr.", "Sample", "Person2");

        // if the actual entity type is equal to the expected one, then simple post- method may be used
        restTemplate1.postForEntity("http://localhost:8080/yagson/person", p1, Void.class);

        // otherwise, the expected serialization type (which is POJOPerosn) needs to be specified explicitly
        // in RequestEntity which is passed to 'exchange':
        RequestEntity<POJOPerson> postRequestEntity = new RequestEntity<>(p2, HttpMethod.POST,
                URI.create("http://localhost:8080/yagson/person"), POJOPerson.class);
        restTemplate1.exchange(postRequestEntity, Void.class);
        // also, RequestEntity may be used to specify the parameterized generic classes, like
        //  new TypeToken<List<POJOPerson>>(){}.getType()

        // the returned entities are de-serialized automatically, based on the specified type and the converters
        // however, please note that the GET requests with no entity does not automatically set 'Content-Type', so
        // do not use Spring's @RequestMapping with 'consumes' attribute for such endpoints (or whole controllers)
        RequestEntity getRequestEntity = new RequestEntity<>(null, HttpMethod.GET,
                URI.create("http://localhost:8080/yagson/persons"));

        ResponseEntity<Set<POJOPerson>> result = restTemplate1.exchange(
                getRequestEntity,
                new ParameterizedTypeReference<Set<POJOPerson>>() {});

        Set<POJOPerson> persons = result.getBody();
        System.out.println("persons = " + persons);

        //
        // demo for Jackson2
        //

        // this RestTemplate reads and writes application/json data through Jackson2
        RestTemplate restTemplate2 = new RestTemplate(Collections.singletonList(
                new MappingJackson2HttpMessageConverter()));

        restTemplate2.postForEntity("http://localhost:8080/json/person",
                new POJOPerson("Sample", "Person4"), Void.class);

        RequestEntity getRequestEntity2 = new RequestEntity<>(null, HttpMethod.GET,
                URI.create("http://localhost:8080/json/persons"));
        result = restTemplate2.exchange(
                getRequestEntity,
                new ParameterizedTypeReference<Set<POJOPerson>>() {});

        persons = result.getBody();
        System.out.println("persons = " + persons);
        // note that PersonEx (for Person2) is stripped down to POJOPerson here
    }

}
