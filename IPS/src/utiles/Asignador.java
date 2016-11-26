package utiles;




import logica.Gestor;
import logica.Vistas.Atleta;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;


public class Asignador {

	/**
	 * Metodo asignarAtleta que relaciona la inscripcion con el atleta
	 * 
	 * @param g
	 *            Gestor que maneja los datos
	 * @param ins
	 *            inscripcion
	 * @param dni
	 *            dni del atleta due�o de la inscripcion
	 * @return true si lo encuentra, false si no
	 */
	public static boolean asignarAtleta(Gestor g, Inscripcion ins, String dni) {
		for (Atleta at : g.getAtletas()) {
			if (dni.equals(at.getDNI())) {
				ins.setAtleta(at);
				return true;
			}
		}
		return false;
	}

	/***
	 * Metodo que asigna una inscripcion a su evento correspondiente
	 * 
	 * @param g
	 *            : gestor que gestiona todos los datos
	 * @param ins
	 *            : inscripcion a asignar
	 * @param id
	 *            : id del evento al que pertenece
	 * @return true si lo consigo, false si no
	 */
	public static boolean asignarAEvento(Gestor g, Inscripcion ins, int id) {
		for (Evento ev : g.getEventos()) {
			if (id == ev.getId()) {
				ev.a�adirInscrito(ins);
				return true;
			}
		}
		return false;
	}

	
}
