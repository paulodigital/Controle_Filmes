package br.edu.faculdadedelta.filme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.filme.modelo.Genero;
import br.edu.faculdadedelta.filme.util.Conexao;

public class GeneroDAO {

	public void incluir(Genero genero) 
			throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO genero (descricao) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, genero.getDescricaoGenero().trim());

		ps.executeUpdate();
		Conexao.fecharConexao(ps, conn, null);
	}

	public void alterar(Genero genero) 
			throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE genero SET descricao = ? WHERE id_genero = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, genero.getDescricaoGenero().trim());
		ps.setLong(2, genero.getIdGenero());
		
		ps.executeUpdate();

		Conexao.fecharConexao(ps, conn, null);
	}

	public void excluir(Genero genero) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM genero WHERE id_genero = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, genero.getIdGenero());

		ps.executeUpdate();

		Conexao.fecharConexao(ps, conn, null);
	}

	public Genero pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_genero, descricao FROM genero WHERE id_genero = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();
		Genero retorno = new Genero();
		if (rs.next()) {
			retorno = popularGenero(rs);
		}
		Conexao.fecharConexao(ps, conn, rs);

		return retorno;
	}

	public List<Genero> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_genero, descricao FROM genero";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		List<Genero> listaRetorno = new ArrayList<>();

		while (rs.next()) {
			listaRetorno.add(popularGenero(rs));
		}

		Conexao.fecharConexao(ps, conn, rs);

		return listaRetorno;
	}

	private Genero popularGenero(ResultSet rs) throws SQLException {

		Genero genero = new Genero();
		genero.setIdGenero(rs.getLong("id_genero"));
		genero.setDescricaoGenero(rs.getString("descricao"));

		return genero;
	}
}
