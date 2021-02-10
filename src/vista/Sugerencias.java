package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.InvestigacionDAO;
import DAO.SugerenciaDAO;
import TPFinal.modelo.Chequeo;
import TPFinal.modelo.Sugerencia;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sugerencias extends JPanel {

	private DefaultTableModel modeloTabla;
	private JFrame marco;
	private JTable table;
	private JTextField txtfieldbuscar;

	public Sugerencias() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(155, 36, 535, 388);
		add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla = new DefaultTableModel(new Object[] {"Frase", "Autor Frase", "Medio Origen", "Enlace", "Fecha"}, 0);
		
		table.setModel(modeloTabla);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SugerenciaDAO sDAO = new SugerenciaDAO();
				int fila= table.getSelectedRow();
				String frase = (String) table.getValueAt(fila, 0);
				String autor = (String) table.getValueAt(fila, 1);
				Sugerencia sug = sDAO.obtenerConFraseAutor(frase, autor);
				Chequeo chh= new Chequeo();
				chh.cargardatos(sug);
				
				
				ChequeosAlta chalta = new ChequeosAlta(false, chh);
				chalta.setMarco(marco);
				marco.setContentPane(chalta);
				marco.validate();
			}
		});
		btnCrear.setBounds(10, 149, 135, 54);
		add(btnCrear);
		
		JButton btneliminar = new JButton("Descartar");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SugerenciaDAO sDAO = new SugerenciaDAO();
				int fila= table.getSelectedRow();
				String frase = (String) table.getValueAt(fila, 0);
				String autor = (String) table.getValueAt(fila, 1);
				sDAO.eliminarconFraseAutor(frase, autor);
				
				modeloTabla.setRowCount(0);
				llenartabla();
			}
		});
		btneliminar.setBounds(10, 214, 135, 54);
		add(btneliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chequeos chequeo = new Chequeos();
				chequeo.setMarco(marco);
				marco.setContentPane(chequeo);
				marco.validate();
			}
		});
		btnVolver.setBounds(522, 435, 168, 54);
		add(btnVolver);
		
		txtfieldbuscar = new JTextField();
		txtfieldbuscar.setBounds(155, 5, 535, 20);
		add(txtfieldbuscar);
		txtfieldbuscar.setColumns(10);
		
		llenartabla();
		
		
	}
	
	public void llenartabla() {
		
		SugerenciaDAO sdao = new SugerenciaDAO();
		
		for (Sugerencia s : sdao.obtenerTodos()) {
			modeloTabla.addRow(new Object[] {s.getFrase(), s.getAutor_frase(), s.getMedio_frase(), s.getEnlace(), s.getFrase()});
			
		}
	}
	
	
	
	
	
	
	public void setMarco(JFrame marco) {
		this.marco=marco;
	}
	
}
