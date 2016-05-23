public class Camion extends BigVehicule {
    public Camion(String modele, int puchaseYear, int purchasePrice, String licenceNumber, char necessaryPermit, double volume) {
        super(modele, puchaseYear, purchasePrice, licenceNumber, necessaryPermit, volume);
    }

    public String afficherCamion() {
        String newLine = System.getProperty("line.separator");

        return "Camion : " + newLine + super.afficherBigVehcule();
    }

    public boolean peutTransporterVolume(double testVolume){
        return testVolume <= this.getVolume();
    }
}
