package br.edu.faculdadedelta.filme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.filme.modelo.Status;
import br.edu.faculdadedelta.filme.util.Conexao;

public class StatusDAO {
	
public void incluir (Status status) throws ClassNotFoundException, SQLException {
		
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO status (descricao) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		
		ps.setString(1, status.getDescricaoStatus().trim());
		
		ps.executeUpdate();
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public void alterar (Status status) throws ClassNotFoundException, SQLException {
		
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE status SET descricao = ? WHERE id_status = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, status.getDescricaoStatus().trim());
		ps.setLong(2, status.getIdStatus());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public void excluir(Status status) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM status WHERE id_status = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, status.getIdStatus());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public Status pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_status, descricao FROM status WHERE id_status = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		Status retorno = new Status();
		if (rs.next()) {
			retorno = popularStatus(rs);
		}
		Conexao.fecharConexao(ps, conn, rs);
		
		return retorno;
}
	
	public List<Status> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_status, descricao FROM status";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<Status> listaRetorno = new ArrayList<>();
		
		while(rs.next()) {
			listaRetorno.add(popularStatus(rs));
		}
		
		Conexao.fecharConexao(ps, conn, rs);
		
		return listaRetorno;
	}
	
	private Status popularStatus(ResultSet rs) throws SQLException {
		
		Status status = new Status();
		status.setIdStatus(rs.getLong("id_Status"));
		status.setDescricaoStatus(rs.getString("descricao"));
	
		return status;
	}
}
