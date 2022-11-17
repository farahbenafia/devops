package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Date;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.mysql.cj.log.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FournisseurServiceImplTest {
	@Autowired
	IFournisseurService fs;
	@Autowired
	ISecteurActiviteService sas;
	
	private Fournisseur initF()
	{
	    Date date = new Date(0);  
		DetailFournisseur detailFournisseur=new DetailFournisseur(null, "fournisseur@gmail.com",date,"Tunis","RandomMat", null);
		Fournisseur f = new Fournisseur(null,"RandomCode","FournisseurTest",CategorieFournisseur.ORDINAIRE,null,null,detailFournisseur);
		return f;
	}

	@Test
	public void testAddFournisseur() {
	//	List<Stock> stocks = stockService.retrieveAllStocks();
	//	int expected=stocks.size();

		Fournisseur f=initF();
		Fournisseur savedFournisseur= fs.addFournisseur(f);
		
	//	assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedFournisseur.getLibelle());
		fs.deleteFournisseur(savedFournisseur.getIdFournisseur());
		
	} 
	
	@Test
	public void testDeleteFournissuer() {
		Fournisseur f=initF();

		Fournisseur savedFournisseur= fs.addFournisseur(f);
		fs.deleteFournisseur(savedFournisseur.getIdFournisseur());
		
		assertNull(fs.retrieveFournisseur(savedFournisseur.getIdFournisseur()));
	}
	@Test
	public void testupdateFournisseur() {
		Fournisseur f=initF();

		Fournisseur savedFournisseur= fs.addFournisseur(f);
		Fournisseur  updatedFournisseur=fs.updateFournisseur(savedFournisseur);
		
		assertEquals(savedFournisseur,updatedFournisseur);

	}

}