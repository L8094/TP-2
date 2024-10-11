package Logica;

	import java.util.ArrayList;
	import java.util.List;

	public class Grafo {
		    private List<Aristas> aristas;
		    private int numeroDeNodos;
		    
//--------------------------------------------------------------------------------------------------------	
			
		    public Grafo(int numeroDeNodos) {
		        this.numeroDeNodos = numeroDeNodos;
		        this.aristas = new ArrayList<>();
		    }

//--------------------------------------------------------------------------------------------------------	
			
		    public void agregarArista(Aristas arista) {
		        aristas.add(arista);
		    }
	
//--------------------------------------------------------------------------------------------------------	
			
		    public List<Aristas> getAristas() {
		        return aristas;
		    }

//--------------------------------------------------------------------------------------------------------	
					    
		    public int getNumeroDeNodos() {
		        return numeroDeNodos;
		    }
		    
//--------------------------------------------------------------------------------------------------------	
			
		    public void mostrarGrafo() {
		        for (Aristas arista : aristas) {
		            System.out.println("Inicio: " + arista.getInicio() + 
		                               ", Fin: " + arista.getFin() + 
		                               ", Peso: " + arista.getPeso());
		        }
		    }	
}