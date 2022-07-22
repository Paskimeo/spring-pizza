package jana60.repository;

import org.springframework.data.repository.CrudRepository;
import jana60.model.pizza;


public interface pizzaRepository extends CrudRepository<pizza, Integer>{
	
	//ti dice il quantitativo di copie gia presenti con quel nome, l'integer iniziale è il dato che torna
	public Integer countByNome(String nome);

}
