public class Voiture extends Vehicule {
    private boolean autoradio;

    public boolean hasAutoradio() {
        return autoradio;
    }

    public void addAutoradio() {
        this.autoradio = true;
    }

    public void removeAutoradio() {
        this.autoradio = false;
    }

    public Voiture(String modele, int puchaseYear, int purchasePrice, String licenceNumber, char necessaryPermit, boolean autoradio) {
        super(modele, puchaseYear, purchasePrice, licenceNumber, necessaryPermit);
        this.autoradio = autoradio;
    }

    public String afficherVoiture() {
        String newLine = System.getProperty("line.separator");

        return "Voiture : " + newLine +
                super.afficherVehicule() + newLine +
                "Autoradio = " + (autoradio ? "oui":"non");
    }
}
