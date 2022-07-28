package jana60.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pizza")
public class pizza 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	
	@ManyToMany
	private List<Ingredienti> ingredienti;
	
	
	

	@NotEmpty(message = "Non esistono pizze senza nome")
	@Column(nullable = false)
	private String nome;
	
	
	@Lob
	private String descrizione; 
	
	
	@NotNull
	@DecimalMin("0.00")
	@DecimalMax("100.00")
	private Double prezzo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public List<Ingredienti> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingredienti> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	
}	
