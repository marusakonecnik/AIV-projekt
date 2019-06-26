package TestniPrimer;

import MessagingBean.QueueAdder;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class EJBZrnoBean implements Serializable,EJBZrno {
    QueueAdder adder = new QueueAdder();

    @Override
    public void metoda() throws Exception {
       // adder.addMessageToQueue("Hellloo");

    }
}
