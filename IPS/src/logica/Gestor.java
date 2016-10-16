package logica;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import persistencia.ConexionBD;
import persistencia.GestorFicheros;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	ArrayList<Atleta> atletas;
	Organizador organizador;
	
	private ConexionBD bd = new ConexionBD();
	private GestorFicheros gF = new GestorFicheros();
	
	
	public Gestor(){
		this.eventos  = new ArrayList<Evento>();
		this.atletas= new ArrayList<Atleta>();
		cargarDatos();
		
	}
	/**
	 * Metodo que llama a la base de datos para cargar todos los datos
	 */
	private void cargarDatos(){
		if(!bd.cargarDatos(this)) {
			JOptionPane.showMessageDialog(null, "Imposible conectar con la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		for(Evento e: eventos){
			if(e.getFinalizado())e.generarClasificacion();
		}
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

	public void añadirInscripcionEvento(Atleta atl,Evento evento) {
		Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

		boolean repetido =false;
		for (int i = 0; i < evento.getInscritosEvento().size(); i++) {
			if(evento.getInscritosEvento().get(i).getIdEvento()==evento.getId() && 
					evento.getInscritosEvento().get(i).getAtleta()== atl){
				repetido=true;				
			}
		}
		if(!repetido && evento.getPlazasDisponibles()>0 ){
			Inscripcion ins = new Inscripcion(atl, fechaActual);
			System.out.println("Se ha inscrito correctamente. Detalles:");
			System.out.println(ins.toString());
			evento.añadirInscrito(ins);
			bd.añadirInscrito(atl, ins);
		}
		
		
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
		bd.añadirAtleta(atl);
	}
	/***
	 * Metodo usado para crear eventos y añadirlos tanto al array como a la base de datos
	 * @param nombre
	 * @param tipo
	 * @param precio
	 * @param distancia
	 * @param fecha_comienzo
	 * @param fecha_fin_insc
	 * @param plazasTotales
	 */
	public void crearEvento(String nombre, String tipo, double precio, double distancia, Date fecha_comienzo, Date fecha_fin_insc, int plazasTotales){
		Evento nuevoEvento= new Evento(getEventos().size(),nombre,tipo, precio, distancia,fecha_comienzo, 
				 fecha_fin_insc, plazasTotales, false);
		eventos.add(nuevoEvento);
		bd.añadirEventoABD(nuevoEvento);
	}
	
	/**
	 * Metodo que da por concluido el evento con el id que se le pasa por parametro y busca el fichero con los resultados
	 * @param id: id del evento que finaliza
	 */
	public void finalizarEvento(int id){
		gF.obtenerResultadosEvento(obtenerEventoPorId(id),bd);
		eventos.get(id).setFinalizado();
		bd.marcarComoFinalizado(obtenerEventoPorId(id));
	}

	public void listarEventosAbiertos() {
		ArrayList<Evento> eventosAbiertos = getEventosAbiertos();
		for (int i = 0; i < eventosAbiertos.size(); i++) {
			System.out.println(i+". "+ eventos.get(i).toString());
		}
		
	}
	
}
