package net.omaima.pres;

import net.omaima.dao.IDao;
import net.omaima.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner scanner = new Scanner(new File("config.txt"));

        String daoClassName = scanner.nextLine();
        //INstantiation dynamique --> on doit charger la classe en memoire
        Class cDao = Class.forName(daoClassName);
        //dans java toutes les classes qu on utilise et les interfaces sont charge une seule fois dans la memeoire sous frome d objet de type class
        IDao dao =(IDao) cDao.newInstance(); //si la classe ne contient pas de constructeur sans parametre ca va generer une exception

        String metierClassName = scanner.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cDao.getConstructor(IDao.class).newInstance(dao);

    }
}
