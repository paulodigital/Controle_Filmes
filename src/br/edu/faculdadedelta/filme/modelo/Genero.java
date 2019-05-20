package br.edu.faculdadedelta.filme.modelo;

public class Genero {

	private Long idGenero;
	private String descricaoGenero;
	public Genero() {
		super();
	}
	public Genero(Long idGenero, String descricaoGenero) {
		super();
		this.idGenero = idGenero;
		this.descricaoGenero = descricaoGenero;
	}
	public Long getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}
	public String getDescricaoGenero() {
		return descricaoGenero;
	}
	public void setDescricaoGenero(String descricaoGenero) {
		this.descricaoGenero = descricaoGenero;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGenero == null) ? 0 : idGenero.hashCode());
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
		Genero other = (Genero) obj;
		if (idGenero == null) {
			if (other.idGenero != null)
				return false;
		} else if (!idGenero.equals(other.idGenero))
			return false;
		return true;
	}
	
	
}
