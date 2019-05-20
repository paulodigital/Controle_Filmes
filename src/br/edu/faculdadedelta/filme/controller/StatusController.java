package br.edu.faculdadedelta.filme.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.filme.dao.StatusDAO;
import br.edu.faculdadedelta.filme.modelo.Status;
import br.edu.faculdadedelta.filme.util.FacesUtil;

@ManagedBean
@SessionScoped
public class StatusController {
	
	private Status status = new Status();
	private StatusDAO dao = new StatusDAO();
	
	private static final String PAGINA_CADASTRO_STATUS = "cadastroStatus.xhtml";
	private static final String PAGINA_LISTA_STATUS = "listaStatus.xhtml";	 
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void limparCampos() {
		status = new Status();
	}
	
	public String salvar() {
		try {
			//INCLUIR
			if (status.getIdStatus() == null) {
			dao.incluir(status);
			FacesUtil.exibirMensagem("Inclus�o realizada com sucesso!");
			limparCampos();
		} else {
			dao.alterar(status);
			FacesUtil.exibirMensagem("Altera��o realizada com sucesso!");
			limparCampos();
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.getStackTrace();
		FacesUtil.exibirMensagem("Erro ao realizar a opera��o!");
	}
	return PAGINA_CADASTRO_STATUS;
}
	
	public String excluir() {
		try {
			dao.excluir(status);
			FacesUtil.exibirMensagem("Exclus�o realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a opera��o!" + e.getMessage());
		}
		return PAGINA_LISTA_STATUS;
	}
	
	public String editar() {
		return PAGINA_CADASTRO_STATUS;
	}
	
	public List<Status> getLista() {
		List<Status> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a opera��o. " + e.getMessage());
			
		}
		return listaRetorno;
		
	}

}
