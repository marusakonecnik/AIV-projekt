package MessagingBean;

import Activity.Activity;
import Email.EmailServer;
import Strategy.MessageStrategy;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(
        activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/MyQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageDrivenBean implements MessageListener {


    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            try {

                System.out.println("[AIVMessageDriven] Sporocilo prispelo: "+tm.getText());

            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if(message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage)message;
            try {
                Activity a = (Activity)objectMessage.getObject();
                EmailServer server = new EmailServer();
                MessageStrategy strategy = a.getStrategy();
                server.send(a.getPersonToNotify(),"",strategy.getMessage());
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }

        else {
            System.out.println("Prispelo je neznano sporoèilo.");
        }
    }

}
