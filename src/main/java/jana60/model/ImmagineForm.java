package jana60.model;


import org.springframework.web.multipart.MultipartFile;

public class ImmagineForm 
{


	private Integer id;
	
	private pizza pizza;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public pizza getPizza() {
		return pizza;
	}

	public void setPizza(pizza pizza) {
		this.pizza = pizza;
	}

	public MultipartFile getContentMultipart() {
		return contentMultipart;
	}

	public void setContentMultipart(MultipartFile contentMultipart) {
		this.contentMultipart = contentMultipart;
	}

	private MultipartFile contentMultipart;
	
	
	
	
}
