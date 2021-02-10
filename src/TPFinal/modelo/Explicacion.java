package TPFinal.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Explicacion extends Articulo{
	
	//variables
	private String epigrafe;
	private String contenido;
	private int categoria;
	private int id;
	private String enlace;
	private ArrayList<Chequeo> chequeos = new ArrayList<Chequeo>();
	
	//métodos
	
	public boolean esDeAltoImpacto() {
		if(chequeos.size()>3)
			return true;
		else
			return false;
	}
	
	public void agregar (Chequeo c) {
		chequeos.add(c);
	}
	
	
	//sets y gets
	
	
	public int getCategoria() {
		return categoria;
	}


	public String getEnlace() {
		return enlace;
	}


	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}


	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}



	public String getEpigrafe() {
		return epigrafe;
	}


	public void setEpigrafe(String epigrafe) {
		this.epigrafe = epigrafe;
	}


	public String getContenido() {
		return contenido;
	}


	public void setContenido(String contenido) {
		this.contenido = contenido;
	}





	public ArrayList<Chequeo> getChequeos() {
		return chequeos;
	}


	public void setChequeos(ArrayList<Chequeo> chequeos) {
		this.chequeos = chequeos;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
