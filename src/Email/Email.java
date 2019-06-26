package Email;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email { //neuporabljen

    public static void sendEmail(String recepient) throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "konecnik.10@gmail.com";
        String password = "12guitar21";


        Session session = Session.getInstance(properties,new Authenticator(){

            protected PasswordAuthentication getPasswordAuthenticaton(){
                return new PasswordAuthentication(myAccountEmail,password);

            }
        });

        Message message = new MimeMessage(session);

        Transport.send(message);


    }
    private static Message prepareMessage(Session session,String myAccountEmail, String recepient) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
        message.setSubject("My First Email.Email from Java app");
        message.setText("Hej There \n");
        return message;
    }
}

