
import java.io.Serializable;
import java.time.LocalDateTime;


public class Reserva implements Serializable {
	
	private	Sala sala;
	private LocalDateTime inicio;
	private LocalDateTime fim;

	public void setSala(Sala Rsala) {
		this.sala = Rsala;
		return;
	}
	
	public Sala getSala() {
		return this.sala;
	}
	
	public void setInicio(LocalDateTime Rinicio) {
		this.inicio = Rinicio;
		return;
	}
	
	public LocalDateTime getInicio() {
		return this.inicio;
	}
	
	public void setFim(LocalDateTime Rfim) {
		this.fim = Rfim;
	}
	
	public LocalDateTime getFim() {
		return this.fim;
	}
	
    public String toString() {
        return new StringBuffer("sala ").append(this.sala)
                .append("inicio  ").append(this.inicio).append("fim : ").append(this.fim).toString();
    }
}
