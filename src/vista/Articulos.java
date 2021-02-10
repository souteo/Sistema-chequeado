package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.ChequeoDAO;
import DAO.ExplicacionDAO;
import DAO.InvestigacionDAO;
import TPFinal.modelo.Articulo;
import TPFinal.modelo.Explicacion;
import TPFinal.modelo.Investigacion;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Font;

public class Articulos extends JPanel {

	private JTable table;
	private DefaultTableModel modeloTabla;
	private JFrame marco;
	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
	public Articulos() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 680, 403);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla = new DefaultTableModel(new Object[] {"Nombre/Titulo","Fecha"}, 0);
		
		table.setModel(modeloTabla);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setMarco(marco);
				marco.setContentPane(menu);
				marco.validate();
			}
		});
		btnVolver.setBounds(543, 451, 147, 38);
		add(btnVolver);
		
		llenarTabla();
		
	}
	
	public void llenarTabla() {
		
		for (Articulo art : calcular_lista_auxiliar()) {
			System.out.println(art.getTitulo());
			System.out.println(art.esDeAltoImpacto());
			if(art.esDeAltoImpacto())
				modeloTabla.addRow(new Object[] {art.getTitulo(), art.getFecha()});
		}
	}
	
	
	public ArrayList<Articulo> calcular_lista_auxiliar(){
		ArrayList<Articulo> listaaux = new ArrayList<>();
		ExplicacionDAO exDAO = new ExplicacionDAO();
		for (Explicacion ex : exDAO.obtenerTodos()) {
			ChequeoDAO c = new ChequeoDAO();
			ex.setChequeos(c.filtrarChequeoXExplicacion(ex));
			System.out.println(ex.getChequeos().size());
			listaaux.add(ex);
		}
		
		InvestigacionDAO inDAO = new InvestigacionDAO();
		for (Investigacion inv : inDAO.obtenerTodos()) {
			listaaux.add(inv);
		}
		
		return listaaux;
	}
	
}
