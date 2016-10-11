package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logica.Evento;

public class ConexionBD {

	
	public static String login="uo238031";
	public static String password = "";
	public static String url = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	//public static String url ="jdbc:hsqldb:hsql://localhost/labdb";
	
	private Connection conectar(){
		try {
			if (DriverManager.getDriver(url) == null){
				if (url.contains("oracle"))
					DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			}else
					DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
		login="SA";
		password= "";
		return DriverManager.getConnection(url, login, password);
		
		} catch (SQLException e) {
			System.out.println("No se ha podido conectar");
			e.printStackTrace();
			return null;
		}
	
	}


	public void cargarEventos(ArrayList<Evento> ev){
		Connection con =conectar();
		Statement st;
		try {
		//Eventos
		st = con.createStatement();
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM EVENTOS");
		ResultSet rs = st.executeQuery(query.toString());
		while(rs.next()){
			int id = rs.getInt("EV_ID");
			String nombre =rs.getString("EV_NOMBRE");
			String tipo =rs.getString("EV_TIPO");
			double distancia =rs.getDouble("EV_DISTANCIA");
			double precio =rs.getDouble("EV_PRECIO");
			Date fecha_comienzo =rs.getDate("EV_FECHA_COMIENZO");
			Date fecha_fin_insc=rs.getDate("EV_FECHA_FIN_INSC");
			ev.add(new Evento(id, nombre, tipo, precio, distancia,fecha_comienzo,fecha_fin_insc));
		}
		rs.close();
		st.close();
		
		//Atletas
		st = con.createStatement();
		StringBuilder query2 = new StringBuilder();
		query.append("SELECT * FROM ATLETAS");
		ResultSet rs2 = st.executeQuery(query.toString());
		while(rs.next()){
			int id = rs.getInt("EV_ID");
			String nombre =rs.getString("EV_NOMBRE");
			String tipo =rs.getString("EV_TIPO");
			double distancia =rs.getDouble("EV_DISTANCIA");
			double precio =rs.getDouble("EV_PRECIO");
			Date fecha_comienzo =rs.getDate("EV_FECHA_COMIENZO");
			Date fecha_fin_insc=rs.getDate("EV_FECHA_FIN_INSC");
			ev.add(new Evento(id, nombre, tipo, precio, distancia,fecha_comienzo,fecha_fin_insc));
		}
		rs.close();
		st.close();
		
		con.close();
		} catch (SQLException e) {
			System.out.println("Error al cargar datos de la BD.");
			e.printStackTrace();
		}
	}
}
	

