package TPFinal.modelo;

import java.time.LocalDate;

public class Chequeo {
	private int id;
	private int categoria;
	private String palabra_clave;
	private String medio_origen;
	private String frase;
	private String enlace;
	private boolean verificacion;
	private LocalDate fecha;
	
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
	public String getPalabra_clave() {
		return palabra_clave;
	}
	public void setPalabra_clave(String palabra_clave) {
		this.palabra_clave = palabra_clave;
	}
	public String getMedio_origen() {
		return medio_origen;
	}
	public void setMedio_origen(String medio_origen) {
		this.medio_origen = medio_origen;
	}
	public String getFrase() {
		return frase;
	}
	public void setFrase(String frase) {
		this.frase = frase;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public boolean isVerificacion() {
		return verificacion;
	}
	public void setVerificacion(boolean verificacion) {
		this.verificacion = verificacion;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
	public void cargardatos(Sugerencia s) {
		setFrase(s.getFrase());
		setMedio_origen(s.getMedio_frase());
		setEnlace(s.getEnlace());
		setFecha(s.getFecha_frase());
		
	}
}
