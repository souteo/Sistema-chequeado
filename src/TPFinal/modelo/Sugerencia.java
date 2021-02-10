package TPFinal.modelo;

import java.time.LocalDate;

public class Sugerencia {

	private String frase;
	private String autor_frase;
	private String enlace;
	private String medio_frase;
	private LocalDate fecha_frase;
	
	public String getFrase() {
		return frase;
	}
	public void setFrase(String frase) {
		this.frase = frase;
	}
	public String getAutor_frase() {
		return autor_frase;
	}
	public void setAutor_frase(String autor_frase) {
		this.autor_frase = autor_frase;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public String getMedio_frase() {
		return medio_frase;
	}
	public void setMedio_frase(String medio_frase) {
		this.medio_frase = medio_frase;
	}
	public LocalDate getFecha_frase() {
		return fecha_frase;
	}
	public void setFecha_frase(LocalDate fecha_frase) {
		this.fecha_frase = fecha_frase;
	}
	
	
}
