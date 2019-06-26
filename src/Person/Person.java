package Person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Person implements Serializable {
    private String name;
    private String surname;
    @Id
    private String email;
    private Date date;
    private double weight;
    private double height;
    private String gender;


   // private List<Activity> activities = new ArrayList<>();

    public Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(Person person) {
        this.name = person.name;
        this.surname = person.surname;
        this.email = person.email;
        this.date = person.date;
        this.weight = person.weight;
        this.height = person.height;
        this.gender = person.gender;

    }

    public void setGender(String gender) {
        this.gender = gender;
    }

/*    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    } */

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }


}
