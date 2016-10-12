package Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import logica.Atleta;
import logica.Inscripcion;

import org.junit.Test;

public class testOrdenar {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		Atleta a1 = new Atleta("1", "a1", "mayor", 20, 0);
		Atleta a2 = new Atleta("2", "a2", "mayor", 21, 0);
		Atleta a3 = new Atleta("3", "a3", "menor", 15, 0);
		Atleta a4 = new Atleta("4", "a4", "menor", 16, 0);
		Atleta a5 = new Atleta("5", "a5", "mayor", 24, 0);
		
		Inscripcion i1 = new Inscripcion(0,a1, 0, new Date(116, 2, 24));
		Inscripcion i2 = new Inscripcion(0,a2, 0, new Date(116, 1, 16));
		Inscripcion i3 = new Inscripcion(0,a3, 0, new Date(116, 2, 21));
		Inscripcion i4 = new Inscripcion(0,a4, 0, new Date(116, 0, 3));
		Inscripcion i5 = new Inscripcion(0,a5, 0, new Date(116, 0, 27));
		Inscripcion i6 = new Inscripcion(0,a1, 0, new Date(116, 5, 1));
		
		ArrayList<Inscripcion> participantes = new ArrayList<Inscripcion>();
		
		participantes.add(i1);
		participantes.add(i2);
		participantes.add(i3);
		participantes.add(i4);
		participantes.add(i5);
		participantes.add(i6);
		
		Iterator<Inscripcion> itParticipantes=participantes.iterator();
		
        while (itParticipantes.hasNext()) {
            Inscripcion elementoLista=(Inscripcion)itParticipantes.next();
            System.out.println(elementoLista.getAtleta().toString() +"\n "+elementoLista.getDorsal()+" "+elementoLista.getEstado() + " " + elementoLista.getFechaInscripcion());    
        }
		
		Collections.sort(participantes);
		
		System.out.println("\n\nOrdenados: \n");
		
		Iterator<Inscripcion> itParticipantes2=participantes.iterator();
		
        while (itParticipantes2.hasNext()) {
            Inscripcion elementoLista=(Inscripcion)itParticipantes2.next();
            System.out.println(elementoLista.getAtleta().toString() +"\n "+elementoLista.getDorsal()+" "+elementoLista.getEstado() + " " + elementoLista.getFechaInscripcion());    
        }
	}

}
