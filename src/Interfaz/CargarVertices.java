package Interfaz;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import Logica.Aristas;
import Logica.Grafo;
import Logica.Kruskal;



public class CargarVertices {

    private JFrame frame;
    private JPanel panelMapa;
    private JPanel panelControles;
    private JMapViewer _mapa;
    private JTextArea textArea;
    private Random random = new Random();
    private ArrayList<String> nombresVertices;

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
        sb.append("Aristas del arbol de expansion minima: \n");
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
        textArea.setBounds(490, 32, 210, 363);
        textArea.setEditable(false);
        panelControles.add(textArea);
    }

    //--------------------------------------------------------------------------------------------------------
    private void cargarGrafo() {
        List<Aristas> listAristas = Relaciones.getListAristas();
        int cantNodos = cantidadNodos(listAristas);
        Grafo grafo = new Grafo(cantNodos);

        for (Aristas arista : listAristas) {
            grafo.agregarArista(arista);
        }

        Kruskal kruskal = new Kruskal(grafo);
        List<Aristas> agm = kruskal.encontrarAGM();
        mostrarAGM(agm);

       
        dibujarVertices(cantNodos);
        
    }

    //--------------------------------------------------------------------------------------------------------
    private void dibujarVertices(int cantidadVertices) {
        double latCentro = -15; 
        double lonCentro = -140; 
        double rangoLatitud = 0.04; 
        double rangoLongitud = 0.04; 

        for (int i = 0; i < cantidadVertices; i++) {
            double lat = latCentro - (rangoLatitud * random.nextDouble());
            double lon = lonCentro - (rangoLongitud * random.nextDouble()); 
            Coordinate coord = new Coordinate(lat, lon);
            String nombre = nombresVertices.get(i); 
            _mapa.addMapMarker(new MapMarkerDot(nombre, coord));
        }
    }

    //--------------------------------------------------------------------------------------------------------   
    
    
    
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CargarVertices window = new CargarVertices();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CargarVertices() {
        nombresVertices = new ArrayList<>(); 
        initialize(); 
    }

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
        _mapa.setDisplayPosition(new Coordinate(-15, -140), 13);
        _mapa.setZoomContolsVisible(false);

        panelMapa.add(_mapa);
        label();
        cargarGrafo();
    }
}
