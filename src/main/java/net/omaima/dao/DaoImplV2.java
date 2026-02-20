package net.omaima.dao;

public class DaoImplV2 implements IDao{
    @Override
    public double getData() {
        System.out.println("Version capteurs");
        double t = 34;
        return t;
    }
}
