package logica;

import java.sql.Date;
import java.util.Calendar;

import utiles.ConversorFechas;

public class Inscripcion {

	private int dorsal;
	private Atleta atleta;
	public final int PREINSCRITO = 0;
	public final int PENDIENTEPAGO = 1;
	public final int PAGADO = 2;
	private int estado;
	private Date fechaInscripcion;
	private java.util.Date fechaInscripcionSQL;
	private Date fechaLimite;
	private final int MAXDIAS = 2;
	private static String dateFormat = "yyyy/MM/dd";
	private int tiempo_segundos;
	
	
	public Inscripcion (Atleta atleta, int dorsal, Date fechaInscrip) 
	{
		this.atleta = atleta;
		this.dorsal = dorsal;
		this.fechaInscripcion = fechaInscrip;
		this.estado = PREINSCRITO;
		this.fechaLimite = ConversorFechas.sumarRestarDiasFecha(fechaInscripcion, 2);
		this.tiempo_segundos=0;
	}
	public Inscripcion (int dorsal, java.util.Date fecha_ins, int estado, int segundos) 
	{
		this.atleta = null;
		this.dorsal = dorsal;
		this.fechaInscripcionSQL = fecha_ins;
		this.estado = PREINSCRITO;
		//this.fechaLimite = ConversorFechas.sumarRestarDiasFecha(fechaInscripcion, 2);
		this.tiempo_segundos=segundos;
	}
	
	
	public boolean sigueDentro(){
		if(fechaInscripcion.getTime() < fechaLimite.getTime())
			return true;
		return false;
	}
	public int getEstado(){
		return estado;
	}
	public void setEstado(int state){
		this.estado = state;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getDorsal() {
		return dorsal;
	}
	public Atleta getAtleta(){
		return atleta;
	}
	public void setAtleta(Atleta atleta){
		this.atleta=atleta;
	}
	public int getTiempoSegundos(){
		return tiempo_segundos;
	}
	public void setTiempoSegundos(int tiempo){
		tiempo_segundos=tiempo;
	}
	

	
}
