package net.omaima.pres;

import net.omaima.dao.DaoImpl;
import net.omaima.metier.MetierImpl;
import net.omaima.net.omaima.ext.DaoImplV2;

public class Pres1 {
    public static void main(String[] args) {
        DaoImplV2 dao = new DaoImplV2();
        MetierImpl metier = new MetierImpl(dao);
        //metier.setDao(dao); //Injection des dependances via le setter
        System.out.println(metier.calcul());
    }
}
