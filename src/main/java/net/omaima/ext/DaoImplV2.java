package net.omaima.ext;

import net.omaima.dao.IDao;
import org.springframework.stereotype.Component;

@Component("dao2")
public class DaoImplV2 implements IDao {

    @Override
    public double getData() {
        System.out.println("Version capteur");
        double t = 12;
        return t;
    }
}
