package Interfaz;



import java.util.*;

import Logica.Aristas;

public class Kruskal {
    private GrafoPrueba grafo;

    public Kruskal(GrafoPrueba grafo) {
        this.grafo = grafo;
    }

    public List<Aristas> encontrarAGM() {
        List<Aristas> agm = new ArrayList<>();
        List<Aristas> aristas = new ArrayList<>(grafo.getAristas());

        // Ordenar las aristas por peso ascendente
        Collections.sort(aristas, Comparator.comparingDouble(Aristas::getPeso));

        for (Aristas arista : aristas) {
            if (!formaCiclo(arista, agm)) {
                agm.add(arista);
            }
        }

        return agm;
    }

    // Verificar si una arista forma un ciclo usando BFS
    private boolean formaCiclo(Aristas arista, List<Aristas> agm) {
        int inicio = arista.getInicio();
        int fin = arista.getFin();
        
        // Mapear conexiones de los nodos ya en el MST
        Map<Integer, List<Integer>> adyacencias = new HashMap<>();
        for (Aristas a : agm) {
            // Asegurar que 'inicio' tenga una lista de adyacencias
            if (!adyacencias.containsKey(a.getInicio())) {
                adyacencias.put(a.getInicio(), new ArrayList<>());
            }
            // Asegurar que 'fin' tenga una lista de adyacencias
            if (!adyacencias.containsKey(a.getFin())) {
                adyacencias.put(a.getFin(), new ArrayList<>());
            }
            
            // Agregar conexiones bidireccionales
            adyacencias.get(a.getInicio()).add(a.getFin());
            adyacencias.get(a.getFin()).add(a.getInicio());
        }
        
        
        // BFS para detectar ciclos entre inicio y fin
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitados = new HashSet<>();
        
        queue.add(inicio);
        visitados.add(inicio);

        while (!queue.isEmpty()) {
            int nodo = queue.poll();
            for (int vecino : adyacencias.getOrDefault(nodo, new ArrayList<>())) {
                if (vecino == fin) {
                    return true;
                }
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    queue.add(vecino);
                }
            }
        }
        return false;
    }

    // Mostrar el MST resultante
    public void mostrarMST(List<Aristas> mst) {
        System.out.println("Aristas del Árbol de Expansión Mínima:");
        for (Aristas arista : mst) {
            System.out.println("Inicio: " + arista.getInicio() +
                               ", Fin: " + arista.getFin() +
                               ", Peso: " + arista.getPeso());
        }
    }



	public static void main(String[] args) {
		
		        GrafoPrueba grafo = new GrafoPrueba(6);

		        grafo.agregarArista(0, 1, 0.3);
		        grafo.agregarArista(0, 2, 1.0);
		        grafo.agregarArista(1, 2, 0.5);
		        grafo.agregarArista(1, 3, 0.2);
		        grafo.agregarArista(2, 4, 0.2);
		        grafo.agregarArista(3, 5, 0.4);
		        grafo.agregarArista(4, 5, 0.9);

		        Kruskal kruskal = new Kruskal(grafo);
		        List<Aristas> mst = kruskal.encontrarAGM();
		        kruskal.mostrarMST(mst);
		    }
		


	}


