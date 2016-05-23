public class Main {

   public static void main(String[] args){
       Voiture vehicule = new Voiture("Twingo", 2016, 1000000, "1234 AZ 49", 'B', true);
       System.out.println(vehicule.age());
       System.out.println(vehicule.afficherVoiture());

       Camion camion = new Camion("J9", 2011, 2000000, "987 BCD 75", 'B', 25.0);
       System.out.println(camion.age());
       System.out.println(camion.afficherCamion());
       System.out.println(camion.peutTransporterVolume(25.0));

       System.out.println(vehicule.coutLocation());
       System.out.println(camion.coutLocation());

       Autocar autocar = new Autocar("FRI", 2005, 9000000, "4567 WX 01", 'D', 3, 53);
       System.out.println(autocar.age());
       System.out.println(autocar.afficherAutocar());
       System.out.println(autocar.peutTransporterPassagers(30, 0.1));
   }
}
