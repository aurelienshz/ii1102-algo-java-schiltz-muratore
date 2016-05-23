public class Autocar extends BigVehicule {

    private int nbPlaces;

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Autocar(String modele, int puchaseYear, int purchasePrice, String licenceNumber, char necessaryPermit, double volume, int nbPlaces) {
        super(modele, puchaseYear, purchasePrice, licenceNumber, necessaryPermit, volume);
        this.nbPlaces = nbPlaces;
    }

    public String afficherAutocar() {
        String newLine = System.getProperty("line.separator");

        return "Autocar : " + newLine +
                super.afficherBigVehcule() + newLine +
                "Nombre de places = " + nbPlaces;
    }

    public boolean peutTransporterPassagers(int nbPassagers, double volumeMoyen){
        return (nbPassagers <= getNbPlaces() && nbPassagers*volumeMoyen <= getVolume());
    }
}
