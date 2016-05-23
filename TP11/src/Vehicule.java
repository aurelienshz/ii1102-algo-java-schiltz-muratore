import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public abstract class Vehicule {
    private String modele;
    private int puchaseYear;
    private int purchasePrice;
    private String licenceNumber;
    private char necessaryPermit;

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getPuchaseYear() {
        return puchaseYear;
    }

    public void setPuchaseYear(int puchaseYear) {
        this.puchaseYear = puchaseYear;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public char getNecessaryPermit() {
        return necessaryPermit;
    }

    public void setNecessaryPermit(char necessaryPermit) {
        this.necessaryPermit = necessaryPermit;
    }

    public Vehicule(String modele, int puchaseYear, int purchasePrice, String licenceNumber, char necessaryPermit) {
        this.modele = modele;
        this.puchaseYear = puchaseYear;
        this.purchasePrice = purchasePrice;
        this.licenceNumber = licenceNumber;
        this.necessaryPermit = necessaryPermit;
    }

    public int age(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat ("y");
        int currentYear = parseInt(sdf.format(date));
        return currentYear - this.getPuchaseYear();
    }

    public String afficherVehicule() {
        String newLine = System.getProperty("line.separator");
        return
                "Modèle = " + modele + newLine +
                "Année d'achat = " + puchaseYear + newLine +
                "Prix d'achat = " + (double) purchasePrice/100 + newLine +
                "Immatriculation = " + licenceNumber + newLine +
                "Permis requis = " + necessaryPermit;
    }

    public int coutLocation() {
        int facteur = 200;
        if (this.age() >= 1) facteur = 250;
        return getPurchasePrice()/facteur;
    }
}
