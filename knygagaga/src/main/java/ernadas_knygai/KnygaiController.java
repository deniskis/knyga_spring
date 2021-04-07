package ernadas_knygai;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class KnygaiController {
	
	@Autowired
	private KnygasRepository knygas_repository;	

	
	@RequestMapping(path="/knygai", method={ RequestMethod.GET, RequestMethod.POST })
    public String knygai(@RequestParam(name="id", required=false, defaultValue="0") Integer id 
			, @RequestParam(name="pav", required=false, defaultValue="") String pav
			, @RequestParam(name="author", required=false, defaultValue="") String author
			, @RequestParam(name="pages", required=false, defaultValue="0") String pages
			, @RequestParam(name="genre", required=false, defaultValue="") String genre
			, @RequestParam(name="salies_kodas", required=false, defaultValue="") String salies_kodas
			, @RequestParam(name="saugoti", required=false, defaultValue="nesaugoti") String saugoti			
			, Model model) {
		
		String res = "Neatlikta";
		
		Knygas knygas = new Knygas();
		
		if ( saugoti.equals( "saugoti" ) ) {
		
			if (id > 0) {
				
				Optional <Knygas> found = knygas_repository.findById( id );
			
				// variantas trynimuiui
				// uzsakymaiRepository.deleteById(id);
			
				if ( found.isPresent() ) {
				
				   knygas = found.get();
				   knygas.setId ( id );
				   
				} else {
					
					res = "Klaida įrašas galėjoi būti pašalintas";
				}
			}	
			
		    knygas.setpav( pav );
		    knygas.setauthor(author);
		    knygas.setpages ( Integer.parseInt ( pages ) );
		    knygas.setgenre( genre );
		    knygas.setSalies_kodas(salies_kodas);
		  
		    knygas_repository.save ( knygas );
		    res = "Saugota";
		    
		}
    	model.addAttribute("knygai", knygas_repository.findAll() );
    	model.addAttribute("res", res );		
   
		return "knygai";	
	}	
	
	@RequestMapping(path="/knygas")	
	public @ResponseBody Knygas miestoDuom(@RequestParam(name="id", required=true, defaultValue="0") Integer id ) throws IOException {
		
		String res;
		
		Knygas knygas = new Knygas();
		
		if (id > 0) {
			
			Optional <Knygas> found = knygas_repository.findById( id );
		
			// variantas trynimuiui
			// uzsakymaiRepository.deleteById(id);
		
			if ( found.isPresent() ) {
			
			   knygas = found.get();
			   knygas.setId ( id );
			   
			} else {
				
				res = "Klaida įrašas galėjoi būti pašalintas";
			}
		}		
		
		return knygas;
	}
	
	@GetMapping(path="/salinti-knyga") // Map ONLY GET Requests
	public @ResponseBody String Trinti (@RequestParam Integer id 
			) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Knygas knygas = new Knygas();
		Optional <Knygas> found = knygas_repository.findById( id );
		
		String res = "Not done";
		
		if ( found.isPresent() ) {
			
			knygas = found.get();
			knygas_repository.deleteById(id);
			   res = "Deleted";
		}		
		return res;
	}	
}
