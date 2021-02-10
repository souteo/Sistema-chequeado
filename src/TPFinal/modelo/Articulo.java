package TPFinal.modelo;

import java.time.LocalDate;

public abstract class Articulo {
	
	private String titulo;
	private LocalDate fecha;
	
	public abstract boolean esDeAltoImpacto();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
