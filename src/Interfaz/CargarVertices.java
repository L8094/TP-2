package Interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import javax.swing.JButton;


public class CargarVertices {

	private JFrame frame;
	private JPanel panelMapa;
	private JPanel panelControles;
	private JMapViewer _mapa;
	private ArrayList<Coordinate> _lasCoordenadas;

	
	public void botonDefinirRelaciones() {
	JButton btnNewButton = new JButton("Definir relaciones");
	btnNewButton.setBounds(511, 141, 150, 23);
	panelControles.add(btnNewButton);
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			Relaciones.main(null);
		}
	});
	}

//--------------------------------------------------------------------------------------------------------	
	
	public void botonEliminarVertice() {
	JButton btnNewButton_1 = new JButton("Eliminar vertice");
	btnNewButton_1.setBounds(511, 286, 150, 23);
	panelControles.add(btnNewButton_1);
	}
	
//--------------------------------------------------------------------------------------------------------	
		
	private void detectarCoordenadas()  
	{
		_lasCoordenadas = new ArrayList<Coordinate>();
				
		_mapa.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				Coordinate markeradd = (Coordinate)
				_mapa.getPosition(e.getPoint());
				_lasCoordenadas.add(markeradd);
				String nombre = JOptionPane.showInputDialog("Numero de espia: ");
				_mapa.addMapMarker(new MapMarkerDot(nombre, markeradd));
			}}
		});
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
		_mapa.setDisplayPosition(new Coordinate(-15,-140), 15);
		_mapa.setZoomContolsVisible(false);
		
				
		panelMapa.add(_mapa);
		detectarCoordenadas();
		botonDefinirRelaciones();
		botonEliminarVertice();
		
	}
}