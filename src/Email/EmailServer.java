package Email;

import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EmailServer {

    @Resource(lookup = "java:jboss/mail/gmail")
    private Session session;
    org.slf4j.Logger log= LoggerFactory.getLogger(EmailServer.class);

    public void send(String addresses, String topic, String textMessage) {

        try {

            InitialContext ic = new InitialContext();
            session = (Session) ic.lookup("java:jboss/mail/gmail");
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);


            Transport.send(message);

        } catch (MessagingException | NamingException e) {
            Logger.getLogger(EmailServer.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }
}