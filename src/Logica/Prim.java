package Logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prim {

    private Grafo grafo;
    private Set<String> visitados; 

 //--------------------------------------------------------------------------------------------------------	

    public Prim(Grafo grafo) {
        this.grafo = grafo;
    }

 //--------------------------------------------------------------------------------------------------------	

    public List<Aristas> encontrarAGM() {
        List<Aristas> aristasDeAgm = new ArrayList<>();
        visitados = new HashSet<>();
        if(grafo.getAristas().size()>0) {
        String verticeInicial = grafo.getAristas().get(0).getInicio(); 
        visitados.add(verticeInicial); 
        while (visitados.size() < Grafo.getCantVertices()) {
            Aristas aristaMinima = encontrarAristaMinima(); 
            if (!Grafo.formaCiclo(aristaMinima, aristasDeAgm)) {
                aristasDeAgm.add(aristaMinima);
                String nuevoVertice = visitados.contains(aristaMinima.getInicio()) ? aristaMinima.getFin() : aristaMinima.getInicio();
                visitados.add(nuevoVertice);
            }
        }
         return Grafo.esConexo(aristasDeAgm) ? aristasDeAgm : null; 
        }
        else {
        	return null;
        }
        
        
    }
   
//--------------------------------------------------------------------------------------------------------	

    private Aristas encontrarAristaMinima() {
        Aristas aristaMinima = null;
        double pesoMinimo = Double.MAX_VALUE;
        for (Aristas arista : grafo.getAristas()) {
            String inicio = arista.getInicio();
            String fin = arista.getFin();
            if ((visitados.contains(inicio) && !visitados.contains(fin)) || (visitados.contains(fin) && !visitados.contains(inicio))) {
                if (arista.getPeso() < pesoMinimo) {
                    pesoMinimo = arista.getPeso();
                    aristaMinima = arista;
                }
            }
        }
        return aristaMinima;
    }
}
