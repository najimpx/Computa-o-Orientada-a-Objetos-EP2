import java.io.Serializable;

public class Sala implements Serializable{
	private String nome;
	private String local;
	private String observacoes; 
	private int capacidade;
	
	public Sala(String Snome, String Slocal, String Sobservacoes, int Scapacidade) {
		this.nome = Snome;
		this.local = Slocal;
		this.observacoes = Sobservacoes;
		this.capacidade = Scapacidade;
	}
	
    public void setNome(String Snome) {
        this.nome = Snome;
    }
 
    public String getNome() {
        return this.nome;
    }
 
    public void setObservacoes(String Sobservacoes) {
        this.observacoes = Sobservacoes;
    }
 
    public String getObservacoes() {
        return this.observacoes;
    }
 
    public void setLocal(String Slocal) {
        this.local = Slocal;
    }
 
    public String getLocal() {
        return this.local;
    }
    
    public void setCapacidade(int Scapacidade) {
        this.capacidade = Scapacidade;
    }
 
    public int getCapacidade() {
        return this.capacidade;
    }
    
    public String toString() {
        return new StringBuffer("nome ").append(this.nome)
                .append("local  ").append(this.local).append("observacoes : ").append(this.observacoes).append("capacidade ").append(this.capacidade).toString();
    }
    
}
