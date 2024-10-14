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
		ingresarEspiaUno.setBounds(327, 124, 292, 20);
		frame.getContentPane().add(ingresarEspiaUno);
		ingresarEspiaUno.setColumns(10);
	}
//--------------------------------------------------------------------------------------------------------
	public void ingresoSegundoEspia() {
		ingresarEspiaDos = new JTextField();
		ingresarEspiaDos.setBounds(327, 191, 292, 20);
		frame.getContentPane().add(ingresarEspiaDos);
		ingresarEspiaDos.setColumns(10);
	}
//--------------------------------------------------------------------------------------------------------
	public void ingresoProbabilidadIntercepcion() {
		ingresarProbabilidadIntercep = new JTextField();
		ingresarProbabilidadIntercep.setBounds(327, 269, 292, 20);
		frame.getContentPane().add(ingresarProbabilidadIntercep);
		ingresarProbabilidadIntercep.setColumns(10);	
	}
//--------------------------------------------------------------------------------------------------------	
	public void labelPrimerEspia() {
		JLabel labelEspiaUno = new JLabel("NOMBRE DE ESPIA:");
		labelEspiaUno.setBounds(100, 123, 150, 28);
		frame.getContentPane().add(labelEspiaUno);
	}
//--------------------------------------------------------------------------------------------------------	
	public void labelSegundoEspia() {
		JLabel labelEspiaDos = new JLabel("NOMBRE DE ESPIA:");
		labelEspiaDos.setBounds(100, 197, 150, 14);
		frame.getContentPane().add(labelEspiaDos);
	}
//--------------------------------------------------------------------------------------------------------	
	public void labelPeso() {
		JLabel label_Peso = new JLabel("PROBABILIDAD DE INTERCEPCIÓN:");
		label_Peso.setBounds(100, 275, 300, 14);
		frame.getContentPane().add(label_Peso);
	}
//--------------------------------------------------------------------------------------------------------	
		public void labelDefinirRelacion() {
		JLabel labelRelacion = new JLabel("DEFINIR RELACION");
		labelRelacion.setFont(new Font("Arial Black", Font.PLAIN, 25));
		labelRelacion.setHorizontalAlignment(SwingConstants.CENTER);
		labelRelacion.setBounds(153, 11, 395, 67);
		frame.getContentPane().add(labelRelacion);
	}
//--------------------------------------------------------------------------------------------------------
	
	public void botonCrearRelacion() {
		JButton botonRelacion = new JButton("CREAR RELACION");

		botonRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});

		botonRelacion.setBounds(100, 358, 134, 23);
		frame.getContentPane().add(botonRelacion);
	}
	
//--------------------------------------------------------------------------------------------------------
		public void botonCrearGrafo() {
	    JButton botonCrearGrafo = new JButton("CREAR GRAFO");
	    botonCrearGrafo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	             frame.setVisible(false);
	 			 GrafoInterfaz.main(null);	        }
	    });
	    botonCrearGrafo.setBounds(485, 358, 134, 23);
	    frame.getContentPane().add(botonCrearGrafo);
	}
		
//--------------------------------------------------------------------------------------------------------		
	public static List<Aristas> getListAristas(){
		return aristaList;
	}
//--------------------------------------------------------------------------------------------------------	
		public static void agregarArista(Aristas arista) {
	    if (aristaList == null) {
	        aristaList = new ArrayList<>();
	    }
	    aristaList.add(arista);
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