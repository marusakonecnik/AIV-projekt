package MessagingBean;

import Activity.Activity;
import Observer.IObserver;

import javax.jms.*;
import javax.naming.InitialContext;

public class QueueAdder implements IObserver {

    public void updateMe(Activity a) throws Exception {
        String message = "Sprememba dodajanje aktivnosti";
        InitialContext ctx = new InitialContext();
        Queue queue = (Queue) ctx.lookup("jms/queue/MyQueue");
        QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("java:jboss/exported/jms/RemoteConnectionFactory");
        QueueConnection cnn = factory.createQueueConnection("snufkin123","12guitar21");
        QueueSession session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        QueueSender sender = session.createSender(queue);

        sender.send(session.createObjectMessage(a));
        sender.send(session.createTextMessage(message));

        session.close();
    }

}