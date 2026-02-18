package net.omaima.pres;

import net.omaima.dao.DaoImpl;
import net.omaima.metier.MetierImpl;

public class Pres1 {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl(dao);
        //metier.setDao(dao); //Injection des dependances via le setter
        System.out.println(metier.calcul());
    }
}
