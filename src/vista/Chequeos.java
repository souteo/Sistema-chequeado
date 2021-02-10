package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.CategoriaDAO;
import DAO.ChequeoDAO;
import TPFinal.modelo.Categoria;
import TPFinal.modelo.Chequeo;
import TPFinal.modelo.Investigacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Chequeos extends JPanel {

	private JTable table;
	private DefaultTableModel modeloTabla;
	private JFrame marco;
	private JTextField txtBuscar;
	
	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
	
	private String palabraclave_busqueda="";
	
	public Chequeos() {
		setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				modeloTabla.setRowCount(0);
				ChequeoDAO cDAO = new ChequeoDAO();
				
				
				if(e.getKeyChar()=='\b') {
					modeloTabla.setRowCount(0);
					
					if(palabraclave_busqueda.length()!=0) 
						palabraclave_busqueda = palabraclave_busqueda.substring(0, palabraclave_busqueda.length() - 1);
					for (Chequeo ch : cDAO.obtenerPalabraclave(palabraclave_busqueda)) {
						modeloTabla.addRow(new Object[] {resolverCategoria(ch) ,ch.getPalabra_clave(),ch.getFrase(),ch.getMedio_origen(),ch.getEnlace(),ch.getFecha(),ch.isVerificacion()});
					}
				}
				else {
					modeloTabla.setRowCount(0);
					palabraclave_busqueda = palabraclave_busqueda + Character.toString(e.getKeyChar());
					for (Chequeo ch : cDAO.obtenerPalabraclave(palabraclave_busqueda)) {
						modeloTabla.addRow(new Object[] {resolverCategoria(ch) ,ch.getPalabra_clave(),ch.getFrase(),ch.getMedio_origen(),ch.getEnlace(),ch.getFecha(),ch.isVerificacion()});
					}
				}
			}
		});
		txtBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtBuscar.setText("");
			}
		});
		txtBuscar.setText("Buscar por palabra clave");
		txtBuscar.setToolTipText("");

		txtBuscar.setBounds(128, 26, 563, 20);
		add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnAlta = new JButton("Ver sugerencias");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sugerencias sug = new Sugerencias();
				sug.setMarco(marco);
				marco.setContentPane(sug);
				marco.validate();
			}
		});
		btnAlta.setBounds(128, 450, 245, 38);
		add(btnAlta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				String palabraClave = (String) table.getValueAt(fila, 1);
				String frase = (String) table.getValueAt(fila, 2);
				
				ChequeoDAO chdao = new ChequeoDAO();
				Chequeo ch = chdao.obtenerConPalabraClaveFrase(palabraClave, frase);
				
				ChequeosAlta chalta = new ChequeosAlta(true,ch);
				chalta.setMarco(marco);
				marco.setContentPane(chalta);
				marco.validate();
			}
		});
		btnModificar.setBounds(10, 57, 108, 38);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String palabraclave = (String) table.getValueAt(fila, 1);
				String frase = (String) table.getValueAt(fila, 2);
				
				ChequeoDAO cdao = new ChequeoDAO();
				cdao.eliminarConPalabraClaveFrase(palabraclave, frase);
				
				modeloTabla.setRowCount(0);
				llenarTablaConTodos();
			}
		});
		btnEliminar.setBounds(10, 106, 108, 38);
		add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 57, 563, 369);
		add(scrollPane);
		
		
		JList list = new JList();
		
		scrollPane.setViewportView(list);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla = new DefaultTableModel(new Object[] {"Categoría","Palabra clave","Frase","Medio de origen","Enlace","Fecha","Verificación"}, 0);
		table.setModel(modeloTabla);
		llenarTablaConTodos();
		
		JButton btnVolver_1 = new JButton("Volver");
		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setMarco(marco);
				marco.setContentPane(menu);
				marco.validate();
			}
		});
		btnVolver_1.setBounds(544, 450, 147, 38);
		add(btnVolver_1);
	}
	
	public void llenarTablaConTodos() {
		ChequeoDAO chdao = new ChequeoDAO();
		for (Chequeo ch : chdao.obtenerTodos()) {
			modeloTabla.addRow(new Object[] {resolverCategoria(ch) ,ch.getPalabra_clave(),ch.getFrase(),ch.getMedio_origen(),ch.getEnlace(),ch.getFecha(),ch.isVerificacion()});
		}
	}
	
	public String resolverCategoria(Chequeo ch) {
		CategoriaDAO catdao = new CategoriaDAO();
		Categoria cat = catdao.obtenerConId(ch.getCategoria());
		return cat.getNombre();
	}
}
