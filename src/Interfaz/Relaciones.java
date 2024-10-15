package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class Relaciones {
	
	private JFrame frame;
	private JTextField ingresarEspiaUno;
	private JTextField ingresarEspiaDos;
	private JTextField ingresarProbabilidadIntercep;
	private String primerEspia;
	private String segundoEspia;
	private String peso;
	private static ArrayList<Aristas> aristaList;
	
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
		ingresarProbabilidadIntercep = new JTextField();
		ingresarProbabilidadIntercep.setBackground(UIManager.getColor("Button.background"));
		ingresarProbabilidadIntercep.setBounds(327, 269, 292, 20);
		frame.getContentPane().add(ingresarProbabilidadIntercep);
		ingresarProbabilidadIntercep.setColumns(10);	
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
	    fondoRelaciones.setIcon(new ImageIcon(Relaciones.class.getResource("/imagenes/fondoRelaciones.jpg")));
	    fondoRelaciones.setBounds(0, 0, 734, 461);
	    frame.getContentPane().add(fondoRelaciones);
	}
		
//--------------------------------------------------------------------------------------------------------		
	
	private void guardarDatos() {
		primerEspia = ingresarEspiaUno.getText();
		segundoEspia = ingresarEspiaDos.getText(); 
		peso = ingresarProbabilidadIntercep.getText();
			
		if (primerEspia.isEmpty() || segundoEspia.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Ingresar el nombre del espia", "ERROR EN INGRESO ESPIA", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (peso.isEmpty() ) {
			JOptionPane.showMessageDialog(frame, "Ingresar peso", "ERROR EN PESO", JOptionPane.WARNING_MESSAGE);
			return;
		} 
		
		try {
			Double pesoFloat = Double.parseDouble(peso);
            if (pesoFloat > 1 || pesoFloat < 0) {
                JOptionPane.showMessageDialog(frame, "El peso debe estar entre 0 y 1", "ERROR EN PESO", JOptionPane.WARNING_MESSAGE);
                return;
            }
				Aristas arista = new Aristas(primerEspia, segundoEspia, pesoFloat);
				aristaList.add(arista);

				ingresarEspiaUno.setText("");
				ingresarEspiaDos.setText("");
				ingresarProbabilidadIntercep.setText("");
				
		} catch (NumberFormatException errorFloatPeso) {
            JOptionPane.showMessageDialog(frame, "Ingresar una probabilidad de intercepcion en numeros del 0 al 1", "ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);

		}
	}
			
//--------------------------------------------------------------------------------------------------------

	public void verificarLista() {
		if( aristaList.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Lista vacia", "ERROR GRAFO VACIO, CREAR RELACION", JOptionPane.WARNING_MESSAGE);
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