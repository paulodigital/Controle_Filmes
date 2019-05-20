package br.edu.faculdadedelta.filme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.edu.faculdadedelta.filme.modelo.Series;

import br.edu.faculdadedelta.filme.util.Conexao;

public class SeriesDAO {
	
public void incluir(Series series) throws ClassNotFoundException, SQLException {
		
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO series (nome, comentario, nota_avaliacao, id_genero, id_status) VALUES (?,?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		
		ps.setString(1, series.getNome().trim());
		ps.setString(2, series.getComentario().trim());
		ps.setDouble(3, series.getNota_avaliacao());
		ps.setLong(4, series.getGenero().getIdGenero());
		ps.setLong(5, series.getStatus().getIdStatus());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
			
	}
	
	public void alterar (Series series) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE series "
				+ "SET nome = ?, comentario = ?, nota_avaliacao = ?, id_genero = ?, id_status = ? "
				+ "WHERE id_series = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
	
		
		ps.setString(1, series.getNome().trim());
		ps.setString(2, series.getComentario().trim());
		ps.setDouble(3, series.getNota_avaliacao());
		ps.setLong(4, series.getGenero().getIdGenero());
		ps.setLong(5, series.getStatus().getIdStatus());
		ps.setLong(6, series.getIdSeries());
				
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public void excluir(Series series) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM series WHERE id_series = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, series.getIdSeries());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public List<Series> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM series ";
			
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Series> listaRetorno = new ArrayList<>();
		while (rs.next()) {
			
			Series series = new Series();
			series.setIdSeries(rs.getLong("id_series"));
			series.setNome(rs.getString("nome").trim());
			series.setComentario(rs.getString("comentario").trim());
			series.setNota_avaliacao(rs.getDouble("nota_avaliacao"));
			
		GeneroDAO dao = new GeneroDAO();
		series.setGenero(dao.pesquisarPorId(rs.getLong("id_genero")));
		
		StatusDAO  dao1 = new StatusDAO();
		series.setStatus(dao1.pesquisarPorId(rs.getLong("id_status")));
			
			listaRetorno.add(series);

				}
		
		Conexao.fecharConexao(ps, conn, rs);
		
		return listaRetorno;

	}


}
