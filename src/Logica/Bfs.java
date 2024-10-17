package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Bfs {
	    
	 public static boolean bfs(String vertice, Set<String> visitados, List<Aristas> agm, String objetivo) {
	        Queue<String> cola = new LinkedList<>();
	        cola.add(vertice);
	        visitados.add(vertice);

	        Map<String, List<String>> adyacencias = new HashMap<>();
	        for (Aristas a : agm) {
	            adyacencias.putIfAbsent(a.getInicio(), new ArrayList<>());
	            adyacencias.putIfAbsent(a.getFin(), new ArrayList<>());
	            adyacencias.get(a.getInicio()).add(a.getFin());
	            adyacencias.get(a.getFin()).add(a.getInicio());
	        }

	        while (!cola.isEmpty()) {
	            String nodo = cola.poll();
	            for (String vecino : adyacencias.getOrDefault(nodo, Collections.emptyList())) {
	                if (vecino.equals(objetivo)) {
	                    return true; 
	                }
	                if (!visitados.contains(vecino)) {
	                    visitados.add(vecino);
	                    cola.add(vecino);
	                }
	            }
	        }
	        return false; 
	    }
	
}
