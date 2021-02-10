package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Menu extends JPanel {

	private JFrame marco;
	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
	public Menu() {
		setLayout(null);

		
		
		JButton botonchequeos = new JButton("Chequeos");
		botonchequeos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chequeos chequeo = new Chequeos();
				chequeo.setMarco(marco);
				marco.setContentPane(chequeo);
				marco.setTitle("Chequeos");
				marco.validate();
			}
		});
		botonchequeos.setBounds(224, 98, 211, 37);
		add(botonchequeos);
		
		JButton botonexplicaciones = new JButton("Explicaciones");
		botonexplicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Explicaciones explicacion = new Explicaciones();
				explicacion.setMarco(marco);
				marco.setContentPane(explicacion);
				marco.setTitle("Explicaciones");
				marco.validate();
				
			}
		});
		botonexplicaciones.setBounds(224, 146, 211, 37);
		add(botonexplicaciones);
		
		JButton botoninvestigaciones = new JButton("Investigaciones");
		botoninvestigaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Investigaciones investi = new Investigaciones();
				investi.setMarco(marco);
				marco.setContentPane(investi);
				marco.setTitle("Investigaciones");
				marco.validate();
			}
		});
		botoninvestigaciones.setBounds(224, 194, 211, 37);
		add(botoninvestigaciones);
		
		JButton botonarticulos = new JButton("Art\u00EDculos de alto impacto");
		botonarticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Articulos art = new Articulos();
				art.setMarco(marco);
				marco.setContentPane(art);
				marco.setTitle("Artículos de alto impacto");
				marco.validate();
			}
		});
		botonarticulos.setBounds(224, 242, 211, 37);
		add(botonarticulos);

	}
}
