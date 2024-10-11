package Logica;



import java.util.*;

public class Kruskal {
    private Grafo grafo;

    public Kruskal(Grafo grafo) {
        this.grafo = grafo;
    }
//--------------------------------------------------------------------------------------------------------	
	    
    public List<Aristas> encontrarAGM() {
        List<Aristas> agm = new ArrayList<>();
        List<Aristas> aristas = new ArrayList<>(grafo.getAristas());
 
        ordenarAristasPorPeso(aristas);
        
        for (Aristas arista : aristas) {
            if (!formaCiclo(arista, agm)) {
                agm.add(arista);
            }
        }

        return agm;
    }
    
//--------------------------------------------------------------------------------------------------------	
    public List<Aristas> ordenarAristasPorPeso(List<Aristas> aristas) {
            int n = aristas.size();
            for (int i = 0; i < n - 1; i++) {
            	int indiceMinimo = i;
                for (int j = i + 1; j < n; j++) {
                    if (aristas.get(j).getPeso() < aristas.get(indiceMinimo).getPeso()) {
                        indiceMinimo = j;
                    }
                }
                Aristas temporal = aristas.get(i);  
                aristas.set(i, aristas.get(indiceMinimo));
                aristas.set(indiceMinimo, temporal);
            }

            return aristas;
        }
    
//--------------------------------------------------------------------------------------------------------		
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
}

