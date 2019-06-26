package TestniPrimer;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("demoBean")
@SessionScoped
public class BeanTestek implements Serializable {

    @EJB
    EJBZrno ejb;
    public void zbudiSeEJB() throws Exception {
        ejb.metoda();
    }

}
