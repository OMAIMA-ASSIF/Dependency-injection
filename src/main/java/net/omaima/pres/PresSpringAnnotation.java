package net.omaima.pres;
import net.omaima.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class PresSpringAnnotation {
    public static void main(String[] args){
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("net.omaima"); //on lui demande de scanner le packge de base

        IMetier metier = applicationContext.getBean(IMetier.class);
        System.out.println("RES="+metier.calcul());
    }
}
