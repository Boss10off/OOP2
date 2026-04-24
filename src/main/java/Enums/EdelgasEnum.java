package Enums;

public enum EdelgasEnum implements Edelgas{
    HELIUM(2,"Helium",4.0026),
    NEON(10,"Neon",20.180),
    ARGON(18,"Argon",39.948),
    Krypton(36,"Krypton",83.798),
    XENON(54,"Xenon",131.29);

    int ordnungsZahl;
    String name;
    double masse;

    EdelgasEnum(int ordnungsZahl, String name, double masse) {
        this.ordnungsZahl = ordnungsZahl;
        this.name = name;
        this.masse = masse;
    }

    @Override
    public String getBezeichner() {
        return name;
    }

    @Override
    public int getOrdnungsZahl() {
        return ordnungsZahl;
    }

    @Override
    public double getAtomMasse() {
        return masse;
    }
}
