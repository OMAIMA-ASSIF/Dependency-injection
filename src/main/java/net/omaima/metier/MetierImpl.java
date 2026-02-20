package net.omaima.metier;

import net.omaima.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {


    @Autowired //injection automatique
    @Qualifier("dao2")
    private IDao dao; //Couplage faible

    //Pour injecter dans l attribut dao
    //un objet d une classe qui implemennte l interface IDao
    //au moment de l'instantiation   (recommende)
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double t = dao.getData();
        double res = t*12*Math.PI/2*Math.cos(t);
        return res;
    }
    //Pour injecter dans l attribut dao
    //un objet d une classe qui implemennte l interface IDao
    //apres instantiation
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
