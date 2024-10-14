package Logica;

	import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	public class Grafo {
		    private List<Aristas> aristas;
		    private static int numeroDeVertices;
		    
//--------------------------------------------------------------------------------------------------------	
			
		    public Grafo(int numeroDeNodos) {
		        Grafo.numeroDeVertices = numeroDeNodos;
		        this.aristas = new ArrayList<>();
		    }

//--------------------------------------------------------------------------------------------------------	
		    	 public void agregarArista(Aristas arista) {
		                if (arista.getPeso() < 0) {
		                    throw new IllegalArgumentException("El peso no puede ser negativo");
		                }
		                if(verificarArista(arista)== false) {
		                    throw new IllegalArgumentException("No se pueden repetir aristas");
		                }
		                else { 
		                    aristas.add(arista);
		                }


		            }
		    	 
//--------------------------------------------------------------------------------------------------------

		    	 private boolean verificarArista(Aristas arista) {
		            for(Aristas ar : aristas){
		                if(arista.getInicio().equals(ar.getInicio()) && arista.getFin().equals(ar.getFin())) {
		                    return false;
		                }
		            }
		            return true;

		        }
	
//--------------------------------------------------------------------------------------------------------	
			
		    public List<Aristas> getAristas() {
		        return aristas;
		    }

//--------------------------------------------------------------------------------------------------------	
					    
		    public static int getCantVertices() {
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
    
  //--------------------------------------------------------------------------------------------------------	

    public static boolean esConexo(List<Aristas> listAristas) {
        Set<String> visitados = new HashSet<>();
        String inicio = listAristas.get(0).getInicio();
        Bfs.bfs(inicio, visitados, listAristas, null);
        return visitados.size() == Grafo.getCantVertices();
    }
    
//--------------------------------------------------------------------------------------------------------	
   
    public static boolean formaCiclo(Aristas arista, List<Aristas> agm) {
        String inicio = arista.getInicio();
        String fin = arista.getFin();
        
        Set<String> visitados = new HashSet<>();
       return Bfs.bfs(inicio, visitados, agm, fin);
        
    }
    
   }