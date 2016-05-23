public abstract class BigVehicule extends Vehicule{
    private double volume;

    public double getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public BigVehicule(String modele, int puchaseYear, int purchasePrice, String licenceNumber, char necessaryPermit, double volume) {
        super(modele, puchaseYear, purchasePrice, licenceNumber, necessaryPermit);
        this.volume = volume;
    }

    public String afficherBigVehcule() {

        String newLine = System.getProperty("line.separator");

        return super.afficherVehicule() + newLine +
                "Volume = " + volume;
    }
}
