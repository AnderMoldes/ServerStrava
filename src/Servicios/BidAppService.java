package Servicios;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Dominio.Bid;
import Dominio.Reto;
import Dominio.Sesion;
import Dominio.Usuario;


//TODO: Implement Singleton Pattern
public class BidAppService {
	
	//TODO: remove when DAO Pattern is implemented
	private List<Sesion> sesion = new ArrayList<>();
	private List<Reto> retos = new ArrayList<>();
	
	public BidAppService() {
		//TODO: remove when DAO Pattern is implemented
		this.initilizeData();
	}
	
	//TODO: remove when DAO Pattern is implemented
	private void initilizeData() {
		//Create Users
		Usuario user0 = new Usuario();
		user0.setEmail("thomas.e2001@gmail.com");
		user0.setName("Thomas");
		user0.setContrasenya("$!9PhNz,");
		
		Usuario user1 = new Usuario();
		user1.setEmail("sample@gmail.com");
		user1.setName("buyer33");		
		user1.setContrasenya("hqc`}3Hb");
								
		//Create Categories
		Sesion sesion = new Sesion();
		sesion.setDeporte("Ciclismo");
		sesion.setDistancia(30);
		sesion.setDuracion(1.5);
		sesion.setFecha_inicio(new Date("2021-10-19"));
		sesion.setHora_inicio("9:00");
		sesion.setNumber(1);
		sesion.setPropietario(user1);
		sesion.setTitulo("Ciclismo 30 km");
		//Create Articles				
		Reto reto = new Reto();
		reto.setDeporte("Correr");
		reto.setDistanciaObjetivo(100);
		reto.setFecha_inicio(new Date("2021-10-19"));
		reto.setFecha_fin(new Date("2021-11-19"));
		reto.setName("100 km");
		reto.setNumber(1);
		//Add the Category to the local cache.
		this.sesion.add(sesion);
		//Add articles to local cahce
		this.retos.add(reto);
	}
	
	
	public List<Sesion> getSesion() {
		//TODO: Get all the categories using DAO Pattern		
		return this.sesion;
	}

	public List<Reto> getReto() {
		//TODO: Get articles of a category using DAO Pattern
		return this.retos;

	}

	public boolean makeBid(Usuario user, int number, float amount) {
		//TODO: Find the artile using DAO Pattern
		Reto article = null;
		
		for (Reto art : this.retos) {
			if (art.getNumber() == number) {
				article = art;
				break;
			}
		}

		if (article != null) {
			Bid newBid = new Bid();		
			newBid.setDate(Calendar.getInstance().getTime());
			newBid.setAmount(amount);
			newBid.setArticle(article);
			newBid.setUser(user);		
			article.addBid(newBid);
			user.addBid(newBid);

			//TODO: Save the new bin in the DB using DAO Pattern
			
			return true;
		} else {
			return false;
		}
	}
	
}