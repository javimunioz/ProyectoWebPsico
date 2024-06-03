package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

import Modelo.Cita;
import Modelo.Usuario;

public class DaoCitas {
	public static Connection con = null;

	public DaoCitas() throws SQLException {
		this.con = DBconexion.getConexion();

	}

	public void insertarcita(Cita c) throws SQLException {
		String sql = "INSERT INTO cita (fecha,hora,idpsicologo,idcliente) VALUES (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, (Date) c.getFecha());
		ps.setTime(2, c.getHora());
		ps.setInt(3, c.getIdpsicologo());
		ps.setInt(4, c.getIdcliente());
		int filas = ps.executeUpdate();
		ps.close();
	}

	// obtener idpsicologo del idusuario en gestion cliente
	public int obteneridpsicologo(int idusuario) throws SQLException {
		String sql = "SELECT * FROM psicologo WHERE idusuario=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, idusuario);

		ResultSet rs = ps.executeQuery();
		rs.next();
		int idpsic = rs.getInt("idpsicologo");

		System.out.println(idpsic);
		return idpsic;
	}
	// obtener idcliente del idusuario en gestion cliente

	public int obteneridcliente(int idusuario) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE idusuario=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idusuario);

		ResultSet rs = ps.executeQuery();
		rs.next();

		int idcli = rs.getInt("idcliente");
		return idcli;
	}

	// Listar las citas de un psicologo

	public ArrayList<Cita> listarcitas(int idpsicologo) throws SQLException {

		String sql = "SELECT c.idcita, c.idcliente, c.idpsicologo, c.fecha, c.hora, u.nombre FROM cita c, cliente cl, usuarios u WHERE c.idpsicologo=? AND c.idcliente=cl.idcliente AND cl.idusuario=u.idusuario";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idpsicologo);
		ResultSet rs = ps.executeQuery();

		ArrayList<Cita> lc = new ArrayList<Cita>();
		while (rs.next()) {

			lc.add(new Cita(rs.getInt("idcita"), rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("idpsicologo"),
					rs.getInt("idcliente"), rs.getString("u.nombre")));

			System.out.println(lc);

		}
		return lc;
	}

	public ArrayList<Cita> listacitas(int idcliente) throws SQLException {
		String sql = "SELECT c.idcita, c.fecha, c.hora, c.idpsicologo, c.idcliente, u.nombre FROM cita c, psicologo p, usuarios u WHERE c.idcliente=? AND c.idpsicologo=p.idpsicologo AND p.idusuario=u.idusuario";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idcliente);
		ResultSet rs = ps.executeQuery();
		ArrayList<Cita> lc = new ArrayList<Cita>();
		while (rs.next()) {

			lc.add(new Cita(rs.getInt("idcita"), rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("idpsicologo"),
					rs.getInt("idcliente"), rs.getString("u.nombre")));

			System.out.println(lc);

		}
		return lc;

	}
}
