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
            if (!Grafo.formaCiclo(arista, agm)) {
                agm.add(arista);
            }
        }
        return Grafo.esConexo(agm) ? agm : null;
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
            Collections.swap(aristas, i, indiceMinimo);
        }
        return aristas;
    }	
   

    //--------------------------------------------------------------------------------------------------------


   
   
}
