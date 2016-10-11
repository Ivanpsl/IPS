package logica;

import java.sql.Date;
import java.util.ArrayList;

public class Evento {


	private ArrayList<Inscrito> participantes;

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
		//this.atletas = new ArrayList<Atleta>();
		this.participantes = new ArrayList<Inscrito>();
		this.id=id;
		this.nombre = name;
		this.fechaCompeticion = (Date) fecha_comienzo;
		this.fechaFinInscripcion = (Date) fecha_fin_insc; 
		this.precio = price;
		this.tipo = type;
		this.distancia= distancia;
	}

	

//	public ArrayList<Atleta> getAtletas() {
//		return this.atletas;
//	}

	public ArrayList<Inscrito> getInscritosEvento() {
		return this.participantes;
	}

	public String toString() {
		StringBuilder sB= new StringBuilder();
		sB.append("ID:" + id + " - ");
		sB.append("Nombre:"+ nombre);
		return sB.toString();
	}

	public void aladirInscrito(Inscrito i) {
		if (i != null) {
			this.participantes.add(i);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public Date getFechaCompeticion() {
		return fechaCompeticion;
	}

	public Date getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}
	
	public int getId(){
		return id;
	}
	
	
}
