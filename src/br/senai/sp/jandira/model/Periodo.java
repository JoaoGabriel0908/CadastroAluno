package br.senai.sp.jandira.model;

public enum Periodo {
	
	MANHA ("Manh�"),
	TARDE ("Tarde"),
	NOITE ("Noite"),
	SABADO ("S�bado"),
	ONLINE ("On-line");
	
	private String descricao;
	
	//CONSTRUTOR 
	//SEMPRE DEIXAR O CONSTRUTOR PRIVADO, POIS O ENUM � UMA LISTA FIXA 
	//E QUE AS OUTRAS CLASSES N�O PODE ALTERA-L�
	private Periodo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
