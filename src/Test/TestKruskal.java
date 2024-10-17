

package Test;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Logica.Aristas;
import Logica.Grafo;
import Logica.Kruskal;

public class TestKruskal {

	private Grafo grafo;
//	private Grafo grafoNoConexo;
    private Kruskal kruskal;
    private Kruskal kruskalDuplicado;
//    private Kruskal kruskalNoConexo;
    
//------------------------------------------------------------------------------------------------------------------  
    @Before
    public void setUp() {
        grafo = new Grafo(6);
//        grafoNoConexo = new Grafo(2);
        // Agregar aristas al grafo
        grafo.agregarArista(new Aristas("0", "1", 0.5));
        grafo.agregarArista(new Aristas("0", "2", 0.2));
        grafo.agregarArista(new Aristas("1", "2", 0.7));
        grafo.agregarArista(new Aristas("1", "3", 0.3));
        grafo.agregarArista(new Aristas("2", "4", 0.3));
        grafo.agregarArista(new Aristas("3", "5", 0.1));
        grafo.agregarArista(new Aristas("4", "5", 1.0));  
        
//        grafoNoConexo.agregarArista(new Aristas("1", "3", 0.3));
//        grafoNoConexo.agregarArista(new Aristas("2", "4", 0.2));
        
//        kruskalNoConexo = new Kruskal(grafoNoConexo);
        kruskal = new Kruskal(grafo);
        
    }
//------------------------------------------------------------------------------------------------------------------
    @Test
    public void testKruskalAGM() {
        // Ejecutar el algoritmo de Kruskal en el grafo
        List<Aristas> arbolGeneradorMinimo = kruskal.encontrarAGM();

        // Verificar que el numero de aristas en el AGM sea correcto: numero de nodos - 1
        assertEquals(5, arbolGeneradorMinimo.size());

        // Verificar las aristas esperadas en el AGM
        // Estas deben ser las aristas de menor peso que conecten todos los nodos
        assertTrue(contieneArista(arbolGeneradorMinimo, "3", "5", 0.1));
        assertTrue(contieneArista(arbolGeneradorMinimo, "0", "2", 0.2));
        assertTrue(contieneArista(arbolGeneradorMinimo, "1", "3", 0.3));
        assertTrue(contieneArista(arbolGeneradorMinimo, "2", "4", 0.3));
        assertTrue(contieneArista(arbolGeneradorMinimo, "0", "1", 0.5));
    }
    
    
//------------------------------------------------------------------------------------------------------------------
    @Test
    public void testGrafoVacio() {
        Grafo grafoVacio = new Grafo(6);
        Kruskal kruskalVacio = new Kruskal(grafoVacio);

        // Un grafo vacio deberia tener un AGM vacio
        List<Aristas> arbolGeneradorMinimo = kruskalVacio.encontrarAGM();
        assertTrue(arbolGeneradorMinimo == null);
    }
    
     
//------------------------------------------------------------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testAristaConPesoNegativo() {
        grafo.agregarArista(new Aristas("1", "2", -0.5));
    }
    
    
    
//------------------------------------------------------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testAristasDuplicadas() {
    	Grafo gr = new Grafo(3);
        gr.agregarArista(new Aristas("0", "1", 0.5));
        gr.agregarArista(new Aristas("0", "1", 0.5)); // Duplicada
        gr.agregarArista(new Aristas("1", "2", 0.7));
        kruskalDuplicado = new Kruskal(gr);
  
        List<Aristas> arbolGeneradorMinimo = kruskalDuplicado.encontrarAGM();

        assertEquals(2, arbolGeneradorMinimo.size()); // 2 aristas: (0,1) y (1,2)
    }
    
//------------------------------------------------------------------------------------------------------------------  
    @Test
    public void testAristasMismoPeso() {
        Grafo grafoMismoPeso = new Grafo(3);
        grafoMismoPeso.agregarArista(new Aristas("0", "1", 0.5));
        grafoMismoPeso.agregarArista(new Aristas("1", "2", 0.5));
        grafoMismoPeso.agregarArista(new Aristas("0", "2", 0.5));

        Kruskal kruskalMismoPeso = new Kruskal(grafoMismoPeso);
        
        List<Aristas> arbolGeneradorMinimo = kruskalMismoPeso.encontrarAGM();

        // Deberia devolver 2 aristas, ya que el AGM solo necesita conectar los 3 nodos
        assertEquals(2, arbolGeneradorMinimo.size());
    }
    
    
//------------------------------------------------------------------------------------------------------------------   
    @Test
    public void testGrafoConUnSoloNodoSinAristas() {
        Grafo grafoUnNodoSinAristas = new Grafo(1); // Grafo con 1 nodo y sin aristas
        Kruskal kruskalUnNodoSinAristas = new Kruskal(grafoUnNodoSinAristas);
        
        List<Aristas> arbolGeneradorMinimo = kruskalUnNodoSinAristas.encontrarAGM();
        
        // Un grafo con un solo nodo y sin aristas deberia devolver un AGM vacio
        assertTrue(arbolGeneradorMinimo == null);
    }

//------------------------------------------------------------------------------------------------------------------  
    
   
    
    
   
    
    
//-----------------------------------------METODOS AUXILIARES----------------------------------------------------------
   
    // Metodo auxiliar para verificar si una arista esta en la lista
    private boolean contieneArista(List<Aristas> aristas, String inicio, String fin, double peso) {
        for (Aristas arista : aristas) {
        	if ((arista.getInicio().equals(inicio) && arista.getFin().equals(fin) && arista.getPeso() == peso) ||
        	(arista.getInicio().equals(fin) && arista.getFin().equals(inicio) && arista.getPeso() == peso)) {
                return true;
            }
        }
        return false;
    }
}