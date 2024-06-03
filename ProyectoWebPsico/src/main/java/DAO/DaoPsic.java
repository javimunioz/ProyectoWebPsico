package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Usuario;

public class DaoPsic {
	public static Connection con = null;

	public DaoPsic() throws SQLException {
		this.con = DBconexion.getConexion();
	}

	public ArrayList<Usuario> psicologos() throws SQLException {

		String sql = "SELECT idusuario,nombre,apellidos,correo,telefono,foto FROM usuarios WHERE idrol= 'psicologo'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<Usuario> psiclist = new ArrayList<>();
		while (rs.next()) {
			if (psiclist != null) {

			}
			psiclist.add(new Usuario(rs.getInt("idusuario"),rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("correo"), rs.getString("telefono"), rs.getString("foto")));
		}

		return psiclist;

	}
}
