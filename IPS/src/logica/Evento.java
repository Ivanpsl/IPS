package logica;

import java.sql.Date;
import java.util.ArrayList;

import persistencia.ConexionBD;

public class Evento {



	private ArrayList<Inscripcion> inscripciones;

	// Datos del evento
	int id;
	String nombre;
	String tipo;
	int plazasDisponibles;
	int plazasTotales;
	double precio;
	double distancia;
	Date fechaCompeticion;
	Date fechaFinInscripcion;
	Clasificacion clasificacion;
	boolean finalizado = false;

	//De momento se considera una unica fecha de cada tipo. 
	
	public Evento(int id, String name, String type, double price, 
			double distancia, java.util.Date fecha_comienzo, 
			java.util.Date fecha_fin_insc, int plazasTotales, boolean finalizado) {
		
		this.inscripciones = new ArrayList<Inscripcion>();
		this.id=id;
		this.nombre = name;
		this.fechaCompeticion = (Date) fecha_comienzo;
		this.fechaFinInscripcion = (Date) fecha_fin_insc; 
		this.precio = price;
		this.tipo = type;
		this.plazasTotales=plazasTotales;
		this.plazasDisponibles= plazasTotales;
		this.distancia= distancia;
		this.finalizado=finalizado;
		
	}
	
	
	public void a�adirInscrito(Inscripcion inscripcion){
		inscripciones.add(inscripcion);
		// Al crear una inscripcion se decrementa plazas y se a�ade inscripcion al evento
		setPlazasDisponibles(getPlazasDisponibles()-1);
	}
	

	public ArrayList<Inscripcion> getInscritosEvento() {
		return this.inscripciones;
	}

	public String toString() {
		StringBuilder sB= new StringBuilder();
		sB.append("\n\n **[ID:" + getId() + " Nombre: " + getNombre() + " ]\nTipo: " + getTipo() + " Precio: " + getPrecio() + " Distancia(km): " + getDistancia() + "\n");
		sB.append("F.Competici�n: " + getFechaCompeticion() + " F.Inscripci�n: " + getFechaFinInscripcion());
		sB.append(" \nParticipantes: " );
		for(Inscripcion i : inscripciones){
			sB.append("\n\t -" +i.toString());
		}
		return sB.toString();
	}
	public boolean getFinalizado(){
		return finalizado;
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
	
	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}
	
	public void setPlazasDisponibles(int plazas) {
		this.plazasDisponibles = plazas;
	}
	public int getPlazasTotales(){
		return plazasTotales;
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
	
	/**
	 * Metodo usado para asignar a cada dorsal el tiempo obtenido del fichero de resultados
	 * @param dorsal: Dorsal del corredor
	 * @param tiempo: tiempo en segudos que ha tardado en completar la carrera
	 */
	public void asignarTiemposDorsal(int dorsal, int tiempo,ConexionBD bd){
		boolean encontrado=false;
		for(Inscripcion p : inscripciones){
			if(p.getDorsal()==dorsal){
				p.setTiempoSegundos(tiempo);
				bd.asignarTiempo(p, tiempo);
				encontrado=true;
			}
		}
		if(!encontrado)
			System.err.println("Participante con dorsal " + dorsal +" y con un tiempo de " + tiempo + "seg. no ha sido encontrado" );
	}
	/***
	 * Metodo generarCLasificacion que genera las clasificaciones de la carrera y sera llamado al final de la competicion
	 */
	public void generarClasificacion(){
		clasificacion=new Clasificacion(inscripciones);
	}
	
	
	public Clasificacion getClasificacion(){
		return clasificacion;
	}
	
	/**
	 * Metodo que da por concluido el evento, lo marca como finalizado y genera las clasificaciones en funcion de los resultados
	 * 
	 */
	public void setFinalizado(){
		finalizado=true;
		generarClasificacion();
	}
}
