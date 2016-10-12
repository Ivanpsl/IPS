package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import persistencia.ConexionBD;
import persistencia.GestorFicheros;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	ArrayList<Atleta> atletas;
	private ConexionBD bd = new ConexionBD();
	private GestorFicheros gF = new GestorFicheros();
	
	
	public Gestor(){
		this.eventos  = new ArrayList<Evento>();
		this.atletas= new ArrayList<Atleta>();
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
	public void mostrarEventosDisponibles(){
		for(Evento evento : eventos){
			System.out.println(evento.toString());
		}
	}
	public ArrayList<Evento> getEventosDisponibles(){
		return this.eventos;
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
	private boolean comprobarAtletaRegistrado(String dni){
		for (int i = 0; i < atletas.size(); i++) {
			if(atletas.get(i).getDNI().equals(dni)){
				return true;
			}
		}
		return false;
		
	}
	

	private Atleta buscarAtletaPorDNI(String dni){
		for (int i = 0; i < atletas.size(); i++) {
			if(atletas.get(i).getDNI().equals(dni)){
				return atletas.get(i);
			}
		}
		return null;
	}

	public void añadirInscripcionEvento() {
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		String DNI="";
		String nombre="";
		String categoria="";
		int sexo=-1;
		int edad=-1;
		System.out.println("Introduzca su DNI");
		try {
			DNI = in.readLine();
			if(!DNI.equals("")&& !comprobarAtletaRegistrado(DNI)){
				while(nombre.equals("") || nombre==null){
				System.out.println("Introduzca su nombre");
				nombre =in.readLine();
				}
				while(edad <0 || edad >120){
					System.out.println("Introduzca su edad (0 - 120)");
					edad =Integer.parseInt(in.readLine());
				}
				while(sexo !=0 || sexo !=1){
					System.out.println("Introduzca su sexo ( 0 -> M , 1 -> F");
					sexo =Integer.parseInt(in.readLine());
				}
				atletas.add(new Atleta(DNI, nombre, categoria, edad, sexo));
			}else{
				Atleta atl = buscarAtletaPorDNI(DNI);
				String respuesta="";
				System.out.println("Ya está registrado ¿Quiere continuar con sus datos?");
				System.out.println("DNI: "+ DNI +"\n Nombre :"+atl.getNombre()+
						"\n Sexo: "+ (atl.getSexo()==0 ? "Masculino":"Femenino")+
						"\n edad: "+ atl.getEdad());
				respuesta = in.readLine();
			}
		} catch (IOException e) {
			System.err.println("Lectura no valida");
			e.printStackTrace();
		}
		
		
	}
	
	
}
