package Beans;

import Activity.Activity;
import Person.Person;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.util.List;

public interface IBean {

    public String addPerson();

    List<Person> getPeople();

    Person getNewPerson();

     String addActivity() throws JMSException, NamingException, Exception;

     Activity getNewActivity();

    List<Activity> getActivities();

    String edit(String email);

    String editActivity(int id);

    String returnPersonActivites(Person person);
}
