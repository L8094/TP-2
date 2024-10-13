package Logica;

	import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	public class Grafo {
		    private List<Aristas> aristas;
		    private int numeroDeVertices;
		    
//--------------------------------------------------------------------------------------------------------	
			
		    public Grafo(int numeroDeNodos) {
		        this.numeroDeVertices = numeroDeNodos;
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
					    
		    public int getCantVertices() {
		        return numeroDeVertices;
		    }
		    
//--------------------------------------------------------------------------------------------------------	
			
		    public void mostrarGrafo() {
		        for (Aristas arista : aristas) {
		            System.out.println("Inicio: " + arista.getInicio() + 
		                               ", Fin: " + arista.getFin() + 
		                               ", Peso: " + arista.getPeso());
		        }
		    }	

	
//--------------------------------------------------------------------------------------------------------	

	
    public Set<String> vecinos(String vertice) {
        Set<String> vecinos = new HashSet<>();

        for (Aristas arista : aristas) {
            if (arista.getInicio().equals(vertice)) {
                vecinos.add(arista.getFin());
            } else if (arista.getFin().equals(vertice)) {
                vecinos.add(arista.getInicio());
            }
        }

        return vecinos;
    }
   }