package sample.server;

import org.springframework.stereotype.Service;
import sample.model.POJOPerson;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Andrey Mogilev
 */
@Service
public class PersonDAO {

    private Set<POJOPerson> persons = new HashSet<>();

    public void addPerson(POJOPerson p) {
        persons.add(p);
    }

    public Set<POJOPerson> getPersons() {
        return new HashSet<>(persons);
    }

    public Set<POJOPerson> getPersonInFamily(String familyName) {
        return persons.stream()
                .filter(p -> p.getFamily().equals(familyName))
                .collect(Collectors.toSet());
    }
}
