package jana60.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jana60.model.Ingredienti;
import jana60.repository.IngredientiRepository;


@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {
	
	 @Autowired
	  private IngredientiRepository repo;

	 
	 @GetMapping
	  public String ingredientiList(Model model)
	 {
	    model.addAttribute("ingredinti", repo.findAllByOrderByName());
	    model.addAttribute("newIngredienti", new Ingredienti());
	    return "/ingredienti/list";
	 }
	 
	 @PostMapping("/save")
	  public String saveCategory(@Valid @ModelAttribute("newCategory") Ingredienti formIngredienti,
	      BindingResult br, Model model) 
	 {
	    if (br.hasErrors()) 
	    {
	      // ricarico la pagina
	      model.addAttribute("categories", repo.findAllByOrderByName());
	      return "category/list";
	 
	    }else {
		      // salvo la category
		      repo.save(formIngredienti);
		      return "redirect:/ingredienti";
	    	
	    }
	 }
}