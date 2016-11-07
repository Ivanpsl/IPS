package logica.Vistas;

import java.sql.Date;
import java.util.ArrayList;



import logica.GestorClasificaciones;
import persistencia.ConexionBD;

public class Evento {


	GestorClasificaciones gC= new GestorClasificaciones();
	
	private ArrayList<Inscripcion> inscripciones; 
	private ArrayList<Clasificacion> clasificaciones; 
	private ArrayList<Categoria> categoriasDelEvento;
	private ArrayList<PlazoInscripcion> plazosDeInscripcion;
	
	ArrayList<String> tiposEventosDefecto;
	
	
	// Datos del evento
	int id;
	String nombre;
	String tipo;
	int plazasDisponibles;
	int plazasTotales;
	//double precio;
	double distancia;
	boolean finalizado = false;
	
	Date fechaCompeticion;
	Date fechaFinInscripcion;
	Date fechaComienzo;

	//De momento se considera una unica fecha de cada tipo. 
	
	public Evento(int id, String name, String type, 
			double distancia, int plazasTotales, boolean finalizado,ArrayList<Categoria> categoriasDelEvento,
			ArrayList<PlazoInscripcion> plazos) {
		
		this.inscripciones = new ArrayList<Inscripcion>();
		this.id=id;
		this.nombre = name;
//		this.fechaCompeticion = (Date) fecha_comienzo;
//		this.fechaFinInscripcion = (Date) fecha_fin_insc; 
		//this.precio = price;
		this.tipo = type;
		this.plazasTotales=plazasTotales;
		this.plazasDisponibles= plazasTotales;
		this.distancia= distancia;
		this.finalizado=finalizado;
		this.categoriasDelEvento=categoriasDelEvento;
		this.plazosDeInscripcion=plazos;
		
		//Tipos de eventos
		//cargarTiposEventosPorDefecto(tiposEventosDefecto);
	}
	public Evento(String name, String type, 
			double distancia, int plazasTotales, Date fechaComienzo,ArrayList<Categoria> categoriasDelEvento,
			ArrayList<PlazoInscripcion> plazos) {
		
		this.inscripciones = new ArrayList<Inscripcion>();
		this.nombre = name;
//		this.fechaCompeticion = (Date) fecha_comienzo;
//		this.fechaFinInscripcion = (Date) fecha_fin_insc; 
		//this.precio = price;
		this.tipo = type;
		this.plazasTotales=plazasTotales;
		this.plazasDisponibles= plazasTotales;
		this.distancia= distancia;
		this.fechaComienzo = fechaComienzo;
		this.categoriasDelEvento=categoriasDelEvento;
		this.plazosDeInscripcion=plazos;
		
		//Tipos de eventos
		//cargarTiposEventosPorDefecto(tiposEventosDefecto);
	}
	
	/**
	 * Crea el arrayList con unos tipos de eventos por defecto. 
	 */
	public static void cargarTiposEventosPorDefecto(ArrayList<String> list){
		list = new ArrayList<String>();
		list.add("Marat�n");
		list.add("Monta�a");
		list.add("Triatlon");
	}
	
	/**
	 * Devuelve tipos de eventos por defecto para cargar en la interfaz por ejemplo
	 * @return
	 */
	public static ArrayList<String> getTiposEventosPorDefecto(){
		ArrayList<String> typeEv = new ArrayList<String>();
		cargarTiposEventosPorDefecto(typeEv);
		return typeEv;
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
		sB.append("\n\n **[ID:" + getId() + " Nombre: " + getNombre() + " ]\nTipo: " + getTipo() + " Precio: " + " Distancia(km): " + getDistancia() + "\n");
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
	 * Metodo generarClasificacion que genera las clasificaciones de la carrera y sera llamado al final de la competicion
	 */
	public void generarClasificacion(){
		clasificaciones=gC.calcularInscripciones(inscripciones,categoriasDelEvento);
	}
	

	
	/**
	 * Metodo que da por concluido el evento, lo marca como finalizado y genera las clasificaciones en funcion de los resultados
	 * 
	 */
	public void setFinalizado(){
		finalizado=true;
		generarClasificacion();
	}

	public PlazoInscripcion getUltimoPlazo(){
		if(!finalizado)
			return plazosDeInscripcion.get(0);
		return null;	
	}
	public ArrayList<Categoria> getCategorias(){
		return categoriasDelEvento;
	}
	public void a�adirCategoria(Categoria categoria){
		categoriasDelEvento.add(categoria);
	}
	public ArrayList<PlazoInscripcion> getPlazos(){
		return plazosDeInscripcion;
	}
}
