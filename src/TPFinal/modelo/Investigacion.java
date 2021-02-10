package TPFinal.modelo;

import java.time.LocalDate;

public class Investigacion extends Articulo {

	//variables
	
	private int categoria;
	private String tema_investigacion;
	private String palabra_clave;
	private String epigrafe;
	private String contenido;
	private int id;
	
	//métodos
	
	public boolean esDeAltoImpacto() {
		return true;
	}
	
	//sets y gets
	

	public String getTema_investigacion() {
		return tema_investigacion;
	}
	public void setTema_investigacion(String tema_investigacion) {
		this.tema_investigacion = tema_investigacion;
	}
	public String getPalabra_clave() {
		return palabra_clave;
	}
	public void setPalabra_clave(String palabra_clave) {
		this.palabra_clave = palabra_clave;
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


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	
	
	
}
