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
	private Date fechaLimite;
	private final int MAXDIAS = 2;
	private static String dateFormat = "yyyy/MM/dd";

	public Inscripcion (Atleta atleta, int dorsal, Date fechaInscrip) 
	{
		this.atleta = atleta;
		this.dorsal = dorsal;
		this.fechaInscripcion = fechaInscrip;
		this.estado = PREINSCRITO;
		this.fechaLimite = ConversorFechas.sumarRestarDiasFecha(fechaInscripcion, 2);
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

	
}
