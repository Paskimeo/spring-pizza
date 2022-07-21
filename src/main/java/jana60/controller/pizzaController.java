package jana60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.pizza;

import jana60.repository.pizzaRepository;

@Controller
@RequestMapping("/")
public class pizzaController {
	
	@Autowired 
	private pizzaRepository repo;

	@GetMapping
	public String index (Model model)
	{
		List<pizza> Listpizza = (List<pizza>) repo.findAll();
		
		model.addAttribute("Listpizza", Listpizza);
		return "index";
	}
}
