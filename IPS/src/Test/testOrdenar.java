package Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import logica.GestorClasificaciones;
import logica.Vistas.Atleta;
import logica.Vistas.Inscripcion;

import org.junit.Test;

public class testOrdenar {

//	@SuppressWarnings("deprecation")
//	@Test
//	public void test() {
//		Atleta a1 = new Atleta("1", "a1", "mayor", 20, 0);
//		Atleta a2 = new Atleta("2", "a2", "mayor", 21, 0);
//		Atleta a3 = new Atleta("3", "a3", "menor", 15, 0);
//		Atleta a4 = new Atleta("4", "a4", "menor", 16, 0);
//		Atleta a5 = new Atleta("5", "a5", "mayor", 24, 0);
//		
//		Inscripcion i1 = new Inscripcion(0,a1, 0, new Date(116, 2, 24));
//		Inscripcion i2 = new Inscripcion(0,a2, 0, new Date(116, 1, 16));
//		Inscripcion i3 = new Inscripcion(0,a3, 0, new Date(116, 2, 21));
//		Inscripcion i4 = new Inscripcion(0,a4, 0, new Date(116, 0, 3));
//		Inscripcion i5 = new Inscripcion(0,a5, 0, new Date(116, 0, 27));
//		Inscripcion i6 = new Inscripcion(0,a1, 0, new Date(116, 5, 1));
//		
//		ArrayList<Inscripcion> participantes = new ArrayList<Inscripcion>();
//		
//		participantes.add(i1);
//		participantes.add(i2);
//		participantes.add(i3);
//		participantes.add(i4);
//		participantes.add(i5);
//		participantes.add(i6);
//		
//		Iterator<Inscripcion> itParticipantes=participantes.iterator();
//		
//        while (itParticipantes.hasNext()) {
//            Inscripcion elementoLista=(Inscripcion)itParticipantes.next();
//            System.out.println(elementoLista.getAtleta().toString() +"\n "+elementoLista.getDorsal()+" "+elementoLista.getEstado() + " " + elementoLista.getFechaInscripcion());    
//        }
//		
//		Collections.sort(participantes);
//		
//		System.out.println("\n\nOrdenados: \n");
//		
//		Iterator<Inscripcion> itParticipantes2=participantes.iterator();
//		
//        while (itParticipantes2.hasNext()) {
//            Inscripcion elementoLista=(Inscripcion)itParticipantes2.next();
//            System.out.println(elementoLista.getAtleta().toString() +"\n "+elementoLista.getDorsal()+" "+elementoLista.getEstado() + " " + elementoLista.getFechaInscripcion());    
//        }
//	}
//	@Test
//	public void testOrdenarClasificaciones(){
//		
//		Atleta a1 = new Atleta("1", "a1", "mayor", 20, 0);
//		Atleta a2 = new Atleta("2", "a2", "mayor", 21, 1);
//		
//		Inscripcion h1 = new Inscripcion(0,a1, 0, new Date(116, 2, 24));
//		h1.setTiempoSegundos(1);
//		Inscripcion m1 = new Inscripcion(0,a2, 1, new Date(116, 1, 16));
//		m1.setTiempoSegundos(2);
//		Inscripcion h2 = new Inscripcion(0,a1, 2, new Date(116, 2, 21));
//		h2.setTiempoSegundos(3);
//		Inscripcion m2 = new Inscripcion(0,a2, 3, new Date(116, 0, 3));
//		m2.setTiempoSegundos(4);
//		
//		ArrayList<Inscripcion> corredores = new ArrayList<Inscripcion>();
//		corredores.add(h1);
//		corredores.add(h2);
//		corredores.add(m1);
//		corredores.add(m2);
//
//		Clasificacion clasificaciones = new Clasificacion(corredores);
//		System.out.println(clasificaciones.toString());
//		ArrayList<Inscripcion> abs = clasificaciones.obtenerClasificacion("absoluta");
//		System.out.println("Clasificacion absoluta: \n");
//		for(Inscripcion ins: abs){
//			System.out.println(ins.getTiempoSegundos()+ " " + ins.getAtleta().getSexo());
//		}
//
//		ArrayList<Inscripcion> masc = clasificaciones.obtenerClasificacion("masculina");
//		System.out.println("Clasificacion masculina: \n");
//		for(Inscripcion ins: masc){
//			System.out.println(ins.getTiempoSegundos()+ " " + ins.getAtleta().getSexo());
//		}
//		ArrayList<Inscripcion> fem = clasificaciones.obtenerClasificacion("femenina");
//		System.out.println("Clasificacion femenina: \n");
//		for(Inscripcion ins: fem){
//			System.out.println(ins.getTiempoSegundos()+ " " + ins.getAtleta().getSexo());
//		}
//	}
}
