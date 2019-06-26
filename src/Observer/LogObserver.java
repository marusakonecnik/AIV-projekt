package Observer;

import Activity.Activity;
import Person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogObserver implements IObserver{
    Logger log= LoggerFactory.getLogger(Person.class);
    @Override
    public void updateMe(Activity a) {
        log.info("je bila obveščena o dodajanju aktivnosti");
    }
}
