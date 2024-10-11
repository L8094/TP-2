package Interfaz;

import java.awt.EventQueue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import Logica.Aristas;
import Logica.Grafo;
import Logica.Kruskal;
import javax.swing.JTextArea;



public class MostrarKruskal {

	private JFrame frame;
	private JTextArea textArea;
	
	private int cantidadNodos(List<Aristas> listAristas) {
		Set<Integer> nodosUnicos = new HashSet<>();
		for (Aristas arista : listAristas) {
			nodosUnicos.add(arista.getInicio());
			nodosUnicos.add(arista.getFin());
		    }
		return nodosUnicos.size();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	 public void mostrarAGM(List<Aristas> agm) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Aristas del Árbol de Expansión Mínima:\n");
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
	        textArea.setBounds(20, 39, 679, 363); 
	        textArea.setEditable(false); 
	        frame.getContentPane().add(textArea); 
	    }

//--------------------------------------------------------------------------------------------------------	
	
	    private void cargarGrafo() {
	    List<Aristas> listAristas = Relaciones.getListAristas(); 
	    int cantNodos = cantidadNodos(listAristas); 
	    Grafo grafo = new Grafo(cantNodos); 
	   
	    for (Aristas arista : listAristas) {
	        grafo.agregarArista(arista);
	        
	     Kruskal kruskal = new Kruskal(grafo);
		 List<Aristas> agm = kruskal.encontrarAGM();
		 mostrarAGM(agm);    
	    }
	   }
	    
//--------------------------------------------------------------------------------------------------------	
	    
	    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarKruskal window = new MostrarKruskal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MostrarKruskal() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		label();
		cargarGrafo();
		 
	}

	

}
