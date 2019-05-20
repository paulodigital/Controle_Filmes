package br.edu.faculdadedelta.filme.modelo;

public class Series {
	
	private Long idSeries;
	private String nome;
	private String comentario;
	private Double nota_avaliacao;
	private Status status;
	private Genero genero;
	public Series() {
		super();
	}
	public Series(Long idSeries, String nome, String comentario, Double nota_avaliacao, Status status, Genero genero) {
		super();
		this.idSeries = idSeries;
		this.nome = nome;
		this.comentario = comentario;
		this.nota_avaliacao = nota_avaliacao;
		this.status = status;
		this.genero = genero;
	}
	public Long getIdSeries() {
		return idSeries;
	}
	public void setIdSeries(Long idSeries) {
		this.idSeries = idSeries;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Double getNota_avaliacao() {
		return nota_avaliacao;
	}
	public void setNota_avaliacao(Double nota_avaliacao) {
		this.nota_avaliacao = nota_avaliacao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSeries == null) ? 0 : idSeries.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Series other = (Series) obj;
		if (idSeries == null) {
			if (other.idSeries != null)
				return false;
		} else if (!idSeries.equals(other.idSeries))
			return false;
		return true;
	}
	
	

}
