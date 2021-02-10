package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import DAO.CategoriaDAO;
import DAO.ChequeoDAO;
import DAO.SugerenciaDAO;
import TPFinal.modelo.Categoria;
import TPFinal.modelo.Chequeo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ChequeosAlta extends JPanel {
	private JTextField textField_palabraclave;
	private JTextField textField_frase;
	private JTextField textField_medioorigen;
	private JTextField textField_enlace;
	private JFrame marco;
	private LocalDate fecha;

 
	public ChequeosAlta(boolean modif, Chequeo chequeo) {
		setLayout(null);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(30, 37, 194, 14);
		add(lblCategoria);
		
		JComboBox comboBox_categoria = new JComboBox();
		comboBox_categoria.setBounds(354, 29, 194, 30);
		add(comboBox_categoria);	
		CategoriaDAO catdao = new CategoriaDAO();
		for (Categoria cat : catdao.obtenerTodos()) {
			comboBox_categoria.addItem(cat.getNombre());
		}
		
		
		JLabel lblPalabrasClave = new JLabel("Palabra Clave");
		lblPalabrasClave.setBounds(30, 79, 194, 14);
		add(lblPalabrasClave);
		
		textField_palabraclave = new JTextField();
		textField_palabraclave.setBounds(354, 70, 194, 32);
		add(textField_palabraclave);
		textField_palabraclave.setColumns(10);
		
		JLabel lblFrase = new JLabel("Frase");
		lblFrase.setBounds(30, 121, 194, 14);
		add(lblFrase);
		
		textField_frase = new JTextField();
		textField_frase.setBounds(354, 113, 194, 30);
		add(textField_frase);
		textField_frase.setColumns(10);
		
		JLabel lblMedioDeOrigen = new JLabel("Medio de Origen");
		lblMedioDeOrigen.setBounds(30, 162, 194, 14);
		add(lblMedioDeOrigen);
		
		textField_medioorigen = new JTextField();
		textField_medioorigen.setBounds(354, 154, 194, 30);
		add(textField_medioorigen);
		textField_medioorigen.setColumns(10);
		
		JLabel lblEnlace = new JLabel("Enlace");
		lblEnlace.setBounds(30, 203, 194, 14);
		add(lblEnlace);
		
		textField_enlace = new JTextField();
		textField_enlace.setBounds(354, 195, 194, 30);
		add(textField_enlace);
		textField_enlace.setColumns(10);
		
		JLabel lblVerificacion = new JLabel("Verificacion");
		lblVerificacion.setBounds(30, 244, 194, 14);
		add(lblVerificacion);
		
		JComboBox comboBox_verificacion = new JComboBox();
		comboBox_verificacion.setBounds(354, 236, 194, 30);
		add(comboBox_verificacion);
		comboBox_verificacion.addItem("True");
		comboBox_verificacion.addItem("False");
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chequeos chequeo = new Chequeos();
				chequeo.setMarco(marco);
				marco.setContentPane(chequeo);
				marco.validate();
			}
		});
		btnVolver.setBounds(28, 375, 147, 38);
		add(btnVolver);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modif) {
					Chequeo chequeo_modificado = new Chequeo();
					
					CategoriaDAO catdao = new CategoriaDAO();
					String nombre = (String) comboBox_categoria.getSelectedItem();
					Categoria cat = catdao.obtenerConNombre(nombre);			
					chequeo_modificado.setCategoria(cat.getId());
					
					if(comboBox_verificacion.getSelectedItem().equals("True"))
						chequeo_modificado.setVerificacion(true);
					else
						chequeo_modificado.setVerificacion(false);

					chequeo_modificado.setPalabra_clave(textField_palabraclave.getText());
					
					ChequeoDAO cDAO = new ChequeoDAO();
					cDAO.updateConEnlaceFrase(chequeo_modificado, textField_enlace.getText(), textField_frase.getText());
					
					Chequeos chequeos = new Chequeos();
					chequeos.setMarco(marco);
					marco.setTitle("Chequeos");
					marco.setContentPane(chequeos);
					marco.validate();
				}
				else {
					Chequeo nuevo_chequeo = new Chequeo();
					
					CategoriaDAO catdao = new CategoriaDAO();
					String nombre = (String) comboBox_categoria.getSelectedItem();
					Categoria cat = catdao.obtenerConNombre(nombre);			
					nuevo_chequeo.setCategoria(cat.getId());
					
					nuevo_chequeo.setPalabra_clave(textField_palabraclave.getText());
					nuevo_chequeo.setFrase(textField_frase.getText());
					nuevo_chequeo.setMedio_origen(textField_medioorigen.getText());
					nuevo_chequeo.setEnlace(textField_enlace.getText());
					nuevo_chequeo.setFecha(fecha);
					if(comboBox_verificacion.getSelectedItem().equals("True"))
						nuevo_chequeo.setVerificacion(true);
					else
						nuevo_chequeo.setVerificacion(false);
					
					ChequeoDAO cDAO = new ChequeoDAO();
					cDAO.insert(nuevo_chequeo);
					SugerenciaDAO sDAO = new SugerenciaDAO();
					sDAO.eliminarconFraseFecha(nuevo_chequeo.getFrase(), fecha);
					
					Chequeos chequeos = new Chequeos();
					chequeos.setMarco(marco);
					marco.setTitle("Chequeos");
					marco.setContentPane(chequeos);
					marco.validate();
				}
			}
		});
		btnGuardar.setBounds(401, 375, 147, 38);
		add(btnGuardar);

		if(modif) {
			textField_palabraclave.setText(chequeo.getPalabra_clave());
			textField_frase.setText(chequeo.getFrase());
			textField_frase.setEditable(false);
			textField_medioorigen.setText(chequeo.getMedio_origen());
			textField_medioorigen.setEditable(false);
			textField_enlace.setText(chequeo.getEnlace());
			textField_enlace.setEditable(false);
			if(chequeo.isVerificacion())
				comboBox_verificacion.setSelectedItem("True");
			else
				comboBox_verificacion.setSelectedItem("False");
			CategoriaDAO cdao = new CategoriaDAO();
			Categoria cat = cdao.obtenerConId(chequeo.getCategoria());
			comboBox_categoria.setSelectedItem(cat.getNombre());
		}
		else {
			textField_medioorigen.setText(chequeo.getMedio_origen());
			textField_medioorigen.setEditable(false);
			textField_frase.setText(chequeo.getFrase());
			textField_frase.setEditable(false);
			textField_enlace.setText(chequeo.getEnlace());
			textField_enlace.setEditable(false);
			fecha = chequeo.getFecha();
		}
	}
	
	

	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
}
