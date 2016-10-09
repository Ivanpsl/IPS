package logica;

import java.sql.Date;
import java.util.ArrayList;

public class Evento {

	private ArrayList<Atleta> atletas;
	private ArrayList<Inscrito> participantes;

	// Datos del evento
	String nombre;
	String tipo;
	int precio;
	Date fechaCompeticion;
	Date fechaFinInscripcion;
	//De momento se considera una unica fecha de cada tipo. 
	
	public Evento(String name, String type, int price, Date dateComp, Date dateEndIns) {
		this.atletas = new ArrayList<Atleta>();
		this.participantes = new ArrayList<Inscrito>();

		this.nombre = name;
		this.fechaCompeticion = dateComp;
		this.fechaFinInscripcion = dateEndIns; 
		this.precio = price;
		this.tipo = type;
	}

	public ArrayList<Atleta> getAtletas() {
		return this.atletas;
	}

	public ArrayList<Inscrito> getInscritosEvento() {
		return this.participantes;
	}

	public String toString() {
		return "Informacion del evento";
	}

	public void añadirAtleta(Atleta a) {
		if (a != null) {
			this.atletas.add(a);
		}
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

	public int getPrecio() {
		return precio;
	}

	public Date getFechaCompeticion() {
		return fechaCompeticion;
	}

	public Date getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}
	
}
