package Observer;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.util.ArrayList;

public interface ISubject {

    ArrayList<IObserver> observers = new ArrayList<>();

    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void Notify() throws Exception;
}
