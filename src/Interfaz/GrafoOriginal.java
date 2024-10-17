package Interfaz;
	
import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.EventQueue;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;
	import java.util.List;
	import javax.swing.JFrame;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import org.openstreetmap.gui.jmapviewer.Coordinate;
	import org.openstreetmap.gui.jmapviewer.JMapViewer;
	import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
	import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
	import Logica.Aristas;
	import Logica.GenerarRandom;
	import Logica.Grafo;
	import javax.swing.JLabel;
	import javax.swing.BoxLayout;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.SwingConstants;
	import java.awt.Font;
	import java.awt.Image;
	
	
public class GrafoOriginal {
	
	    static JFrame frame;
	    private JPanel panelMapa;
	    private JPanel panelControles;
	    private JMapViewer _mapa;
	    private static ArrayList<String> nombresVertices;
	    private static List<Coordinate> lasCoord = new ArrayList<>();
	    private static Grafo grafo;
	
//--------------------------------------------------------------------------------------------------------   
	
	    public GrafoOriginal() {
	        nombresVertices = new ArrayList<>(); 
	        cargarGrafo();
	    }
//--------------------------------------------------------------------------------------------------------
	   
	    public static Grafo getGrafo() {
	 		return grafo;
	 	}
 //--------------------------------------------------------------------------------------------------------
	    
	    public static List<Coordinate> getcoord(){
		   return lasCoord;
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
	   
	    private JPanel crearPanelComparacion(double tiempoKruskal, double tiempoPrim) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        JLabel labelKruskal = new JLabel("Tiempo de ejecucion de Kruskal: " + tiempoKruskal + " ns");
	        JLabel labelPrim = new JLabel("Tiempo de ejecucion de Prim: " + tiempoPrim + " ns");
	        JLabel labelComparacion;
	        if (tiempoKruskal == 0 || tiempoPrim == 0) {
	            labelComparacion = new JLabel("Falta crear algun AGM para comparar");
	        } else {
	            if (tiempoKruskal < tiempoPrim) {
	                labelComparacion = new JLabel("Kruskal genero un agm mas rapido");
	            } else {
	                labelComparacion = new JLabel("Prim genero un agm mas rapido");
	            }
	        }
	        panel.add(labelKruskal);
	        panel.add(labelPrim);
	        panel.add(labelComparacion);
	        return panel;
	    }
 //--------------------------------------------------------------------------------------------------------
	    
	    private void boton_compararTiempo() {
		    JButton btnCompararTiempos = new JButton("Comparar Tiempos");
		    btnCompararTiempos.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    			mostrarComparacionTiempos(grafo.getTiempoKruskal(), grafo.getTiempoPrim());
		    	}
		    });
		    btnCompararTiempos.setBounds(522, 383, 150, 23);
		    panelControles.add(btnCompararTiempos);
	    }
//--------------------------------------------------------------------------------------------------------
	    
	    private void mostrarComparacionTiempos(double tiempoKruskal, double tiempoPrim) {
	        JPanel panelComparacion = crearPanelComparacion(tiempoKruskal, tiempoPrim);
	        JOptionPane.showMessageDialog(frame, panelComparacion, "Comparacion de Tiempos", JOptionPane.INFORMATION_MESSAGE);
	    }
//--------------------------------------------------------------------------------------------------------
	  
	    private void boton_kruskal() {
	    	JButton Kruskal = new JButton("Kruskal");
	        Kruskal.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		             frame.setVisible(false);
		 			 GrafoKruskal.main(null); 
		        }
		    });
	        Kruskal.setBounds(541, 193, 113, 23);
	        panelControles.add(Kruskal);      
	    }
//--------------------------------------------------------------------------------------------------------
	   
	    private void boton_prim() {
	        JButton Prim = new JButton("Prim");
	        Prim.setBounds(541, 247, 113, 23);
	        Prim.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		             frame.setVisible(false);
		             GrafoPrim.main(null);
		 			 	        }
		    });
	        panelControles.add(Prim);
	    }
//--------------------------------------------------------------------------------------------------------
	
	    private void cargarFondo() {
		     ImageIcon originalIcon= new ImageIcon(Relaciones.class.getResource("/Imagenes/fondoGrafos.jpg"));
			 Image scaledImage = originalIcon.getImage().getScaledInstance(750, 500, Image.SCALE_SMOOTH); 
			 JLabel fondoGrafoOriginal = new JLabel(new ImageIcon(scaledImage));
			 fondoGrafoOriginal.setBounds(0, 0, 734, 461);
	        panelControles.add(fondoGrafoOriginal);
	    }
//--------------------------------------------------------------------------------------------------------
	
	    private void label() {
	        JLabel lblNewLabel = new JLabel("Arbol generador minimo:");
	        lblNewLabel.setForeground(new Color(255, 255, 255));
	        lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setBounds(482, 95, 230, 55);
	        panelControles.add(lblNewLabel);          
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
	            JOptionPane.showMessageDialog(frame, "GRAFO NO CONEXO - VUELVA A INGRESAR", "ERROR GRAFO", JOptionPane.WARNING_MESSAGE);        
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
	        for (int i = 0; i < cantidadVertices; i++) {
	        	double lat = GenerarRandom.generarLatitud();
	        	double lon = GenerarRandom.generarLongitud();
	            Coordinate coord = new Coordinate(lat, lon);
	            lasCoord.add(coord);
	            String nombre = nombresVertices.get(i);
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
	        frame.setTitle("Crear el camino mas seguro");
	
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
	        label();
	        boton_compararTiempo();
	        boton_kruskal();
	        boton_prim();
	        cargarFondo();
	    }
	}
