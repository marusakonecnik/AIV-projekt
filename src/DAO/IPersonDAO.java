package DAO;

import Person.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPersonDAO {

    void save(Person person);
    List<Person> returnAllPeople();
    Person findOsebaByEmail(String email);
    void deletePerson(String email);
}
