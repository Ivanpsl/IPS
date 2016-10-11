package logica;

import java.sql.Date;

import utiles.ConversorFechas;

public class Inscripcion implements Comparable<Inscripcion> {

	
	public static final int PREINSCRITO = 0;
	public static final int PENDIENTEPAGO = 1;
	public static final int PAGADO = 2;
	
	private final int MAXDIAS = 2;
	private static String dateFormat = "yyyy/MM/dd";
	
	private int dorsal;
	private Atleta atleta;
	private int estado;
	
	private Date fechaInscripcion;
	private Date fechaLimite;
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
		this.estado = PREINSCRITO;
		fechaInscripcion= ConversorFechas.convertFechaJavaSQL(fecha_ins);
		this.fechaLimite = ConversorFechas.sumarRestarDiasFecha(fechaInscripcion, 2);
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
	
	public Date getFechaInscripcion(){
		return this.fechaInscripcion;
	}
	
	public String toString()
	{
		StringBuilder sB= new StringBuilder();
		sB.append("Datos atleta: " + getAtleta().toString() + "\n");
		sB.append("Estado: " + getEstado() + " Dorsal: " + getDorsal() + " F.Inscripción: " + getFechaInscripcion());
		return sB.toString();
	}
	
	
	@Override
	public int compareTo(Inscripcion i) {
		if (fechaInscripcion.compareTo(i.fechaInscripcion) < 0) {
            return -1;
        }
		else if (fechaInscripcion.compareTo(i.fechaInscripcion) > 0) {
            return 1;
        }
		else
		{
			if (estado < i.estado)
				return -1;
			if (estado > i.estado)
				return 1;
			return 0;
		}
	}
}
