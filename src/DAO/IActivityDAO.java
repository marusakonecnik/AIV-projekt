package DAO;

import Activity.Activity;
import Person.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IActivityDAO {

     void save(Activity activity) throws Exception;
     Activity getActivityByID(int id);
     List<Activity> getPersonActivites(Person person);
     void deleteActivity(int id);

}
