package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.ChequeoDAO;
import DAO.ExplicacionDAO;
import DAO.InvestigacionDAO;
import TPFinal.modelo.Chequeo;
import TPFinal.modelo.Explicacion;
import TPFinal.modelo.Investigacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Explicaciones extends JPanel {
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JFrame marco;
	private JTextField textField;
	private JTextField txtfldbuscar;
	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
	public Explicaciones() {
	
		setLayout(null);
		
		txtfldbuscar = new JTextField();
		txtfldbuscar.setBounds(156, 26, 534, 20);
		add(txtfldbuscar);
		txtfldbuscar.setColumns(10);
		
		JButton btnAlta = new JButton("Ver potenciales explicaciones");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PotencialesExplicaciones potexp = new PotencialesExplicaciones();
				potexp.setMarco(marco);
				marco.setContentPane(potexp);
				marco.validate();
			}
		});
		btnAlta.setBounds(156, 450, 245, 38);
		add(btnAlta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				String titulo = (String) table.getValueAt(fila, 0);
				ExplicacionDAO exDAO = new ExplicacionDAO();
				Explicacion expaeditar = exDAO.obtenerConTitulo(titulo);
				AltaExplicacion altaex= new AltaExplicacion(expaeditar, true);
				altaex.setMarco(marco);
				marco.setContentPane(altaex);
				marco.setTitle("Modificar una Investigación");
				marco.validate();
			}
		});
		btnModificar.setBounds(10, 187, 136, 38);
		add(btnModificar);
		
		
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExplicacionDAO exdao= new ExplicacionDAO();
				int fila = table.getSelectedRow();
				String titulo = (String) table.getValueAt(fila, 0);
				Explicacion ex=exdao.obtenerConTitulo(titulo);

				exdao.eliminarConId(ex.getId());

				modeloTabla.setRowCount(0);
				llenarTablaExplicacionesConTodos();
			}
		});
		btnEliminar.setBounds(10, 263, 136, 38);
		add(btnEliminar);
		
		JButton btnVolver_1 = new JButton("Volver");
		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setMarco(marco);
				marco.setContentPane(menu);
				marco.validate();
			}
		});
		btnVolver_1.setBounds(543, 450, 147, 38);
		add(btnVolver_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 57, 534, 382);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla = new DefaultTableModel(new Object[] {"Nombre","Epigrafe","Contenido", "Fecha"}, 0);
		
		table.setModel(modeloTabla);
		
		llenarTablaExplicacionesConTodos();
		
		}
	public void llenarTablaExplicacionesConTodos() {
		ExplicacionDAO exdao = new ExplicacionDAO();
		for (Explicacion ex : exdao.obtenerTodos()) {
			modeloTabla.addRow(new Object[] {ex.getTitulo(), ex.getEpigrafe(), ex.getContenido(), ex.getFecha()});
		}
}
}