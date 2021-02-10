
package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.CategoriaDAO;
import DAO.ChequeoDAO;
import TPFinal.modelo.Categoria;
import TPFinal.modelo.Chequeo;
import TPFinal.modelo.Explicacion;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.ItemEvent;

public class PotencialesExplicaciones extends JPanel {

	private JFrame marco;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JButton btnFiltrar;
	private JScrollPane scrollPane;
	private JComboBox comboBox_categorias;
	private JButton btnNewButton;

	public void setMarco(JFrame marco) {
		this.marco = marco;
	}

	public PotencialesExplicaciones() {
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 46, 520, 327);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		modeloTabla = new DefaultTableModel(new Object[] { "Enlace", "Cant. Chequeos" }, 0);

		table.setModel(modeloTabla);

		comboBox_categorias = new JComboBox();
		comboBox_categorias.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

			}
		});
		comboBox_categorias.setBounds(53, 15, 195, 20);
		add(comboBox_categorias);

		CategoriaDAO c = new CategoriaDAO();
		for (Categoria x : c.obtenerTodos()) {
			comboBox_categorias.addItem(x.getNombre());
		}

		btnNewButton = new JButton("Crear explicaci\u00F3n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Explicacion exp= new Explicacion();
				
				String enlace = (String) modeloTabla.getValueAt(table.getSelectedRow(), 0);
				HashMap <String, ArrayList<Chequeo>> mapa = crearHashMap((String)comboBox_categorias.getSelectedItem());
				exp.setChequeos(mapa.get(enlace));
				
				exp.setEnlace(enlace);
				exp.setCategoria(comboBox_categorias.getSelectedIndex());
				AltaExplicacion altaexp = new AltaExplicacion(exp,false);
				altaexp.setMarco(marco);
				marco.setContentPane(altaexp);
				marco.validate();
			}
		});
		btnNewButton.setBounds(53, 384, 354, 39);
		add(btnNewButton);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Explicaciones exp = new Explicaciones();
				exp.setMarco(marco);
				marco.setContentPane(exp);
				marco.validate();
			}
		});
		btnVolver.setBounds(521, 434, 168, 54);
		add(btnVolver);

		btnFiltrar = new JButton("filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modeloTabla.setRowCount(0);
				table.setModel(modeloTabla);
				llenarTabla();
				table.setModel(modeloTabla);
			}
		});
		btnFiltrar.setBounds(258, 12, 89, 23);
		add(btnFiltrar);

	}

	public ArrayList<Explicacion> llenarTabla() {
		ArrayList<Explicacion> potenciales = new ArrayList<>();
		HashMap<String, ArrayList<Chequeo>> hashMap = crearHashMap((String) comboBox_categorias.getSelectedItem());
		for (String e : hashMap.keySet()) {
			Explicacion p = new Explicacion();
			p.setCategoria(hashMap.get(e).get(0).getCategoria());
			p.setChequeos(hashMap.get(e));

			modeloTabla.addRow(new Object[] { 
					e, 
					p.getChequeos().size() 
				});
			potenciales.add(p);
		}
		return potenciales;
	}

	public HashMap<String, ArrayList<Chequeo>> crearHashMap(String catnombre) {

		CategoriaDAO catdao = new CategoriaDAO();
		Categoria categoria = catdao.obtenerConNombre(catnombre);

		ChequeoDAO chdao = new ChequeoDAO();
		ArrayList<Chequeo> listachequeos = chdao.select_categoria(categoria.getId());

		HashMap<String, ArrayList<Chequeo>> mapa = new HashMap<>();
		
		for (Chequeo chequeo : listachequeos) {
			if (!mapa.containsKey(chequeo.getEnlace())) {
				mapa.put(chequeo.getEnlace(), new ArrayList<Chequeo>());
				mapa.get(chequeo.getEnlace()).add(chequeo);
			} else {
				mapa.get(chequeo.getEnlace()).add(chequeo);
			}
		}
		return mapa;
	}
}
