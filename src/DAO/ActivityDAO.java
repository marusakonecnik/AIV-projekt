package DAO;

import Activity.Activity;
import MessagingBean.QueueAdder;
import Observer.LogObserver;
import Person.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ActivityDAO implements IActivityDAO {


    private ArrayList<Activity> activities = new ArrayList<Activity>();
    @PersistenceContext
    private EntityManager em;
    private QueueAdder adder = new QueueAdder();
    public void save(Activity activity) throws Exception {
        Activity foundActivity = getActivityByID(activity.getId());
        if(activity.getObservers().size()==0)
            activity.addObserver(new LogObserver());
           // MailObserver observer = new MailObserver();
           // observer.setEmail(activity.getPerson().getEmail());
            activity.addObserver(new QueueAdder());
        if(foundActivity==null){

            Activity clonedActivity =(Activity)activity.makeCopy();
            em.persist(clonedActivity);
        }
        else {
            updateActivity(foundActivity,activity);
            em.merge(foundActivity);
        }
         activity.Notify();

    }
    public Activity getActivityByID(int id){
        Activity a = new Activity();
        try{
            a = (Activity)em.createQuery("SELECT a from Activity a WHERE a.id=?1").setParameter(1,id).getSingleResult();
        }catch(javax.persistence.NoResultException e){
            return null;
        }
        return a;
    }

    private void updateActivity(Activity oldActivity, Activity newActivity){

        oldActivity.setName(newActivity.getName());
        oldActivity.setDistance(newActivity.getDistance());
        oldActivity.setFinishTime(newActivity.getFinishTime());
        oldActivity.setStartingTime(newActivity.getStartingTime());
        oldActivity.setSteps(newActivity.getSteps());
        oldActivity.setType(newActivity.getType());
    }

    public List<Activity> getPersonActivites(Person person){

        List<Activity> list = em.createQuery("SELECT a from Activity a WHERE a.person.email=?1").setParameter(1,person.getEmail()).getResultList();
        return list;

    }

    public void deleteActivity(int id)
    {
        em.remove(getActivityByID(id));
        //em.createQuery("delete  from Activity a WHERE a.id=:id").setParameter("id",id);
    }

}
