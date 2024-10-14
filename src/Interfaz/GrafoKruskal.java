package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import Logica.Aristas;
import Logica.Grafo;
import Logica.Kruskal;
import javax.swing.JButton;



public class GrafoKruskal {

    private JFrame frame;
    private JPanel panelMapa;
    private JPanel panelControles;
    private JMapViewer _mapa;
    private JTextArea textArea;
    private ArrayList<String> nombresVertices;
    private List<Coordinate> lasCoord = new ArrayList<>();
    

    //--------------------------------------------------------------------------------------------------------
    private int cantidadNodos(List<Aristas> listAristas) {
        for (Aristas arista : listAristas) {
            String inicio = arista.getInicio();
            String fin = arista.getFin();
            if (!nombresVertices.contains(inicio)) {
                nombresVertices.add(inicio);
            }
            if (!nombresVertices.contains(fin)) {
                nombresVertices.add(fin);
            }
        }
        return nombresVertices.size(); 
    }

    //--------------------------------------------------------------------------------------------------------
    public void mostrarAGM(List<Aristas> agm) {
        StringBuilder sb = new StringBuilder();
        sb.append("Arbol generador minimo Kruskal: \n");
        for (Aristas arista : agm) {
            sb.append("Inicio: ").append(arista.getInicio())
              .append(", Fin: ").append(arista.getFin())
              .append(", Peso: ").append(arista.getPeso()).append("\n");
        }
        textArea.setText(sb.toString());
    }

   //--------------------------------------------------------------------------------------------------------
    public void label() {
        textArea = new JTextArea();
        textArea.setBounds(490, 32, 210, 277);
        textArea.setEditable(false);
        panelControles.add(textArea);
        
        JButton grafoInterfaz = new JButton("Grafo original");
        grafoInterfaz.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	             frame.setVisible(false);
	 			 GrafoInterfaz.frame.setVisible(true);	        }
	    });
        grafoInterfaz.setBounds(524, 376, 148, 23);
        panelControles.add(grafoInterfaz);
    }

   //--------------------------------------------------------------------------------------------------------
    private void cargarGrafo() {
        List<Aristas> listAristas = Relaciones.getListAristas();
        
        int cantNodos = cantidadNodos(listAristas);
        Grafo grafo = new Grafo(cantNodos);

        for (Aristas arista : listAristas) {
            grafo.agregarArista(arista);
        }
//ver cuanto tiempo tarda en crear el grafo: 
        Kruskal kruskal = new Kruskal(grafo);
        List<Aristas> agm = kruskal.encontrarAGM();
        
        if (agm == null) {
            JOptionPane.showMessageDialog(frame, "El grafo original no es conexo", "ERROR INGRESANDO GRAFO - NO CONEXO", JOptionPane.WARNING_MESSAGE);        
            
            Relaciones.main(null);
           
            return;
        }
        
        initialize();  
        frame.setVisible(true);  
        
        mostrarAGM(agm);
        dibujarVertices();
        dibujarAristas(listAristas, Color.RED);
        dibujarAristas(agm, Color.BLUE);
    }

   //--------------------------------------------------------------------------------------------------------
    private void dibujarVertices() {
        List<Coordinate> coordenadas = GrafoInterfaz.getcoord();
        lasCoord.addAll(coordenadas);
        for (int i = 0; i < nombresVertices.size(); i++) {
            String nombre = nombresVertices.get(i);
            Coordinate coord = coordenadas.get(i);
            _mapa.addMapMarker(new MapMarkerDot(nombre, coord));
    }
    }
   
    
    private void dibujarAristas(List<Aristas> listaAristas, Color C ) {
        

        for (Aristas arista : listaAristas) {
            int inicioIndex = nombresVertices.indexOf(arista.getInicio());
            int finIndex = nombresVertices.indexOf(arista.getFin());
            
            // Solo procede si ambos índices son válidos.
            if (inicioIndex != -1 && finIndex != -1) {
                Coordinate inicioCoord = lasCoord.get(inicioIndex);
                Coordinate finCoord = lasCoord.get(finIndex);

                List<Coordinate> coordenadas = new ArrayList<>();
                coordenadas.add(inicioCoord); // Punto de inicio
                coordenadas.add(finCoord);    // Punto final
                coordenadas.add(inicioCoord);

               
                MapPolygonImpl lineaArista = new MapPolygonImpl(coordenadas);
				lineaArista.setColor(C);
                _mapa.addMapPolygon(lineaArista); 
            }
        }
    }
    
    
    
   //--------------------------------------------------------------------------------------------------------   
    
    public static void main(String[] args) {
    	 EventQueue.invokeLater(new Runnable() {
             public void run() {
                 try {
                     GrafoKruskal window = new GrafoKruskal();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         });  
    }
    
 //--------------------------------------------------------------------------------------------------------   

    public GrafoKruskal() {
        nombresVertices = new ArrayList<>(); 
       
        cargarGrafo();
    }
 
 //--------------------------------------------------------------------------------------------------------   

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 750, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMapa = new JPanel();
        panelMapa.setBounds(10, 11, 437, 446);
        frame.getContentPane().add(panelMapa);

        panelControles = new JPanel();
        panelControles.setBounds(457, 11, 242, 446);
        frame.getContentPane().add(panelControles);
        panelControles.setLayout(null);

        _mapa = new JMapViewer();
        _mapa.setDisplayPosition(new Coordinate(-68, -83), 6);
        _mapa.setZoomContolsVisible(false);

        panelMapa.add(_mapa);
        label();
        
    }
}