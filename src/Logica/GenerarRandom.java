package Logica;

import java.util.Random;

public class GenerarRandom {

    private static Random random = new Random();
    private static double latMin = -69.52;
    private static double latMax = -66.35;
    private static double lonMin = -87.25;
    private static double lonMax = -78.77; 
    

  //--------------------------------------------------------------------------------------------------------			
    
    public static Double generarLatitud() {
    	Double latitud = latMin + (latMax - latMin) * random.nextDouble();
    	return latitud;
    }
  //--------------------------------------------------------------------------------------------------------			

    public static Double generarLongitud() {
    	Double longitud = lonMin + (lonMax - lonMin) * random.nextDouble();
    	return longitud;
    }
	
}
