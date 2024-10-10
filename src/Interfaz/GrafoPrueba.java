package Interfaz;

	import java.util.ArrayList;
	import java.util.List;

	import Logica.Aristas;

	public class GrafoPrueba {
		    private List<Aristas> aristas;
		    private int numeroDeNodos;

		    public GrafoPrueba(int numeroDeNodos) {
		        this.numeroDeNodos = numeroDeNodos;
		        this.aristas = new ArrayList<>();
		    }

		    public void agregarArista(int inicio, int fin, double peso) {
		        Aristas arista = new Aristas(inicio, fin, peso);
		        aristas.add(arista);
		    }

		    public List<Aristas> getAristas() {
		        return aristas;
		    }

		    public int getNumeroDeNodos() {
		        return numeroDeNodos;
		    }
		    
		    public void mostrarGrafo() {
		        for (Aristas arista : aristas) {
		            System.out.println("Inicio: " + arista.getInicio() + 
		                               ", Fin: " + arista.getFin() + 
		                               ", Peso: " + arista.getPeso());
		        }
		    }
		

	
	    public static void main(String[] args) {
	        GrafoPrueba grafo = new GrafoPrueba(6);

	        // Agregar aristas entre nodos con peso
	        grafo.agregarArista(0, 1, 2.5);
	        grafo.agregarArista(0, 2, 3.0);
	        grafo.agregarArista(1, 2, 1.5);
	        grafo.agregarArista(1, 3, 2.0);
	        grafo.agregarArista(2, 4, 4.0);
	        grafo.agregarArista(3, 5, 3.5);
	        grafo.agregarArista(4, 5, 1.0);

	        // Mostrar el grafo
	        grafo.mostrarGrafo();
	    }
	}
	

