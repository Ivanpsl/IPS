package logica;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import persistencia.ConexionBD;
import persistencia.GestorFicheros;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	ArrayList<Atleta> atletas;
	ArrayList<Inscripcion> inscripciones;
	private ConexionBD bd = new ConexionBD();
	private GestorFicheros gF = new GestorFicheros();
	
	
	public Gestor(){
		this.eventos  = new ArrayList<Evento>();
		this.atletas= new ArrayList<Atleta>();
		this.inscripciones=new ArrayList<Inscripcion>();
		cargarDatos();
	}
	
	private void cargarDatos(){
		bd.cargarDatos(this);
	}
	public ArrayList<Evento> getEventos(){
		return eventos;
	}
	
	public ArrayList<Atleta> getAtletas(){
		return atletas;
	}
	public ArrayList<Evento> getEventosAbiertos(){
		Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());
		ArrayList<Evento> evDisp = new ArrayList<Evento>();
		for(Evento evento : eventos){
			if(evento.getFechaFinInscripcion().getTime() >= fechaActual.getTime()){
				evDisp.add(evento);
			}
		}
		return evDisp;
	}

	public ArrayList<Evento> getEventosDisponibles(){
		return this.eventos;
	}
	public ArrayList<Evento> obtenerEventosEInscripciones(String dni){
		ArrayList<Evento> evInscrito= new ArrayList<Evento>();
		for(Evento e: eventos){
			for(Inscripcion a: e.getInscritosEvento()){
				if(a.getAtleta().DNI.equals(dni)){ 
					evInscrito.add(e);
				}
			}
		}
		return evInscrito;
	}
	public Inscripcion getInscripcion(String dni, Evento ev){
		for(Inscripcion ins: ev.getInscritosEvento()){
			if(dni.equals(ins.getAtleta().getDNI())) return ins;
		}
		return null;
	}
	
	public void mostrarInscritosDeEvento(int id){
		Evento ev = null;
		for(Evento e : eventos){
			if(e.getId() == id){
				ev = e;
			}
		}
		if(ev == null){
			System.out.println("No se ha encontrado el evento");
		}else{
			ArrayList<Inscripcion> inscritos = ev.getInscritosEvento();
			for(Inscripcion a: inscritos){
				System.out.println(a.toString());
			}
		}
	}
	public boolean comprobarAtletaRegistrado(String dni){
		for (int i = 0; i < atletas.size(); i++) {
			if(atletas.get(i).getDNI().equals(dni)){
				return true;
			}
		}
		return false;
		
	}
	

	public Atleta buscarAtletaPorDNI(String dni){
		for (int i = 0; i < atletas.size(); i++) {
			if(atletas.get(i).getDNI().equals(dni)){
				return atletas.get(i);
			}
		}
		return null;
	}

	public void a�adirInscripcionEvento(Atleta atl,Evento evento) {
		Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

		boolean repetido =false;
		for (int i = 0; i < inscripciones.size(); i++) {
			if(inscripciones.get(i).getIdEvento()==evento.getId() && 
					inscripciones.get(i).getAtleta()== atl){
				repetido=true;				
			}
		}
		if(!repetido && evento.getPlazas()>0 ){
			Inscripcion ins = new Inscripcion(evento, atl, fechaActual);
			System.out.println("Se ha inscrito correctamente. Detalles:");
			System.out.println(ins.toString());
			inscripciones.add(ins);
		}
		
		
	}
	
	/**
	 * Metodo llamado al finalizar el evento para obtener los resultados de las clasificaciones
	 * @param ev
	 */
	public void obtenerTiempos(Evento ev){
		gF.obtenerResultadosEvento(ev);
		ev.generarClasificacion();
	}
	
	public Evento obtenerEventoPorId(int id){
		for(Evento e : eventos){
			if(e.getId() == id)
				return e;
		}
		return null;
	}

	public void addAtleta(Atleta atl) {
		atletas.add(atl);
		
	}

	public void listarEventosAbiertos() {
		ArrayList<Evento> eventosAbiertos = getEventosAbiertos();
		for (int i = 0; i < eventosAbiertos.size(); i++) {
			System.out.println(i+". "+ eventos.get(i).toString());
		}
		
	}
	
}
