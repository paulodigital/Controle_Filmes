package br.edu.faculdadedelta.filme.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.filme.dao.SeriesDAO;
import br.edu.faculdadedelta.filme.modelo.Genero;
import br.edu.faculdadedelta.filme.modelo.Series;
import br.edu.faculdadedelta.filme.modelo.Status;
import br.edu.faculdadedelta.filme.util.FacesUtil;

@ManagedBean
@SessionScoped
public class SeriesController {
	
	private Series series = new Series();
	private SeriesDAO dao = new SeriesDAO();
	private Genero generoSelecionado = new Genero();
	private Status statusSelecionado = new Status();
	
	private static final String PAGINA_CADASTRO_SERIE = "cadastroSerie.xhtml";
	private static final String PAGINA_LISTA_SERIE = "listaSerie.xhtml";	 
	
	public Series getSeries() {
		return series;
	}
	public void setSeries(Series series) {
		this.series = series;
	}
	public Genero getGeneroSelecionado() {
		return generoSelecionado;
	}
	public void setGeneroSelecionado(Genero generoSelecionado) {
		this.generoSelecionado = generoSelecionado;
	}
	public Status getStatusSelecionado() {
		return statusSelecionado;
	}
	public void setStatusSelecionado(Status statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public void limparCampos() {
		series = new Series();
		generoSelecionado = new Genero();
		statusSelecionado = new Status();
	}
	
	public String salvar() {
		try {
			if (series.getIdSeries() == null) {
				series.setGenero(generoSelecionado);
				series.setStatus(statusSelecionado);
				dao.incluir(series);
				FacesUtil.exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				series.setGenero(generoSelecionado);
				series.setStatus(statusSelecionado);
				dao.alterar(series);
				FacesUtil.exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				FacesUtil.exibirMensagem("Erro ao realizar a operação!" + e.getMessage());
			}
			return PAGINA_CADASTRO_SERIE;
		}
	
	public String excluir() {
		try {
			dao.excluir(series);
			FacesUtil.exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação!");
		}
		return PAGINA_LISTA_SERIE;
	}
	
	public String editar() {
		generoSelecionado = series.getGenero();
		statusSelecionado = series.getStatus();
		return PAGINA_CADASTRO_SERIE;
	}
	
	public List<Series> getLista() {
		List<Series> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return listaRetorno;
	}

}
