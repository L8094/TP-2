package Logica;

public class Aristas {

	    private String inicio;
	    private String fin;
	    private double peso;

//--------------------------------------------------------------------------------------------------------			

	    public Aristas(String inicio, String fin, double peso) {
	        verificarParametro(inicio);
	        verificarParametro(fin);

	        this.inicio=inicio; 
	        this.fin=fin;
	        this.peso=peso;
	    }

//--------------------------------------------------------------------------------------------------------	
		
	    public String getInicio() {
	        return inicio;
	    }
//--------------------------------------------------------------------------------------------------------	
		
	    public String getFin() { 
	        return fin;
	    }
//--------------------------------------------------------------------------------------------------------	
			    
	    public double getPeso() {
	        return peso;
	    }
//--------------------------------------------------------------------------------------------------------				    
	    
	    public boolean equals(Aristas arista) {
	        return arista.inicio == getInicio() && arista.fin == getFin()
	                && arista.peso == getPeso();
	    }
//--------------------------------------------------------------------------------------------------------			
		
	    private void verificarParametro(String nodo) {
	        if( nodo == null || nodo.isEmpty() ) {
	            throw new IllegalArgumentException("El inicio no puede ser negativo: " + nodo);
	        }
	    }
}
