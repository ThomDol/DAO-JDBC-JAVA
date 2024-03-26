package cda.tom.model;

import cda.tom.service.ArticleService;
import cda.tom.service.ContinentService;
import cda.tom.service.CouleurService;
import cda.tom.service.FabricantService;
import cda.tom.service.Factory;
import cda.tom.service.MarqueService;
import cda.tom.service.PaysService;
import cda.tom.service.TicketService;
import cda.tom.service.TypeBiereService;
import cda.tom.service.VendreService;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ContinentService continentService = new ContinentService();
		PaysService paysService= new PaysService();
		TicketService ticketService = new TicketService();
		CouleurService couleurService = new CouleurService();
		FabricantService fabricantService = new FabricantService();
		MarqueService marqueService = new MarqueService();
		TypeBiereService typeBiereService = new TypeBiereService();
		ArticleService articleService = new ArticleService();
		VendreService vendreService = new VendreService();
		
		
	
		Ticket ticket = new Ticket(2025,"2024-03-19","15:13:13");
		ticketService.save(ticket);
		
	
	

	
	
	
		
	
		
	
	
	
	
	
	
		
		
		
		
		
		

			
			

				
			
			
			}
		

		

	

}
