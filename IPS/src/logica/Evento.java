package logica;

import java.sql.Date;
import java.util.ArrayList;

public class Evento {


	private ArrayList<Inscripcion> participantes;

	// Datos del evento
	int id;
	String nombre;
	String tipo;
	double precio;
	double distancia;
	Date fechaCompeticion;
	Date fechaFinInscripcion;
	//De momento se considera una unica fecha de cada tipo. 
	
	public Evento(int id, String name, String type, double price, double distancia, java.util.Date fecha_comienzo, java.util.Date fecha_fin_insc) {
		
		this.participantes = new ArrayList<Inscripcion>();
		this.id=id;
		this.nombre = name;
		this.fechaCompeticion = (Date) fecha_comienzo;
		this.fechaFinInscripcion = (Date) fecha_fin_insc; 
		this.precio = price;
		this.tipo = type;
		this.distancia= distancia;
	}
	
	
	public void añadirInscrito(Inscripcion inscripcion){
		participantes.add(inscripcion);
	}
	

	public ArrayList<Inscripcion> getInscritosEvento() {
		return this.participantes;
	}

	public String toString() {
		StringBuilder sB= new StringBuilder();
		sB.append("ID:" + getId() + " Nombre: " + getNombre() + " Tipo: " + getTipo() + "Precio: " + getPrecio() + " Distancia(km): " + getDistancia() + "\n");
		sB.append("F.Competición: " + getFechaCompeticion() + " F.Inscripción: " + getFechaFinInscripcion());
		return sB.toString();
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public double getPrecio() {
		return this.precio;
	}

	public Date getFechaCompeticion() {
		return this.fechaCompeticion;
	}

	public Date getFechaFinInscripcion() {
		return this.fechaFinInscripcion;
	}
	
	public int getId(){
		return this.id;
	}
	
	public double getDistancia()
	{
		return this.distancia;
	}
	
	
}
