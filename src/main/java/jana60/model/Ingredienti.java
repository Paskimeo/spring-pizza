package jana60.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;


@Entity
public class Ingredienti {

	
	
@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


@NotEmpty(message = "Category name cannot be empty")
@Column(nullable = false)
private String name;


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


@ManyToMany(mappedBy = "ingredienti")
private List<pizza> pizza;


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}





public List<pizza> getPizza() {
	return pizza;
}


public void setPizza(List<pizza> pizza) {
	this.pizza = pizza;
}

public int getNumberOfIngredienti() {
    return pizza.size();
}
}
