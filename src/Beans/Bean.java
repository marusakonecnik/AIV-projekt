package Beans;

import Activity.Activity;
import DAO.IActivityDAO;
import DAO.IPersonDAO;
import Person.Person;
import Strategy.FormalMessage;
import Strategy.InformalMessage;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("personBean")
@SessionScoped

public class Bean implements Serializable,IBean {


    private String email;
    private Person newPerson = new Person();
    private static List<Person> people = new ArrayList<>();

    @EJB
    private IActivityDAO daoA;

    @EJB
    private IPersonDAO daoP;

    private Activity newActivity = new Activity();
    private List<Activity> activities = new ArrayList<>();




    public String addPerson(){

        Person copy = new Person(newPerson);
        daoP.save(copy);
        newPerson = new Person();

        return "peopleList.xhtml";
    }

    public List<Person> getPeople() {
        people = daoP.returnAllPeople();
        return people;
    }

    public Person getNewPerson() {
        return newPerson;
    }

    @RolesAllowed("security-role")
    public String addActivity() throws Exception {

        newActivity.setPerson(daoP.findOsebaByEmail(email));

        switch (newActivity.getType())
        {
            case "sport": newActivity.setStrategy(new InformalMessage()); break;

            case "druzabna": newActivity.setStrategy(new InformalMessage()); break;

            case "izobrazevanje": newActivity.setStrategy(new FormalMessage()); break;
        }

        daoA.save(newActivity);
        newActivity = new Activity();

        return "peopleList.xhtml";

    }

    public Activity getNewActivity() {
        return newActivity;
    }


    public List<Activity> getActivities() {

        return activities;

    }
    public String edit(String email){
        newPerson =daoP.findOsebaByEmail(email);
        return "registracija.xhtml";
    }

    public String editActivity(int id){
        newActivity = daoA.getActivityByID(id);
        return "index.xhtml";
    }

    public String cloneActivity(int id) throws Exception {
        newActivity = daoA.getActivityByID(id);

        return "index.xhtml";
    }

    public String returnPersonActivites(Person person){
       // activities = Person.getActivities();
        activities = daoA.getPersonActivites(person);
        return "activityList.xhtml";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
