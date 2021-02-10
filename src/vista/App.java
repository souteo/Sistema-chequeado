package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class App {

	public static void main(String[] args) {

        JMenu menu, menuayu;  
        JMenuItem itemini, itemche, itemexp, iteminv, itemart, itemsalir;  
		JFrame marco = new JFrame("Chekeado");
		
		marco.setBounds(0,0,800,600);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setVisible(true);


		
        JMenuBar mb=new JMenuBar();  
        menu=new JMenu("Menu");
        menuayu=new JMenu("Ayuda");
        itemini=new JMenuItem("Inicio");  
        itemche=new JMenuItem("Chequeos");  
        itemexp=new JMenuItem("Explicaciones");  
        iteminv=new JMenuItem("Investigaciones");  
        itemart=new JMenuItem("Articulos de alto impacto"); 
        itemsalir=new JMenuItem("Salir");
        
        itemini.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Menu inicio = new Menu();
		        inicio.setMarco(marco);
		        marco.setContentPane(inicio);
		        marco.setTitle("Menú");
				marco.validate();
			}
		});
        
        itemche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Chequeos chequeos = new Chequeos();
		        chequeos.setMarco(marco);
		        marco.setContentPane(chequeos);
		        marco.setTitle("Chequeos");
				marco.validate();
			}
		});
        
        itemexp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Explicaciones explicaciones = new Explicaciones();
		        explicaciones.setMarco(marco);
		        marco.setContentPane(explicaciones);
		        marco.setTitle("Explicaciones");
				marco.validate();
			}
		});
        
        iteminv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Investigaciones investigacion = new Investigaciones();
		        investigacion.setMarco(marco);
		        marco.setContentPane(investigacion);
		        marco.setTitle("Investigaciones");
				marco.validate();
			}
		});
        
        itemart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Articulos arts = new Articulos();
		        arts.setMarco(marco);
		        marco.setContentPane(arts);
		        marco.setTitle("Artículos de alto impacto");
				marco.validate();
			}
		});
        
        itemsalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit( 0 );
			}
		});
        menu.add(itemini); menu.add(itemche); menu.add(itemexp); menu.add(iteminv); menu.add(itemart); menu.add(itemsalir);
        mb.add(menu); mb.add(menuayu);  
   
		
		Menu init = new Menu();
        marco.setJMenuBar(mb);  
        init.setMarco(marco);
        marco.setContentPane(init);
        marco.setTitle("Menú");
		marco.validate();
		
		
	}

}
