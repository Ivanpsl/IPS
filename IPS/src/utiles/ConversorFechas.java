package utiles;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConversorFechas {
	/**
	 * Suma a una fecha el numero de d�as que se le pasa como par�metro, ya sea positivo (sumar) negativo (restar)
	 * @param fecha Fecha que se va a editar, vale sql o util
	 * @param dias Dias que se suman a la fecha
	 * @return Devuelve la fecha de tipo sql
	 */
	public static Date sumarRestarDiasFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); 
		calendar.add(Calendar.DAY_OF_YEAR, dias); 
		Date fechaBuena = convertFechaJavaSQL(calendar.getTime());
		return fechaBuena;
	}
	/**
	 * Convierte una fecha de java.util.Date a sql.Date
	 * @param fecha Fecha a convertir
	 * @return Retorna la fecha en sql.Date
	 */
	public static Date convertFechaJavaSQL(java.util.Date fecha){
		Date fechabuena = new Date(fecha.getTime());
		return fechabuena;
	}
	/**
	 * Obitene la diferencia en segundos entre dos fechas dadas en un tipo int.
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public static int obtenerDiferenciaFechaEnSegundos(Date fechaInicio, Date fechaFin){
		int result = 0;
		long inicio = fechaInicio.getTime();
		long fin = fechaFin.getTime();
		result = (int)((fin - inicio)/1000);
		return result;
	}
	
	/**
	 * Utiliza el String fechaNacimiento para calcular su edad.
	 * @param fecha
	 */
	public static int fechaNacimientoEdad(String fecha)
	{
		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaNacimiento = Calendar.getInstance(); //para que no se queje
		java.util.Date fechaNac = null;
		
		try 
		{
			fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		} 
		catch (ParseException e) {
			System.err.println("Error al convertir la fecha de nacimiento a edad \n" + e.getMessage());
		}
		
		fechaNacimiento.setTime(fechaNac);
		
        int a�o = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        
        if(mes < 0 || (mes == 0 && dia < 0))
        {
        	a�o--;
        }
        
        return a�o;
	}
	/**
	 * Devuelve la fecha actual en formato sql
	 * @return
	 */
	public static Date getFechaActual(){
		Date fecha = new Date(Calendar.getInstance().getTime().getTime());
		return fecha;
	}
}
