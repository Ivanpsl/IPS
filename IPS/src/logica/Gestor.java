package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;
import logica.Vistas.PlazoInscripcion;
import persistencia.ConexionBD;
import persistencia.GestorFicheros;
import usuarios.Organizador;
import utiles.Comprobaciones;
import utiles.ConversorFechas;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	ArrayList<Atleta> atletas;
	Organizador organizador;
	Evento eventoSeleccionado;
	Atleta atIdentificado;
	
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
	/**
	 * Metodo que busca y marca como atleta identificado si es que existe
	 * @param dni: del atleta buscado
	 * @return
	 */
	public boolean identificarAtletaPorDNI(String dni){
		atIdentificado=buscarAtletaPorDNI(dni);
		if(atIdentificado!=null){
			return true;
		}return false;
	}
	public Atleta getAtletaIdentificado(){
		return atIdentificado;
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
			Inscripcion ins = new Inscripcion(atl, fechaActual,evento);
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
	public void crearEvento(String nombre, String tipo, double distancia, Date fecha_comienzo, Date fecha_fin_insc, int plazasTotales
			,ArrayList<Categoria> categoriasDelEvento, ArrayList<PlazoInscripcion>  plazos){
		//Los fallos de argumento que los controle la interfaz para que no se detenga la ejecuci�n. 
		if(!Comprobaciones.esString(nombre) || !Comprobaciones.esString(tipo) ||  fecha_comienzo == null || fecha_fin_insc == null || categoriasDelEvento == null || plazos == null)
			throw new IllegalArgumentException();
		Date fechaActual = ConversorFechas.getFechaActual();
		if(fecha_comienzo.getTime() <= fechaActual.getTime() || fecha_fin_insc.getTime() <= fechaActual.getTime())
			throw new DateTimeException("Las fechas ya han pasado introducidas ya han pasado.");
		
		//Si todo esta bien se crea el evento y se a�ade
		Evento nuevoEvento= new Evento(getEventos().size(),nombre,tipo,distancia, plazasTotales, false, categoriasDelEvento, plazos);
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
	public void confirmarSeleccion(Evento ev){
		eventoSeleccionado=ev;
	}
	public Evento getEventoSeleccionado(){
		return eventoSeleccionado;
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
	public void inscribirLote(List<Atleta> atletasAInscribir) {
		for (int i = 0; i < atletasAInscribir.size(); i++) {
			a�adirInscripcionEvento(atletasAInscribir.get(i), eventoSeleccionado);
		}
		
		
	}
	
}
