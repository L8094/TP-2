package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import Logica.Aristas;
import Logica.Grafo;
import Logica.Kruskal;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GrafoKruskal {

	    static JFrame frame;
	    private JPanel panelMapa;
	    private JPanel panelControles;
	    private JMapViewer _mapa;
	    private JTextArea textArea;
	    private ArrayList<String> nombresVertices;
	    private List<Coordinate> lasCoord = new ArrayList<>();
	    private  long tiempoEjecucion;
	    private boolean creado = false;
    
    
//--------------------------------------------------------------------------------------------------------   
	
	    public GrafoKruskal() {
	        nombresVertices = new ArrayList<>(); 
	       
	        cargarGrafo();
	    }
//--------------------------------------------------------------------------------------------------------
	    
    	public long getTiempoEjecucion() {
	    	return this.tiempoEjecucion;
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
    
	    private void mostrarAGM(List<Aristas> agm) {
	    	if(creado == false) {
	    		this.creado = true;
	    		StringBuilder sb = new StringBuilder();
	            sb.append("Arbol generador minimo Kruskal: \n");
	            for (Aristas arista : agm) {
	                sb.append("Inicio: ").append(arista.getInicio())
	                  .append(", Fin: ").append(arista.getFin())
	                  .append(", Peso: ").append(arista.getPeso()).append("\n");
	            }
	            sb.append("Tiempo de ejecucion: \n"+ tiempoEjecucion +" Nanosegundos");
	            textArea.setText(sb.toString());
	    	}       
	    }
	    
//--------------------------------------------------------------------------------------------------------

	    private void boton_grafo() {
	        JButton grafoInterfaz = new JButton("Grafo original");
	        grafoInterfaz.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		             frame.setVisible(false);
		 			 GrafoOriginal.frame.setVisible(true);;
		 			 }
		    });
	        grafoInterfaz.setBounds(524, 376, 148, 23);
	        panelControles.add(grafoInterfaz);    
	    }
 //--------------------------------------------------------------------------------------------------------
	    
	    private void cargarFondo() {
	        ImageIcon originalIcon= new ImageIcon(Relaciones.class.getResource("/Imagenes/fondoGrafos.jpg"));
		 	Image scaledImage = originalIcon.getImage().getScaledInstance(750, 500, Image.SCALE_SMOOTH);
		 	JLabel fondoGrafoKruskal = new JLabel(new ImageIcon(scaledImage));
		    fondoGrafoKruskal.setBounds(0, 0, 734, 461);
		    panelControles.add(fondoGrafoKruskal);
	    }
 //--------------------------------------------------------------------------------------------------------
   
	    private void label() {
	        textArea = new JTextArea();
	        textArea.setBounds(490, 32, 210, 277);
	        textArea.setEditable(false);
	        panelControles.add(textArea);
	    }

//--------------------------------------------------------------------------------------------------------
   
	    private void cargarGrafo() {
	    	
	    	if(!this.creado) {
		        Grafo grafo = GrafoOriginal.getGrafo();
		        List<Aristas> listAristas = Relaciones.getListAristas();
		        nombresVertices = new ArrayList<>(GrafoOriginal.getNombresVertices());
		        Kruskal kruskal = new Kruskal(grafo);
		    	long inicioTiempo =  System.nanoTime();
		        List<Aristas> agm = kruskal.encontrarAGM();
		        long finTiempo= System.nanoTime();;
		        this.tiempoEjecucion= finTiempo - inicioTiempo;
		        grafo.setTiempoKruskal(tiempoEjecucion);
		        initialize();  
		        frame.setVisible(true);  
		        mostrarAGM(agm);
		        dibujarVertices();
		        dibujarAristas(listAristas, Color.RED);
		        dibujarAristas(agm, Color.BLUE);
	    	}
	    }
//--------------------------------------------------------------------------------------------------------
   
		 private void dibujarVertices() {
		        List<Coordinate> coordenadas = GrafoOriginal.getcoord();
		        lasCoord.addAll(coordenadas);
		        for (int i = 0; i < nombresVertices.size(); i++) {
		            String nombre = nombresVertices.get(i);
		            Coordinate coord = coordenadas.get(i);
		            MapMarkerDot marker = new MapMarkerDot(nombre, coord);
		            marker.setBackColor(Color.BLACK);
		            marker.setColor(Color.YELLOW);
		            _mapa.addMapMarker(marker);
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

	    private void initialize() {
	        frame = new JFrame();
	        frame.setResizable(false);
	        frame.setBounds(100, 100, 750, 500);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setTitle("Camino seguro creado mediante algoritmo Kruskal");
	        
	        panelMapa = new JPanel();
	        panelMapa.setBounds(10, 11, 437, 446);
	        frame.getContentPane().add(panelMapa);
	        panelControles = new JPanel();
	        panelControles.setBounds(457, 11, 242, 446);
	        frame.getContentPane().add(panelControles);
	        panelControles.setLayout(null);
	        
	        _mapa = new JMapViewer();
	        _mapa.setDisplayPosition(new Coordinate(-68, -83), 6);
	        _mapa.setZoomControlsVisible(false);
	        panelMapa.setLayout(new BorderLayout());
	        panelMapa.add(_mapa, BorderLayout.CENTER);
	        panelMapa.add(_mapa);
	        boton_grafo();
	        label();
	        cargarFondo();
	    }
}
