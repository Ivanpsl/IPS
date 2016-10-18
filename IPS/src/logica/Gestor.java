package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

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

	public void a�adirInscripcionEvento(Atleta atl,Evento evento) {
		Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

		boolean repetido =false;
		for (int i = 0; i < evento.getInscritosEvento().size(); i++) {
			if(evento.getInscritosEvento().get(i).getIdEvento()==evento.getId() && 
					evento.getInscritosEvento().get(i).getAtleta()== atl){
				repetido=true;				
			}
		}
		if(!repetido && evento.getPlazasDisponibles()>0 ){
			Inscripcion ins = new Inscripcion(atl, fechaActual,evento.getId());
			System.out.println("Se ha inscrito correctamente. Detalles:");
			System.out.println(ins.toString());
			evento.a�adirInscrito(ins);
			mostrarNumeroCuenta(ins);
			bd.a�adirInscrito(atl, ins);
		}
	}
	
	/**
	 * Pasar el n�mero de cuenta al usuario para que pueda pagar, viene en un txt antes de los que han pagado.
	 * @throws IOException
	 */
	public void mostrarNumeroCuenta(Inscripcion ins)
	{
		System.out.println("A continuaci�n se mostrar� donde realizar la transferencia: \n");
		
		try {
			BufferedReader file = new BufferedReader(new FileReader("ficheros/banco.txt"));
			String line = file.readLine();
			System.out.println("N�mero de cuenta: " + line);
			System.out.println("Se ha actualizado su estado y ahora consta como Pendiente de Pago.");
			ins.setEstado(1);
			bd.actualizarEstadoPago(ins, 1);
			file.close();
		}
		catch (Exception e)
		{
			System.err.println("No se ha podido facilitar el n�mero de cuenta. " + e.getMessage());
		}
	}
	
	/**
	 * Asignaci�n de dorsales a partir del 10.
	 * Antes de ello se ordenan los inscritos por fecha de inscripci�n y estado del pago.
	 */
	public void asignarDorsales(int idEvento)
	{
		Collections.sort(eventos.get(idEvento).getInscritosEvento());
		
		ArrayList<Inscripcion> inscritos = eventos.get(idEvento).getInscritosEvento();
		int cont = 10;
		
		for (Inscripcion i : inscritos){
			if (i.getEstado() == 2)
			{
				i.setDorsal(cont);
				bd.asignarDorsal(i, cont);
				cont++;
			}
			else
			{
				i.setDorsal(-1);
				bd.asignarDorsal(i, -1);
			}
		}
	}
	
	/**
	 * M�todo que verifica los DNI del txt para saber que atletas han pagado.
	 * @throws IOException 
	 */
	public void comprobarPagados(int idEvento)
	{
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("ficheros/banco.txt"));
			String linea = fichero.readLine(); //La primera es el numero de cuenta
			
			ArrayList<Inscripcion> inscritos = eventos.get(idEvento).getInscritosEvento();
			
			while (fichero.ready()) {
				linea = fichero.readLine();
		    	String[] trozos = linea.split("\n");
		    	for (Inscripcion i : inscritos)
		    	{
		    		if (i.getAtleta().getDNI().toUpperCase().equals(trozos[0]))
		    		{
		    			i.setEstado(2);
		    			bd.actualizarEstadoPago(i, 2);
		    		}
		    	}
		    }
		    fichero.close();
		    }
		    catch (FileNotFoundException fnfe) {
		    	new FileNotFoundException("Fichero del banco no encontrado");
		    }
		    catch (IOException ioe) {
		    	new RuntimeException("Error de entrada/salida.");
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
		bd.a�adirAtleta(atl);
	}
	/***
	 * Metodo usado para crear eventos y a�adirlos tanto al array como a la base de datos
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
		bd.a�adirEventoABD(nuevoEvento);
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
	
	public boolean existeAtletaEnEvento(int id, String dni){
		Evento ev = null;
		for(Evento e : eventos){
			if(e.getId() == id)
				ev = e;
		}
		if(ev != null){
			for(Inscripcion i : ev.getInscritosEvento()){
				if(i.getAtleta().getDNI().equals(dni)){
					return true;
				}
			}
		}
		return false;
	}
	
}
