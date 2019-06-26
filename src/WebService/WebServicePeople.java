package WebService;


import DAO.IPersonDAO;
import Person.Person;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

@WebService
public class WebServicePeople {

    @EJB
    public IPersonDAO dao;

    public List<Person> returnAllPeople()
    {
        return dao.returnAllPeople();
    }

    public void addPerson(Person p)
    {
        dao.save(p);
    }




}
