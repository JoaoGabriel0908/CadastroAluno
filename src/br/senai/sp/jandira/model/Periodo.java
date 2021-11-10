package br.senai.sp.jandira.model;

public enum Periodo {
	
	MANHA ("Manhã"),
	TARDE ("Tarde"),
	NOITE ("Noite"),
	SABADO ("Sábado"),
	ONLINE ("On-line");
	
	private String descricao;
	
	//CONSTRUTOR 
	//SEMPRE DEIXAR O CONSTRUTOR PRIVADO, POIS O ENUM É UMA LISTA FIXA 
	//E QUE AS OUTRAS CLASSES NÃO PODE ALTERA-LÁ
	private Periodo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
