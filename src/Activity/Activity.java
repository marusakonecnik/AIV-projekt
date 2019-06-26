package Activity;

import Observer.IObserver;
import Observer.ISubject;
import Person.Person;
import Strategy.MessageStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table
public class Activity implements ISubject, Serializable,ActivityInterface{
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private Date startingTime;
    private Date finishTime;
    private Date date;
    private double distance;
    private int steps;
    private String type;
    private String personToNotify;

    @Transient
    private MessageStrategy strategy;

    @ManyToOne
    private Person person;


    public Activity(Activity activity) {
        this.id = activity.id;
        this.name = activity.name;
        this.startingTime = activity.startingTime;
        this.finishTime = activity.finishTime;
        this.date = activity.date;
        this.distance = activity.distance;
        this.steps = activity.steps;
        this.type = activity.type;
        this.person = activity.person;
    }

    public String getPersonToNotify() {
        return personToNotify;
    }

    public void setPersonToNotify(String personToNotify) {
        this.personToNotify = personToNotify;
    }

    public MessageStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MessageStrategy strategy) {
        this.strategy = strategy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ActivityInterface makeCopy() {
        Activity activityObject = null;

        try {
            activityObject = (Activity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return activityObject;
    }
    public ArrayList<IObserver> getObservers() {
        return observers;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public Activity() {
        date= new Date();
    }

    public Date getDate() {
        return date;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public double getDistance() {
        return distance;
    }

    public int getSteps() {
        return steps;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void addObserver(IObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IObserver observer){
        observers.remove(observer);
    }



    public void Notify() throws Exception {

        for(int i=0; i<observers.size(); i++){
            observers.get(i).updateMe(this);
        }
    }
}
