package Gateway;

import java.rmi.Naming;
import java.rmi.RemoteException;

import Login.ILogin;


public class LoginGateway {
	private static LoginGateway instance;
	private ILogin currencyConvService;
	
	private LoginGateway() {
		try {		
			String URL = "//127.0.0.1:1099/CurrencyExchange";
			this.currencyConvService = (ILogin) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println("# Error locating remote fa�ade: " + ex);
		}
	}
	
	public static LoginGateway getInstance() {
		if(instance == null) {
			instance = new LoginGateway();
		}
		
		return instance;
	}
	
	public long login(String email, String password) {
		System.out.println("Haciendo login...");
		try {
			return this.currencyConvService.loginGoogle(email, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public String comprobar() {
		System.out.println("   - Combrobando...");
		
		try {
			return this.currencyConvService.combrobarLogin();
		} catch (Exception ex) {
			System.out.println("   $ Error : " + ex.getMessage());
			return "";
		}		
	}

}