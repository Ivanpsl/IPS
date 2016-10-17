package logica;

import java.sql.Date;

import utiles.ConversorFechas;

public class Inscripcion implements Comparable<Inscripcion> {

	
	public static final int PREINSCRITO = 0;
	public static final int PENDIENTEPAGO = 1;
	public static final int PAGADO = 2;
	
	
	private final int MAXDIAS = 2;
	private static String dateFormat = "yyyy/MM/dd";
	
	private int dorsal; //-1 sin asignar
	private Atleta atleta; //Necesario para relacionar el atleta con sus inscripciones
	private String categoria; 
	private int estado;
	private int id_evento;
	private Date fechaInscripcion;
	private Date fechaLimite;
	private int tiempo_segundos;
	
	/**
	 * Constructor de Inscripcion e
	 * standar desde la aplicacion 
	 * @param atleta
	 * @param dorsal -> NO SE PUEDE PASAR COMO PARAMETRO. CALCULAR DENTRO
	 * @param 
	 * @param fechaInscrip
	 */
	public Inscripcion (Atleta atleta, Date fechaInscrip) 
	{
		this.atleta = atleta;
		this.fechaInscripcion = fechaInscrip;
		this.estado = PREINSCRITO;
		this.fechaLimite = ConversorFechas.sumarRestarDiasFecha(fechaInscripcion, 2);
		this.tiempo_segundos=10000;
		this.dorsal=-1;
		calcularCategoria();
	
	}
	
	public void calcularCategoria(){
	    if(atleta.getSexo()==0){
	      this.categoria="Masculino";
	    }else{
	      this.categoria="Femenino";
	    }
	  }
	
	/***
	 * Constructor de Inscripcion usado para crear inscritos desde la base de datos 
	 * @param dorsal
	 * @param fecha_ins
	 * @param estado
	 * @param segundos
	 */
	public Inscripcion (int id_evento, int dorsal, java.util.Date fecha_ins, int estado, int segundos, String categoria) 
	{
		this.id_evento=id_evento;
		this.atleta = null;
		this.dorsal = dorsal;
		this.estado = estado;
		this.fechaInscripcion= ConversorFechas.convertFechaJavaSQL(fecha_ins);
		this.fechaLimite = ConversorFechas.sumarRestarDiasFecha(fechaInscripcion, 2);
		this.tiempo_segundos=segundos;
		this.categoria=categoria;
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
	
	public int getIdEvento(){
		return id_evento;
	}
	public int getResultado(){
		return tiempo_segundos;
	}
	public String getCategoria(){
		return categoria;
	}
	public String toString()
	{
		String dorsal;
		if(getDorsal()==-1) dorsal= "[Sin asignar]";
		else dorsal= "" + getDorsal();
		String estado;
		if(getEstado()==PREINSCRITO)estado = "Preinscrito";
		else if(getEstado()==PENDIENTEPAGO) estado="Pendiente de pago";
		else estado="Pagado";
		StringBuilder sB= new StringBuilder();
		sB.append("Datos atleta: " + getAtleta().toString() + "\n");
		sB.append("\tEstado: " + estado + ". Dorsal: " + dorsal + ". F.Inscripción: " + getFechaInscripcion());
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
