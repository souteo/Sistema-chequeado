package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.CategoriaDAO;
import DAO.InvestigacionDAO;
import TPFinal.modelo.Categoria;
import TPFinal.modelo.Investigacion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Investigaciones extends JPanel {
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JFrame marco;
	private JTextField txtF_tema;
	private String tema="";
	
	
	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
	public Investigaciones() {
		setLayout(null);
		
		txtF_tema = new JTextField();
		txtF_tema.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				modeloTabla.setRowCount(0);
				InvestigacionDAO inDao = new InvestigacionDAO();
				
				if(e.getKeyChar() == '\b')
			    {  
					modeloTabla.setRowCount(0);
					if(tema.length()!=0)
						tema = tema.substring(0, tema.length() - 1);
			    	for (Investigacion inv : inDao.obtenerConTema(tema)) {		
			    		modeloTabla.addRow(new Object[] {inv.getTema_investigacion(), inv.getPalabra_clave(), inv.getTitulo(), inv.getEpigrafe(), inv.getContenido(),inv.getFecha(),resolverCategoria(inv)});
					}
			    }
			    else{
			    modeloTabla.setRowCount(0);
				tema = tema + Character.toString(e.getKeyChar());
				for (Investigacion inv : inDao.obtenerConTema(tema)) {		
					modeloTabla.addRow(new Object[] {inv.getTema_investigacion(), inv.getPalabra_clave(), inv.getTitulo(), inv.getEpigrafe(), inv.getContenido(), inv.getFecha(), resolverCategoria(inv)});
				}
			    }
			}
		});
		
		txtF_tema.setBounds(167, 26, 523, 20);
		add(txtF_tema);
		txtF_tema.setColumns(10);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvestigacionAlta investi = new InvestigacionAlta(null, false);
				investi.setMarco(marco);
				marco.setContentPane(investi);
				marco.validate();
			}
		});
		btnAlta.setBounds(10, 152, 147, 38);
		add(btnAlta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				String titulo = (String) table.getValueAt(fila, 2);
				String palabraclave = (String) table.getValueAt(fila, 1);
				InvestigacionDAO invdao = new InvestigacionDAO();
				Investigacion investigacion_a_editar = invdao.obtenerConTituloPalabraClave(titulo, palabraclave);
				boolean modif = true;
				
				InvestigacionAlta invalta = new InvestigacionAlta(investigacion_a_editar, true);
				invalta.setMarco(marco);
				marco.setContentPane(invalta);
				marco.setTitle("Modificar una Investigación");
				marco.validate();
			}
		});
		btnModificar.setBounds(10, 201, 147, 38);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvestigacionDAO invdao = new InvestigacionDAO();
				int fila = table.getSelectedRow();
				String titulo = (String) table.getValueAt(fila, 2);
				String palabraclave = (String) table.getValueAt(fila, 1);
				invdao.eliminarConTituloPalabraclave(titulo, palabraclave);
				
				
				modeloTabla.setRowCount(0);
				llenarTablaInvestigaciones();
			}
		});
		btnEliminar.setBounds(10, 250, 147, 38);
		add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 57, 523, 382);
		add(scrollPane);

		JButton btnVolver_1 = new JButton("Volver");
		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setMarco(marco);
				marco.setContentPane(menu);
				marco.setTitle("Menú");
				marco.validate();
			}
		});
		btnVolver_1.setBounds(543, 450, 147, 38);
		add(btnVolver_1);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla = new DefaultTableModel(new Object[] {"Tema", "Palabra Clave", "Titulo", "Epigrafe", "Contenido" ,"Fecha de creación", "Categoría"}, 0);
		
		table.setModel(modeloTabla);
		
		
		llenarTablaInvestigaciones();
		
	}
	
	public void llenarTablaInvestigaciones() {
		InvestigacionDAO idao = new InvestigacionDAO();
		for(Investigacion inv: idao.obtenerTodos()) {
			modeloTabla.addRow(new Object[] {inv.getTema_investigacion(), inv.getPalabra_clave(), inv.getTitulo(), inv.getEpigrafe(), inv.getContenido(), inv.getFecha(), resolverCategoria(inv)});
		}
	}
	
	public String resolverCategoria(Investigacion inv) {
		CategoriaDAO catdao = new CategoriaDAO();
		Categoria cat = catdao.obtenerConId(inv.getCategoria());
		return cat.getNombre();
	}
	
}
