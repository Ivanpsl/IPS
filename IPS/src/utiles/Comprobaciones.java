package utiles;

import java.sql.Date;


public class Comprobaciones {
	public static boolean esString(String cadena){
		char[] cad = cadena.toCharArray();
		boolean res = true;
		for(char c : cad){
			if(!Character.isLetter(c) && !(c == ' '))
				res = false;
		}
		return res;
	}
	/**
	 * Comprueba que sea un objeto java.sql.Date 
	 * 
	 * (Es una bobada porque ya te obliga a meterle un sql.Date
	 * @param miFecha
	 * @return
	 */
	public static boolean esFechaSQL(Date miFecha){
		if(miFecha instanceof java.sql.Date){
			return true;
		}
		return false;
	}
	
	public static boolean esNumero(String miNumero){
		char[] cad = miNumero.toCharArray();
		for(char c : cad){
			if(!Character.isDigit(c))
				return false;
		}
		return true;
	}
}
