package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.CategoriaDAO;
import DAO.ChequeoDAO;
import DAO.ExplicacionDAO;
import TPFinal.modelo.Categoria;
import TPFinal.modelo.Chequeo;
import TPFinal.modelo.Explicacion;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class AltaExplicacion extends JPanel {
	private JTextField textField_titulo;
	private JTextField textField_epigrafe;
	private JTextField textField_contenido;
	private JFrame marco;
	
	public JFrame getMarco() {
		return marco;
	}

	public void setMarco(JFrame marco) {
		this.marco = marco;
	}

	public AltaExplicacion(Explicacion exp,boolean modif) {
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(10, 14, 26, 14);
		add(lblTitulo);
		
		textField_titulo = new JTextField();
		textField_titulo.setBounds(70, 11, 370, 20);
		add(textField_titulo);
		textField_titulo.setColumns(10);
		
		JLabel lblEpigrafe = new JLabel("Epigrafe");
		lblEpigrafe.setBounds(10, 39, 40, 14);
		add(lblEpigrafe);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(10, 139, 49, 14);
		add(lblContenido);
		
		textField_epigrafe = new JTextField();
		textField_epigrafe.setBounds(70, 42, 370, 86);
		add(textField_epigrafe);
		textField_epigrafe.setColumns(10);
		
		textField_contenido = new JTextField();
		textField_contenido.setBounds(70, 139, 370, 116);
		add(textField_contenido);
		textField_contenido.setColumns(10);
		
		JButton btnSiguiente = new JButton("Guardar");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!modif) {

					exp.setContenido(textField_contenido.getText());
					exp.setEpigrafe(textField_epigrafe.getText());
					exp.setTitulo(textField_titulo.getText());
					exp.setFecha(LocalDate.now());
					exp.setEnlace(exp.getEnlace());
					
					ExplicacionDAO exDAO = new ExplicacionDAO();
					exDAO.insert(exp);
					
					Explicacion expp = exDAO.obtenerConTitulo(textField_titulo.getText());
					System.out.println(expp.getId());
					
					for(Chequeo c:exp.getChequeos()) {
						exDAO.guardarExCheq(expp, c);
					}
					
					Explicaciones explicacion = new Explicaciones();
					explicacion.setMarco(marco);
					marco.setContentPane(explicacion);
					marco.validate();
				}
				else {
					exp.setTitulo(textField_titulo.getText());
					exp.setContenido(textField_contenido.getText());
					exp.setEpigrafe(textField_epigrafe.getText());
					
					ExplicacionDAO exdao = new ExplicacionDAO();
					exdao.updateConId(exp, exp.getId());
					
					Explicaciones explicacion = new Explicaciones();
					explicacion.setMarco(marco);
					marco.setContentPane(explicacion);
					marco.validate();	
				}
			}
		});
		btnSiguiente.setBounds(351, 266, 89, 23);
		add(btnSiguiente);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Explicaciones explicacion = new Explicaciones();
				explicacion.setMarco(marco);
				marco.setContentPane(explicacion);
				marco.validate();
			}
		});
		btnVolver.setBounds(70, 266, 89, 23);
		add(btnVolver);
		
		if (modif) {
			textField_titulo.setText(exp.getTitulo());
			textField_epigrafe.setText(exp.getEpigrafe());
			textField_contenido.setText(exp.getContenido());
		}
	}
}