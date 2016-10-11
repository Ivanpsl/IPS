package logica;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Inscrito {

	private int dorsal;
	private Atleta atleta;
	private final int PREINSCRITO = 0;
	private final int PENDIENTEPAGO = 1;
	private final int PAGADO = 2;
	private int estado;
	private Date fechaInscripcion;
	private Date fechaLimite;
	private final int MAXDIAS = 2;
	private static String dateFormat = "yyyy/MM/dd";

	public Inscrito (Atleta atleta, int dorsal, Date fechaInscrip) 
	{
		this.atleta = atleta;
		this.dorsal = dorsal;
		this.fechaInscripcion = fechaInscrip;
		this.fechaLimite = sumarRestarDiasFecha(fechaInscripcion, 2);
		
	}


	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getDorsal() {
		return dorsal;
	}


	
	public static Date convierteStringADate(String stringFecha, String formato){
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		Date fecha;
		try {
			fecha = (Date) sdf.parse(stringFecha);
		} catch (ParseException e) {
			System.err.println("Error al convertir las fechas");
			return null;
		}
		return fecha;
	}

	public static Date sumarRestarDiasFecha(Date fecha, int dias) {
		System.out.println(fecha.toString());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); 
		calendar.add(Calendar.DAY_OF_YEAR, dias); 
		System.out.println(fecha.toString());
		Date fechaBuena = convertFechaJavaSQL(calendar.getTime());
		System.out.println(fechaBuena.toString());
		return fechaBuena;
	}
	
	private static Date convertFechaJavaSQL(java.util.Date fecha){
		Date fechabuena = new Date(fecha.getTime());
		return fechabuena;
	}
}
