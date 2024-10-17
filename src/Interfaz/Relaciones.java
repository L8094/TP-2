package Interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import Logica.Aristas;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextArea;

public class Relaciones {
	
	private JFrame frame;
	private JTextField ingresarEspiaUno;
	private JTextField ingresarEspiaDos;
	private JSlider sliderProbabilidadIntercep;
	private String primerEspia;
	private String segundoEspia;
	private double peso;
	private static ArrayList<Aristas> aristaList;
	private double probabilidad;
    private JTextArea mostrarRelac;

 //--------------------------------------------------------------------------------------------------------	
  
    public Relaciones() {
  	    initialize();
  	}
 //--------------------------------------------------------------------------------------------------------

  	public void verificarLista() {
  		if( aristaList.isEmpty()) {
  			JOptionPane.showMessageDialog(frame, "Lista vacia, crear relacion", "ERROR GRAFO VACIO", JOptionPane.WARNING_MESSAGE);
  		}else {
  	    	frame.setVisible(false);
          	GrafoOriginal.main(null);
          	}
  		}
  //--------------------------------------------------------------------------------------------------------	
  	
  	public static List<Aristas> getListAristas(){
  		return aristaList;
  	}
  //--------------------------------------------------------------------------------------------------------	
  	
  	public static void main(String[] args) {
  	EventQueue.invokeLater(new Runnable() {
  			public void run() {
  			try {
  				Relaciones window = new Relaciones();
  				window.frame.setVisible(true);
  				} catch (Exception e) {
  				e.printStackTrace();
  				}
  			}});
  	}
 //--------------------------------------------------------------------------------------------------------
	
    private void mostrarRelacionesEntreEspias(List<Aristas> relac) {
		if (relac.isEmpty() ) {
			return;
		}
    	StringBuilder sb = new StringBuilder();
           sb.append("Relaciones Creadas: \n");
           for (Aristas arista : relac) {
                sb.append(" ").append(arista.getInicio())
                 .append(" & ").append(arista.getFin())
                 .append(", ").append(arista.getPeso()).append(" - ");
            }
            mostrarRelac.setText(sb.toString());
    	} 	
//--------------------------------------------------------------------------------------------------------
	
    private void verRelaciones() {
	    mostrarRelac = new JTextArea();
	    mostrarRelac.setBounds(0, 406, 734, 55);
	    mostrarRelac.setEditable(false); 
	    frame.getContentPane().add(mostrarRelac);
	    if (aristaList != null && !aristaList.isEmpty()) {
	        mostrarRelacionesEntreEspias(aristaList);
	    } else {
	        mostrarRelac.setText("- Para comenzar crea una relacion entre espias -");
	    }
	}
//--------------------------------------------------------------------------------------------------------
	
    private void label_crearRelacion() {
		JLabel lblCrearRelacion = new JLabel("CREAR RELACION");
		lblCrearRelacion.setForeground(Color.WHITE);
		lblCrearRelacion.setFont(new Font("Arial Black", Font.PLAIN, 10));
		lblCrearRelacion.setBounds(118, 353, 100, 14);
		frame.getContentPane().add(lblCrearRelacion);
	}
//--------------------------------------------------------------------------------------------------------
	
    private void label_crearGrafo() {
		JLabel lblCrearGrafo = new JLabel("CREAR GRAFO");
		lblCrearGrafo.setForeground(Color.WHITE);
		lblCrearGrafo.setFont(new Font("Arial Black", Font.PLAIN, 10));
		lblCrearGrafo.setBounds(521, 353, 81, 14);
		frame.getContentPane().add(lblCrearGrafo);
	}
//--------------------------------------------------------------------------------------------------------
	
    private void ingresoPrimerEspia() {
		ingresarEspiaUno = new JTextField();
		ingresarEspiaUno.setBackground(SystemColor.menu);
		ingresarEspiaUno.setBounds(229, 124, 292, 20);
		frame.getContentPane().add(ingresarEspiaUno);
		ingresarEspiaUno.setColumns(10);
	}
//--------------------------------------------------------------------------------------------------------
	
    private void ingresoSegundoEspia() {
		ingresarEspiaDos = new JTextField();
		ingresarEspiaDos.setBackground(UIManager.getColor("Button.background"));
		ingresarEspiaDos.setBounds(229, 193, 292, 20);
		frame.getContentPane().add(ingresarEspiaDos);
		ingresarEspiaDos.setColumns(10);
	}
//--------------------------------------------------------------------------------------------------------
	
    public void slideBarProbabilidadIntercepcion() {
	    sliderProbabilidadIntercep = new JSlider(0, 10);
	    sliderProbabilidadIntercep.setBorder(null);
	    sliderProbabilidadIntercep.setForeground(Color.WHITE);
	    sliderProbabilidadIntercep.setPaintLabels(true);
	    sliderProbabilidadIntercep.setBounds(229, 283, 292, 37);
	    sliderProbabilidadIntercep.setBackground(new Color(0, 51, 102));
	    JLabel probabilidadLabel = new JLabel("0.0");
	    probabilidadLabel.setForeground(new Color(255, 255, 255));
	    probabilidadLabel.setBackground(new Color(240, 240, 240));
	    probabilidadLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
	    probabilidadLabel.setBounds(531, 283, 100, 45); 
	    sliderProbabilidadIntercep.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            probabilidad = sliderProbabilidadIntercep.getValue() / 10.0;
	            probabilidadLabel.setText(String.format("%.1f", probabilidad));
	        }
	    });
	    sliderProbabilidadIntercep.setValue(0);
	    frame.getContentPane().add(sliderProbabilidadIntercep);
	    frame.getContentPane().add(probabilidadLabel); 
	}
//--------------------------------------------------------------------------------------------------------	
	
    private void labelPrimerEspia() {
		JLabel labelEspiaUno = new JLabel("NOMBRE DE ESPIA:");
		labelEspiaUno.setForeground(new Color(255, 255, 255));
		labelEspiaUno.setFont(new Font("Arial Black", Font.PLAIN, 12));
		labelEspiaUno.setBounds(300, 97, 150, 28);
		frame.getContentPane().add(labelEspiaUno);
	}
//--------------------------------------------------------------------------------------------------------	
	
    private void labelSegundoEspia() {
		JLabel labelEspiaDos = new JLabel("NOMBRE DE ESPIA:");
		labelEspiaDos.setForeground(new Color(255, 255, 255));
		labelEspiaDos.setFont(new Font("Arial Black", Font.PLAIN, 12));
		labelEspiaDos.setBounds(300, 175, 150, 14);
		frame.getContentPane().add(labelEspiaDos);
	}
//--------------------------------------------------------------------------------------------------------	

    private void labelPeso() {
		JLabel lblProbabilidadDeIntercepcion = new JLabel("PROBABILIDAD DE INTERCEPCION:");
		lblProbabilidadDeIntercepcion.setForeground(new Color(255, 255, 255));
		lblProbabilidadDeIntercepcion.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblProbabilidadDeIntercepcion.setBounds(256, 255, 238, 14);
		frame.getContentPane().add(lblProbabilidadDeIntercepcion);
	}
//--------------------------------------------------------------------------------------------------------	
	
    private void labelDefinirRelacion() {
		JLabel labelRelacion = new JLabel("DEFINIR RELACION");
		labelRelacion.setForeground(new Color(255, 255, 255));
		labelRelacion.setFont(new Font("Arial Black", Font.PLAIN, 25));
		labelRelacion.setHorizontalAlignment(SwingConstants.CENTER);
		labelRelacion.setBounds(175, 0, 395, 67);
		frame.getContentPane().add(labelRelacion);
	}
//--------------------------------------------------------------------------------------------------------
	
    private void botonCrearRelacion() {
	    JButton botonRelacion = new JButton("                                          ");
	    botonRelacion.setOpaque(false);
	    botonRelacion.setContentAreaFilled(false);
	    botonRelacion.setBorderPainted(false);  
	    		botonRelacion.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    			guardarDatos();
	    		}});
	    			botonRelacion.setBounds(100, 339, 140, 42);
	    			frame.getContentPane().add(botonRelacion);
	}
//--------------------------------------------------------------------------------------------------------
	
    private void botonCrearGrafo() {
	    JButton botonCrearGrafo = new JButton("                                          ");
	    botonCrearGrafo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            verificarLista(); 
	    }});
	    botonCrearGrafo.setBounds(491, 339, 140, 42);
	    frame.getContentPane().add(botonCrearGrafo);
	    botonCrearGrafo.setOpaque(false);
	    botonCrearGrafo.setContentAreaFilled(false);
	    botonCrearGrafo.setBorderPainted(false);
	}
//--------------------------------------------------------------------------------------------------------
		
    private void cargarFondo() {
			ImageIcon originalIcon = new ImageIcon(Relaciones.class.getResource("/Imagenes/fondoRelaciones.jpg"));
		    Image scaledImage = originalIcon.getImage().getScaledInstance(750, 500, Image.SCALE_SMOOTH);
			JLabel fondoRelaciones = new JLabel(new ImageIcon(scaledImage));
			fondoRelaciones.setBounds(0, 0, 750, 500); 
			frame.getContentPane().add(fondoRelaciones);
		}
//--------------------------------------------------------------------------------------------------------		
	
	private void guardarDatos() {
		primerEspia = ingresarEspiaUno.getText();
		segundoEspia = ingresarEspiaDos.getText(); 
		peso = probabilidad;
		if (primerEspia.isEmpty() || segundoEspia.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Ingresar el nombre del espia", "ERROR EN INGRESO ESPIA", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (primerEspia.equals(segundoEspia)) {
			JOptionPane.showMessageDialog(frame, "Ingresaron mismo espias", "ERROR NO SE PUEDE INGRESAR MISMO ESPIA", JOptionPane.WARNING_MESSAGE);
			return;
		}
		Aristas arista = new Aristas(primerEspia, segundoEspia, peso);
		boolean existeRelacion = false;
		for (Aristas existente : aristaList) {
		    if ((existente.getInicio().equals(primerEspia) && existente.getFin().equals(segundoEspia)) ||
		        (existente.getInicio().equals(segundoEspia) && existente.getFin().equals(primerEspia))) {
		        existeRelacion = true;
		    }
		}
	    if (existeRelacion) {
	        JOptionPane.showMessageDialog(frame, "Los espias ya fueron ingresados","Error en ingreso espias",  JOptionPane.WARNING_MESSAGE);
	        return;
	    }
		aristaList.add(arista);
		sliderProbabilidadIntercep.setValue(0);
		ingresarEspiaUno.setText("");
		ingresarEspiaDos.setText("");
		verRelaciones();
	}

//--------------------------------------------------------------------------------------------------------	

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Crear relaciones entre los espias");
		
		aristaList = new ArrayList<>();
		verRelaciones();
		ingresoPrimerEspia();
		ingresoSegundoEspia();
		slideBarProbabilidadIntercepcion();
		labelPrimerEspia();
		labelSegundoEspia();
		labelPeso();
		labelDefinirRelacion(); 
		botonCrearRelacion();
		botonCrearGrafo ();
		label_crearRelacion();
		label_crearGrafo();
		cargarFondo();
	}
}
