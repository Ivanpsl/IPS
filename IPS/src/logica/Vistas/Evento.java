package logica.Vistas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

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
	int idOrganizador;

	Date fechaFinInscripcion;
	Date fechaComienzo;
	int numeroEtapas;

	
	public Evento(int id, String name, String type, 
			double distancia, int plazasTotales, boolean finalizado,ArrayList<Categoria> categoriasDelEvento,
			ArrayList<PlazoInscripcion> plazos,Date fechaComienzo, int idOrganizador) {
		
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
		this.fechaComienzo=fechaComienzo;
		this.idOrganizador=idOrganizador;
		//Tipos de eventos
		//cargarTiposEventosPorDefecto(tiposEventosDefecto);
	}
	public Evento(String name, String type, 
			double distancia, int plazasTotales, Date fechaComienzo,ArrayList<Categoria> categoriasDelEvento,
			ArrayList<PlazoInscripcion> plazos, int idOrganizador, int nEtapas) {
		
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
		this.idOrganizador=idOrganizador;
		this.numeroEtapas = nEtapas;
		//Tipos de eventos
		//cargarTiposEventosPorDefecto(tiposEventosDefecto);
	}
	
	/**
	 * Crea el arrayList con unos tipos de eventos por defecto. 
	 */
	public static void cargarTiposEventosPorDefecto(ArrayList<String> list){
		list = new ArrayList<String>();
		list.add("Maratón");
		list.add("Montaña");
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
	

	
	public void añadirInscrito(Inscripcion inscripcion){
		inscripciones.add(inscripcion);
		// Al crear una inscripcion se decrementa plazas y se añade inscripcion al evento
		setPlazasDisponibles(getPlazasDisponibles()-1);
	}
	

	public ArrayList<Inscripcion> getInscritosEvento() {
		return this.inscripciones;
	}

	public String toString() {
		StringBuilder sB= new StringBuilder();
		sB.append("\n\n **[ID:" + getId() + " Nombre: " + getNombre() + " ]\nTipo: " + getTipo() + " Precio: " + " Distancia(km): " + getDistancia() + "\n");
		sB.append("F.Competición: " + getFechaCompeticion() + " F.Inscripción: " + getFechaFinInscripcion());
		sB.append(" \nParticipantes: " );
		for(Inscripcion i : inscripciones){
			sB.append("\n\t -" +i.toString());
		}
		return sB.toString();
	}
	public boolean getFinalizado(){
		return comprobarFinalizado();
		
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
		return this.fechaComienzo;
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
	public void asignarTiemposDorsal(int dorsal, ArrayList<Integer> tiempos,ConexionBD bd){
		boolean encontrado=false;
		for(Inscripcion p : inscripciones){
			if(p.getDorsal()==dorsal){
				p.setTiempoSegundos(tiempos.get(tiempos.get(tiempos.size()-1)));
				p.setTiemposPorEtapas(tiempos);
				bd.asignarTiempo(p, tiempos);
				encontrado=true;
			}
		}
		if(!encontrado)
			System.err.println("Participante con dorsal " + dorsal +" y con un tiempo de " + tiempos + "seg. no ha sido encontrado" );
	}
	/***
	 * Metodo generarClasificacion que genera las clasificaciones de la carrera y sera llamado al final de la competicion
	 */
	public void generarClasificacion(){
		clasificaciones=gC.calcularInscripciones(inscripciones,categoriasDelEvento);
	}
	
	public boolean comprobarFechasInscripcion(Date fechaActual){
		boolean cambio=false;
		ArrayList<PlazoInscripcion> arrayFinal = new ArrayList<PlazoInscripcion>();
		if(plazosDeInscripcion!=null && plazosDeInscripcion.size()>0){
			for(PlazoInscripcion plazo: plazosDeInscripcion){
				if(fechaActual.after(plazo.getFechaFin())){
					cambio=true;
				}else arrayFinal.add(plazo);
			}
			plazosDeInscripcion=arrayFinal;
			if(plazosDeInscripcion.size()==0);
		}
		return cambio;
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
		if(!finalizado && plazosDeInscripcion!=null && plazosDeInscripcion.size()>0)
			return plazosDeInscripcion.get(0);
		return null;	
	}
	public ArrayList<Categoria> getCategorias(){
		return categoriasDelEvento;
	}
	public void añadirCategoria(Categoria categoria){
		categoriasDelEvento.add(categoria);
	}
	public ArrayList<PlazoInscripcion> getPlazos(){
		return plazosDeInscripcion;
	}
	public ArrayList<Clasificacion> getClasificaciones(){
		return clasificaciones;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getIdOrganizador(){
		return idOrganizador;
	}
	public boolean comprobarFinalizado(){
		long actual = Calendar.getInstance().getTimeInMillis();
		if(this.fechaComienzo.getTime() <= actual){
			this.finalizado = true;
			return finalizado;
		}
		return false;
	}
	
	public int getNumeroEtapas(){
		return numeroEtapas;
	}
	public int obtenerPosicionAbsoluta(Inscripcion ins){
		return gC.obtenerPosicionAbsoluta(ins, this);	
	}
	public int obtenerPosicionCategoria(Inscripcion ins){
		return gC.obtenerPosicion(ins, this);
	}
}
