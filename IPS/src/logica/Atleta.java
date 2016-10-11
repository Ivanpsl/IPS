package logica;

import java.sql.Date;

import oracle.net.aso.s;

public class Atleta {
	String DNI;
	String nombre;
	String categoria;
	Date fechaInscripcion;
	public Atleta(String dni, String nombre, String categoria, Date fecha){
		this.DNI = dni;
		this.nombre = nombre;
		this.categoria = categoria;
		this.fechaInscripcion = fecha;
	}
	private void setDNI(String dNI) {
		DNI = dNI;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	private void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getDNI() {
		return DNI;
	}
	public String getNombre() {
		return nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	
	public String toString(){
		StringBuilder sB= new StringBuilder();
		sB.append("DNI: "+getDNI()+" Nombre: "+getNombre()+" Categoría: "+getCategoria()+" Fecha inscripcion: "+getFechaInscripcion().toString());
		return sB.toString();
	}
}
