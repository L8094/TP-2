package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Logica.Aristas;

import java.awt.Font;

public class Relaciones {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String primerEspia;
	private String segundoEspia;
	private String peso;
	private ArrayList<Aristas> aristaList;
	

	public void ingresoPrimerEspia() {
	textField = new JTextField();
	textField.setBounds(395, 128, 86, 20);
	frame.getContentPane().add(textField);
	textField.setColumns(10);
	primerEspia = textField.getText();
	}
	
	public void ingresoSegundoEspia() {
	textField_1 = new JTextField();
	textField_1.setBounds(395, 195, 86, 20);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);
	segundoEspia = textField.getText();
	}
	
	public void ingresoPeso() {
	textField_2 = new JTextField();
	textField_2.setBounds(395, 273, 86, 20);
	frame.getContentPane().add(textField_2);
	textField_2.setColumns(10);
	peso = textField.getText();
	}
	
	public void labelPrimerEspia() {
	JLabel lblNewLabel = new JLabel("ESPIA");
	lblNewLabel.setBounds(251, 124, 40, 28);
	frame.getContentPane().add(lblNewLabel);
	}
	
	public void labelSegundoEspia() {
	JLabel lblNewLabel_1 = new JLabel("ESPIA");
	lblNewLabel_1.setBounds(251, 198, 46, 14);
	frame.getContentPane().add(lblNewLabel_1);
	}
	
	public void labelPeso() {
	JLabel lblNewLabel_2 = new JLabel("PESO");
	lblNewLabel_2.setBounds(251, 276, 46, 14);
	frame.getContentPane().add(lblNewLabel_2);
	}
	
	public void labelDefinirRelacion() {
	JLabel lblNewLabel_3 = new JLabel("DEFINIR RELACION");
	lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 20));
	lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_3.setBounds(236, 45, 245, 28);
	frame.getContentPane().add(lblNewLabel_3);
	}
	
	public void botonCrearRelacion() {
		JButton btnNewButton = new JButton("CREAR RELACION");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Capturar los valores de los JTextField al hacer clic en el botón
				primerEspia = textField.getText();
				segundoEspia = textField_1.getText(); // corregido para usar el campo correcto
				peso = textField_2.getText();         // corregido para usar el campo correcto

				// Crear la relación de Aristas
				Aristas arista = new Aristas(Integer.parseInt(primerEspia), Integer.parseInt(segundoEspia), Double.parseDouble(peso));
				aristaList.add(arista);

				// Limpiar los campos de texto
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});

		btnNewButton.setBounds(139, 358, 134, 23);
		frame.getContentPane().add(btnNewButton);
	}


	public void botonCrearGrafo () {
	JButton btnNewButton_1 = new JButton("CREAR GRAFO");
	btnNewButton_1.setBounds(485, 358, 134, 23);
	frame.getContentPane().add(btnNewButton_1);
	
	}
	
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

	
	public Relaciones() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		aristaList = new ArrayList<>();
		ingresoPrimerEspia();
		ingresoSegundoEspia();
		ingresoPeso();
		labelPrimerEspia();
		labelSegundoEspia();
		labelPeso();
		labelDefinirRelacion(); 
		botonCrearRelacion();
		botonCrearGrafo ();
	}
}
