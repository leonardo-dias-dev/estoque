package veterinaria.estoque.model.enums;

public enum UnidadeMedida {
	UNID("UNID"),
	PCT("PCT"),
	TB("TB"),
	ML("ML"),
	GR("GR"),
	FR("FR"),
	AMP("AMP"),
	LT("LT");
	
	private String nome;

	private UnidadeMedida(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
}
