package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import Logica.Aristas;
import Logica.Grafo;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class GrafoOriginal {

    static JFrame frame;
    private JPanel panelMapa;
    private JPanel panelControles;
    private JMapViewer _mapa;
    private Random random = new Random();
    private static ArrayList<String> nombresVertices;
    private static List<Coordinate> lasCoord = new ArrayList<>();
    private static Grafo grafo;

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
    public void label() {
        
        JLabel lblNewLabel = new JLabel("Arbol generador minimo: ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(492, 46, 186, 55);
        panelControles.add(lblNewLabel);          
        JButton Kruskal = new JButton("Kruskal");
        Kruskal.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	             frame.setVisible(false);
	 			 GrafoKruskal.main(null);	        }
	    });
        Kruskal.setBounds(528, 172, 113, 23);
        panelControles.add(Kruskal);       
        JButton Prim = new JButton("Prim");
        Prim.setBounds(528, 298, 113, 23);
        Prim.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	             frame.setVisible(false);
	             GrafoPrim.main(null);
	 			 	        }
	    });
        panelControles.add(Prim);
    }

   //--------------------------------------------------------------------------------------------------------
    
    private void cargarGrafo() {
        List<Aristas> listAristas = Relaciones.getListAristas();
        int cantNodos = cantidadNodos(listAristas);
        grafo = new Grafo(cantNodos);
        for (Aristas arista : listAristas) {
            grafo.agregarArista(arista);
        }
        
        if (!Grafo.esConexo(listAristas)) {
            JOptionPane.showMessageDialog(frame, "El grafo original no es conexo", "ERROR INGRESANDO GRAFO - NO CONEXO", JOptionPane.WARNING_MESSAGE);        
            
            Relaciones.main(null);
           
            return;
        }
        
        initialize();  
        frame.setVisible(true);  
        dibujarVertices(cantNodos);
        dibujarAristas(listAristas, Color.RED);
    }

   //--------------------------------------------------------------------------------------------------------
    
    private void dibujarVertices(int cantidadVertices) {
        double latMin = -69.52;
        double latMax = -66.35;
        double lonMin = -87.25;
        double lonMax = -78.77; 
        for (int i = 0; i < cantidadVertices; i++) {
            double lat = latMin + (latMax - latMin) * random.nextDouble();
            double lon = lonMin + (lonMax - lonMin) * random.nextDouble();
            Coordinate coord = new Coordinate(lat, lon);
            lasCoord.add(coord);
            String nombre = nombresVertices.get(i);
            _mapa.addMapMarker(new MapMarkerDot(nombre, coord));
        }
    }

//--------------------------------------------------------------------------------------------------------
  
    private void dibujarAristas(List<Aristas> listaAristas, Color C ) {
        for (Aristas arista : listaAristas) {
            int inicioIndex = nombresVertices.indexOf(arista.getInicio());
            int finIndex = nombresVertices.indexOf(arista.getFin());
            if (inicioIndex != -1 && finIndex != -1) {
                Coordinate inicioCoord = lasCoord.get(inicioIndex);
                Coordinate finCoord = lasCoord.get(finIndex);
                List<Coordinate> coordenadas = new ArrayList<>();
                coordenadas.add(inicioCoord); 
                coordenadas.add(finCoord);    
                coordenadas.add(inicioCoord);
                MapPolygonImpl lineaArista = new MapPolygonImpl(coordenadas);
				lineaArista.setColor(C);
                _mapa.addMapPolygon(lineaArista); 
            }
        }
    }
  

    //--------------------------------------------------------------------------------------------------------
 
    public static List<Coordinate> getcoord(){
	   return lasCoord;
   }
 
    //--------------------------------------------------------------------------------------------------------
   
   public static Grafo getGrafo() {
		return grafo;
	}
 
   //--------------------------------------------------------------------------------------------------------
 
   public static List<String> getNombresVertices() {
	    return nombresVertices;
	}

 //--------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
    	 EventQueue.invokeLater(new Runnable() {
             public void run() {
                 try {
                     GrafoOriginal window = new GrafoOriginal();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         });  
    }
    
 //--------------------------------------------------------------------------------------------------------   

   
    public GrafoOriginal() {
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