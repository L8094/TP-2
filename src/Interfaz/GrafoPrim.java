package Interfaz;

	import java.awt.Color;
	import java.awt.EventQueue;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;
	import java.util.List;
	import javax.swing.JFrame;	
	import javax.swing.JPanel;
	import javax.swing.JTextArea;
	import org.openstreetmap.gui.jmapviewer.Coordinate;
	import org.openstreetmap.gui.jmapviewer.JMapViewer;
	import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
	import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
	import Logica.Aristas;
	import Logica.Grafo;
	import Logica.Prim;

import javax.swing.JButton;


	public class GrafoPrim {

	    private JFrame frame;
	    private JPanel panelMapa;
	    private JPanel panelControles;
	    private JMapViewer _mapa;
	    private JTextArea textArea;
	    private ArrayList<String> nombresVertices;
	    private List<Coordinate> lasCoord = new ArrayList<>();
	    
	    //--------------------------------------------------------------------------------------------------------
	    public void mostrarAGM(List<Aristas> agm) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Arbol generador minimo Prim: \n");
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
		 			 GrafoOriginal.frame.setVisible(true);	        }
		    });
	        grafoInterfaz.setBounds(524, 376, 148, 23);
	        panelControles.add(grafoInterfaz);
	    }

	   //--------------------------------------------------------------------------------------------------------
	    private void cargarGrafo() {
	    	Grafo grafo = GrafoOriginal.getGrafo();
	        List<Aristas> listAristas = Relaciones.getListAristas();
	        nombresVertices = new ArrayList<>(GrafoOriginal.getNombresVertices());
	        Prim prim = new Prim(grafo);
	        List<Aristas> agm = prim.encontrarAGM();  
	        initialize();  
	        frame.setVisible(true);  
	        mostrarAGM(agm);
	        dibujarVertices();
	        dibujarAristas(listAristas, Color.RED);
	        dibujarAristas(agm, Color.GREEN);
	    }


//--------------------------------------------------------------------------------------------------------
	    private void dibujarVertices() {
	        List<Coordinate> coordenadas = GrafoOriginal.getcoord();
	        lasCoord.addAll(coordenadas);
	        for (int i = 0; i < nombresVertices.size(); i++) {
	            String nombre = nombresVertices.get(i);
	            Coordinate coord = coordenadas.get(i);
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
	    
	    public static void main(String[] args) {
	    	 EventQueue.invokeLater(new Runnable() {
	             public void run() {
	                 try {
	                     GrafoPrim window = new GrafoPrim();
	                 } catch (Exception e) {
	                     e.printStackTrace();
	                 }
	             }
	         });  
	    }
	    
	 //--------------------------------------------------------------------------------------------------------   

	    public GrafoPrim() {
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

