package br.edu.faculdadedelta.filme.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.filme.dao.GeneroDAO;
import br.edu.faculdadedelta.filme.modelo.Genero;
import br.edu.faculdadedelta.filme.util.FacesUtil;


@ManagedBean
@SessionScoped
public class GeneroController {
	
	private Genero genero = new Genero();
	private GeneroDAO dao = new GeneroDAO();
	
	private static final String PAGINA_CADASTRO_GENERO = "cadastroGenero.xhtml";
	private static final String PAGINA_LISTA_GENERO = "listaGenero.xhtml";
		
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void limparCampos() {
		genero = new Genero();
	}
	
	public String salvar() {
		try {
			//INCLUIR
			if (genero.getIdGenero() == null) {
			dao.incluir(genero);
			FacesUtil.exibirMensagem("Inclusão realizada com sucesso!");
			limparCampos();
		} else {
			dao.alterar(genero);
			FacesUtil.exibirMensagem("Alteração realizada com sucesso!");
			limparCampos();
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.getStackTrace();
		FacesUtil.exibirMensagem("Erro ao realizar a operação!");
	}
	return PAGINA_CADASTRO_GENERO;
}
	
	public String excluir() {
		try {
			dao.excluir(genero);
			FacesUtil.exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação!" + e.getMessage());
		}
		return PAGINA_LISTA_GENERO;
	}
	
	public String editar() {
		return PAGINA_CADASTRO_GENERO;
	}
	
	public List<Genero> getLista() {
		List<Genero> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
			
		}
		return listaRetorno;
		
	}

}
