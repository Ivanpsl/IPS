package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logica.GestorCategorias;
import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;

import org.junit.Test;

import igu.Main;
import persistencia.GestorFicheros;
import utiles.ConversorFechas;

public class testPersistencia {

//	@Test
//	public void test() {
//		ArrayList<Evento> ev = new ArrayList<Evento>();
//		GestorFicheros gF = new GestorFicheros();
//		//gF.cargarEventos(ev);
//		System.out.println(ev.toString());
//	}
	
	@Test
	public void testFecha(){
		ConversorFechas f = new ConversorFechas();
		assertEquals(21,f.fechaNacimientoEdad("14/03/1995"));
		
		Atleta a = new Atleta("1587", "fgsf", "14/03/1995", 0);
		assertEquals(21, a.getEdad());
	}
	
//	@Test
//	public void testCategorias(){
//		Categoria[] categoriasDef = {
//			
//			new Categoria("Senior", 18, 35, Categoria.MASCULINO),
//			new Categoria("VeteranoA", 36, 40, Categoria.MASCULINO),
//			new Categoria("VeteranoB", 41, 45, Categoria.MASCULINO),
//
//			
//			new Categoria("Senior", 18, 35, Categoria.FEMENINO),
//			new Categoria("VeteranoA", 36, 40, Categoria.FEMENINO),	
//			new Categoria("VeteranoB", 41, 45, Categoria.FEMENINO),	
//
//		
//		};
//		ArrayList<Categoria> cate = new ArrayList<Categoria>();
//		for (int i = 0; i < categoriasDef.length; i++) {
//			cate.add(categoriasDef[i]);
//		}
//		
//		//assertTrue(GestorCategorias.comprobarRangoCategorias(cate));
//		
//		//assertTrue(GestorCategorias.comprobarSolapadosCategoria(cate));
//		//assertTrue(GestorCategorias.comprobarNombreRepetidoCategoria(cate));
//	}
	
//	@Test
//	public void testAsignarCategorias(){
//		Categoria[] categoriasDef = {
//				
//				new Categoria("Senior", 18, 35, Categoria.MASCULINO),
//				new Categoria("VeteranoA", 36, 40, Categoria.MASCULINO),
//				new Categoria("VeteranoB", 41, 45, Categoria.MASCULINO),
//
//				
//				new Categoria("Senior", 18, 35, Categoria.FEMENINO),
//				new Categoria("VeteranoA", 36, 40, Categoria.FEMENINO),	
//				new Categoria("VeteranoB", 41, 45, Categoria.FEMENINO),	
//
//			
//			};
//			ArrayList<Categoria> cate = new ArrayList<Categoria>();
//			for (int i = 0; i < categoriasDef.length; i++) {
//				cate.add(categoriasDef[i]);
//			}
//			
//		Inscripcion ins = new Inscripcion();
//		Atleta at = new Atleta("154","pepe",10,1);
//		ins.calcularCategoria(cate, at);
//		System.out.println(ins.categoria);
//		
//		
//	}

}
