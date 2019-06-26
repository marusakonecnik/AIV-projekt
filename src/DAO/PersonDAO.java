package DAO;

import Person.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonDAO implements IPersonDAO {
    private ArrayList<Person> peop = new ArrayList<Person>();
    @PersistenceContext
    private EntityManager em;
    /*private PersonDAO() {
        peop = new ArrayList<>();
    }
    public static PersonDAO getInstance(){
        if(instance==null){
            instance = new PersonDAO();
        }
        return instance;
    } */

    public void save(Person person){
        Person foundPerson = findOsebaByEmail(person.getEmail());
        if(foundPerson==null){
            em.persist(person);
        }
        else{
            updatePerson(foundPerson,person);
            em.merge(foundPerson);
        }

    }

    public List<Person> returnAllPeople(){
        List<Person> list = em.createQuery("SELECT o from Person o").getResultList();
        return list;
    }

    public Person findOsebaByEmail(String email){
        Person p;
        try{
             p = (Person)em.createQuery("SELECT p from Person p WHERE p.email =?1").setParameter(1,email).getSingleResult();
        }catch(javax.persistence.NoResultException e){
            return null;
        }

        return p;
    }

    public void updatePerson(Person newPerson, Person oldPerson){
        newPerson.setName(oldPerson.getName());
        newPerson.setSurname(oldPerson.getSurname());
        newPerson.setEmail(oldPerson.getEmail());
        newPerson.setWeight(oldPerson.getWeight());
        newPerson.setHeight(oldPerson.getHeight());
        newPerson.setGender(oldPerson.getGender());
        newPerson.setDate(oldPerson.getDate());
    }

    public void deletePerson(String email)
    {
        Person p = findOsebaByEmail(email);
        em.remove(p);


    }

}
