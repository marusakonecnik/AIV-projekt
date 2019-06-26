package Observer;

import Activity.Activity;
import Email.EmailServer;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class MailObserver implements IObserver{
    private String personEmail;
    @Override
    public void updateMe(Activity a) throws NamingException, JMSException {

        EmailServer email = new EmailServer();
        email.send(personEmail,"Notification","aktivnost je bila dodana/spremenjena");
    }

    public String getEmail() {
        return personEmail;
    }

    public void setEmail(String email) {
        this.personEmail = email;
    }
}
