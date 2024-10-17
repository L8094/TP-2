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
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	
//--------------------------------------------------------------------------------------------------------
	public void ingresoPrimerEspia() {
		ingresarEspiaUno = new JTextField();
		ingresarEspiaUno.setBackground(SystemColor.menu);
		ingresarEspiaUno.setBounds(327, 124, 292, 20);
		frame.getContentPane().add(ingresarEspiaUno);
		ingresarEspiaUno.setColumns(10);
	}
//--------------------------------------------------------------------------------------------------------
	public void ingresoSegundoEspia() {
		ingresarEspiaDos = new JTextField();
		ingresarEspiaDos.setBackground(UIManager.getColor("Button.background"));
		ingresarEspiaDos.setBounds(327, 191, 292, 20);
		frame.getContentPane().add(ingresarEspiaDos);
		ingresarEspiaDos.setColumns(10);
	}
//--------------------------------------------------------------------------------------------------------
	public void ingresoProbabilidadIntercepcion() {
	    sliderProbabilidadIntercep = new JSlider(0, 10);
	    sliderProbabilidadIntercep.setBounds(327, 258, 292, 45);
	    sliderProbabilidadIntercep.setBackground(UIManager.getColor("Button.background"));
	    sliderProbabilidadIntercep.setPaintLabels(true);
	    sliderProbabilidadIntercep.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        probabilidad = sliderProbabilidadIntercep.getValue() / 10.0;
	            System.out.println("Probabilidad de Intercepción: " + probabilidad);
	        }
	    });
	    sliderProbabilidadIntercep.setValue(0);
	    frame.getContentPane().add(sliderProbabilidadIntercep);
	}


	
//--------------------------------------------------------------------------------------------------------	
	public void labelPrimerEspia() {
		JLabel labelEspiaUno = new JLabel("NOMBRE DE ESPIA:");
		labelEspiaUno.setForeground(Color.BLACK);
		labelEspiaUno.setFont(new Font("Arial Black", Font.PLAIN, 12));
		labelEspiaUno.setBounds(100, 123, 150, 28);
		frame.getContentPane().add(labelEspiaUno);
	}
//--------------------------------------------------------------------------------------------------------	
	public void labelSegundoEspia() {
		JLabel labelEspiaDos = new JLabel("NOMBRE DE ESPIA:");
		labelEspiaDos.setForeground(Color.BLACK);
		labelEspiaDos.setFont(new Font("Arial Black", Font.PLAIN, 12));
		labelEspiaDos.setBounds(100, 197, 150, 14);
		frame.getContentPane().add(labelEspiaDos);
	}
//--------------------------------------------------------------------------------------------------------	
	public void labelPeso() {
		JLabel lblProbabilidadDeIntercepcion = new JLabel("PROBABILIDAD DE INTERCEPCION:");
		lblProbabilidadDeIntercepcion.setForeground(Color.BLACK);
		lblProbabilidadDeIntercepcion.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblProbabilidadDeIntercepcion.setBounds(83, 271, 300, 14);
		frame.getContentPane().add(lblProbabilidadDeIntercepcion);
	}
//--------------------------------------------------------------------------------------------------------	
		public void labelDefinirRelacion() {
		JLabel labelRelacion = new JLabel("DEFINIR RELACION");
		labelRelacion.setForeground(Color.BLACK);
		labelRelacion.setFont(new Font("Arial Black", Font.PLAIN, 25));
		labelRelacion.setHorizontalAlignment(SwingConstants.CENTER);
		labelRelacion.setBounds(153, 11, 395, 67);
		frame.getContentPane().add(labelRelacion);
	}
//--------------------------------------------------------------------------------------------------------
	
	public void botonCrearRelacion() {
		JButton botonRelacion = new JButton("CREAR RELACION");
		botonRelacion.setForeground(Color.BLUE);
		botonRelacion.setBackground(Color.LIGHT_GRAY);

		botonRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarDatos();
		}});

		botonRelacion.setBounds(100, 358, 134, 23);
		frame.getContentPane().add(botonRelacion);
	}
	
//--------------------------------------------------------------------------------------------------------
		public void botonCrearGrafo() {
	    JButton botonCrearGrafo = new JButton("CREAR GRAFO");
	    botonCrearGrafo.setBackground(Color.LIGHT_GRAY);
	    botonCrearGrafo.setForeground(Color.BLUE);
	    botonCrearGrafo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            verificarLista(); 
	    }});
	    botonCrearGrafo.setBounds(485, 358, 134, 23);
	    frame.getContentPane().add(botonCrearGrafo);
	    
	    JLabel fondoRelaciones = new JLabel("");
	    fondoRelaciones.setBounds(0, 0, 734, 461);
	    fondoRelaciones.setIcon(new ImageIcon(Relaciones.class.getResource("/Imagenes/afondoRelaciones.jpg")));
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
		aristaList.add(arista);
		sliderProbabilidadIntercep.setValue(0);
		ingresarEspiaUno.setText("");
		ingresarEspiaDos.setText("");			
	}
			
//--------------------------------------------------------------------------------------------------------

	public void verificarLista() {
		if( aristaList.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "ERROR GRAFO VACIO, CREAR RELACION", "Lista vacia", JOptionPane.WARNING_MESSAGE);
		}else {
	    	frame.setVisible(false);
        	GrafoOriginal.main(null);
        	}
		}
	
	
//--------------------------------------------------------------------------------------------------------	
		public static void agregarArista(Aristas arista) {
	    if (aristaList == null) {
	        aristaList = new ArrayList<>();
	    }
	    aristaList.add(arista);
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
			}
		});
	}

//--------------------------------------------------------------------------------------------------------	

	public Relaciones() {
	    initialize();
	}
//--------------------------------------------------------------------------------------------------------	

	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		aristaList = new ArrayList<>();
		ingresoPrimerEspia();
		ingresoSegundoEspia();
		ingresoProbabilidadIntercepcion();
		labelPrimerEspia();
		labelSegundoEspia();
		labelPeso();
		labelDefinirRelacion(); 
		botonCrearRelacion();
		botonCrearGrafo ();
	}
}
