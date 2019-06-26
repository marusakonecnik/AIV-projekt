package Observer;

import Activity.Activity;

import javax.jms.JMSException;
import javax.naming.NamingException;

public interface IObserver {

    void updateMe(Activity a) throws NamingException, JMSException, Exception;
}
