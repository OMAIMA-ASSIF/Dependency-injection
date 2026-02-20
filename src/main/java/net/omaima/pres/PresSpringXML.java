package net.omaima.pres;
import net.omaima.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXML {
    public static void main(String[] args) {
       ApplicationContext springContext = new ClassPathXmlApplicationContext("config.xml");

       IMetier metier = springContext.getBean(IMetier.class);  //chercher un bean qui implemente l interface IMetier -> sa va fonctionner car on a un seul bean , aussi on a pas eu besoin de faire le casding
       System.out.println("RES="+metier.calcul()) ;

    }
}
