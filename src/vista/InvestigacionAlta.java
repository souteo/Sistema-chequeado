package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import DAO.CategoriaDAO;
import DAO.InvestigacionDAO;
import TPFinal.modelo.Categoria;
import TPFinal.modelo.Investigacion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class InvestigacionAlta extends JPanel {
	private JTextField textField_tema;
	private JTextField textField_palabraclave;
	private JTextField textField_titulo;
	private JTextField textField_epigrafe;
	private JTextField textField_contenido;

	

	

	private JFrame marco;

	public InvestigacionAlta(Investigacion inv, boolean modif) {
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Categoria");
		lblNewLabel.setBounds(35, 21, 88, 14);
		add(lblNewLabel);
		
		JComboBox comboBox_categoria = new JComboBox();
		comboBox_categoria.setBounds(151, 18, 483, 20);
		add(comboBox_categoria);
		
		CategoriaDAO catdao = new CategoriaDAO();
		
		for (Categoria cat : catdao.obtenerTodos()) {
			comboBox_categoria.addItem(cat.getNombre());
		}
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setBounds(35, 52, 69, 14);
		add(lblTema);
		
		textField_tema = new JTextField();
		textField_tema.setBounds(151, 49, 483, 20);
		add(textField_tema);
		textField_tema.setColumns(10);
		
		JLabel lblPalabraClave = new JLabel("Palabra Clave");
		lblPalabraClave.setBounds(35, 83, 102, 14);
		add(lblPalabraClave);
		
		textField_palabraclave = new JTextField();
		textField_palabraclave.setBounds(151, 80, 483, 20);
		add(textField_palabraclave);
		textField_palabraclave.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(35, 114, 69, 14);
		add(lblTitulo);
		
		textField_titulo = new JTextField();
		textField_titulo.setBounds(151, 111, 483, 20);
		add(textField_titulo);
		textField_titulo.setColumns(10);
		
		JLabel lblEpigrafe = new JLabel("Epigrafe");
		lblEpigrafe.setBounds(35, 155, 65, 14);
		add(lblEpigrafe);
		
		textField_epigrafe = new JTextField();
		textField_epigrafe.setBounds(151, 142, 483, 40);
		add(textField_epigrafe);
		textField_epigrafe.setColumns(10);
		
		textField_contenido = new JTextField();
		textField_contenido.setBounds(151, 193, 483, 227);
		add(textField_contenido);
		textField_contenido.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				if(modif) {
					InvestigacionDAO invdao = new InvestigacionDAO();
					inv.setTema_investigacion(textField_tema.getText());
					inv.setPalabra_clave(textField_palabraclave.getText());
					inv.setTitulo(textField_titulo.getText());
					inv.setEpigrafe(textField_epigrafe.getText());
					inv.setContenido(textField_contenido.getText());

					
					CategoriaDAO catdao = new CategoriaDAO();
					String nombre = (String) comboBox_categoria.getSelectedItem();
					Categoria cat = catdao.obtenerConNombre(nombre);
					
					inv.setCategoria(cat.getId());
					
					invdao.updateConId(inv, inv.getId());
					
				}
				
				else{
					
					
					Investigacion nuevainv = new Investigacion();
					
					nuevainv.setTema_investigacion(textField_tema.getText());
					nuevainv.setPalabra_clave(textField_palabraclave.getText());
					nuevainv.setTitulo(textField_titulo.getText());
					nuevainv.setEpigrafe(textField_epigrafe.getText());
					nuevainv.setContenido(textField_contenido.getText());
					nuevainv.setFecha(LocalDate.now());
					
					CategoriaDAO catdao = new CategoriaDAO();
					String nombre = (String) comboBox_categoria.getSelectedItem();
					Categoria cat = catdao.obtenerConNombre(nombre);
					
					nuevainv.setCategoria(cat.getId());
					
					
					InvestigacionDAO invdao = new InvestigacionDAO();
					invdao.guardarnuevo(nuevainv);
					
					Investigaciones investigaciones = new Investigaciones();
					investigaciones.setMarco(marco);
					marco.setContentPane(investigaciones);
					marco.validate();

				}
				
				Investigaciones investigaciones = new Investigaciones();
				investigaciones.setMarco(marco);
				marco.setContentPane(investigaciones);
				marco.setTitle("Investigaciones");
				marco.validate();
				
			}
		});
		btnGuardar.setBounds(487, 451, 147, 38);
		add(btnGuardar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Investigaciones investi = new Investigaciones();
				investi.setMarco(marco);
				marco.setContentPane(investi);
				marco.setTitle("Investigaciones");
				marco.validate();
			}
		});
		btnVolver.setBounds(151, 451, 147, 38);
		add(btnVolver);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(35, 299, 102, 14);
		add(lblContenido);
		
		if(modif) {
			textField_contenido.setText(inv.getContenido());
			textField_epigrafe.setText(inv.getEpigrafe());
			textField_palabraclave.setText(inv.getPalabra_clave());
			textField_tema.setText(inv.getTema_investigacion());
			textField_titulo.setText(inv.getTitulo());
			CategoriaDAO cDAO = new CategoriaDAO();
			Categoria cat = cDAO.obtenerConId(inv.getCategoria());
			comboBox_categoria.setSelectedItem(cat.getNombre());
		}
		
		
	}


	public JFrame getMarco() {
		return marco;
	}

	public void setMarco(JFrame marco) {
		this.marco = marco;
	}



	
}
