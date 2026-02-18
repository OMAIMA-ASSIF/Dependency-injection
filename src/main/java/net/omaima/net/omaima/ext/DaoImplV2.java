package net.omaima.net.omaima.ext;

import net.omaima.dao.DaoImpl;
import net.omaima.dao.IDao;

public class DaoImplV2 implements IDao {

    @Override
    public double getData() {
        System.out.println("Version capteur");
        double t = 12;
        return t;
    }
}
