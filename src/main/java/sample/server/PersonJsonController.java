package sample.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.model.POJOPerson;

import java.util.Set;

/**
 * @author Andrey Mogilev
 */
@RestController
@RequestMapping("/json")
public class PersonJsonController {

    @Autowired
    private PersonDAO personDao;

    @RequestMapping(method = RequestMethod.POST, value = "/person")
    public void handleAddPerson(@RequestBody POJOPerson p) {
        personDao.addPerson(p);
    }

    @RequestMapping("/persons")
    public Set<POJOPerson> handleGetPersons() {
        return personDao.getPersons();
    }
}
