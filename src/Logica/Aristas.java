package Logica;

public class Aristas {
	

	    private int inicio;
	    private int fin;
	    private double peso;

	    public Aristas(int inicio, int fin, double peso) {
	        verificarParametro(inicio);
	        verificarParametro(fin);

	        this.inicio=inicio; 
	        this.fin=fin;
	        this.peso=peso;
	    }


		private void verificarParametro(int i) {
	        if( i < 0 )
	            throw new IllegalArgumentException("El inicio no puede ser negativo: " + i);
	    } 

	    public int getInicio() {
	        return inicio;
	    }

	    public int getFin() { 
	        return fin;
	    }

	    public double getPeso() {
	        return peso;
	    }

	    public boolean equals(Aristas arista) {
	        return arista.inicio == getInicio() && arista.fin == getFin()
	                && arista.peso == getPeso();

	    }
}































