package utiles;

import java.sql.Date;
import java.util.Calendar;

public class ConversorFechas {
	/**
	 * Suma a una fecha el numero de días que se le pasa como parámetro, ya sea positivo (sumar) negativo (restar)
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
}
