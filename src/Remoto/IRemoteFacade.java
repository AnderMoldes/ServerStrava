package Remoto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import DTO.*;


//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	

	public long login(String email, String password) throws RemoteException;
	
	public long registro(String email, String name, Date fecha_nac, double peso, String contrasenya, String frec) throws RemoteException;
	
	public void logout(long token) throws RemoteException; 
	
	public List<SesionDTO> getSesion() throws RemoteException;
	
	public long crearReto(int number, String name, Date fecha_ini, Date fecha_fin, double distanciaObjetivo, String deporte) throws RemoteException;
	
	public List<RetoDTO> getRetos() throws RemoteException;
	
	public long crearSesion(int number, String titulo, String deporte, double distancia, Date fecha_ini, String hora_ini, double duracion) throws RemoteException;
	
	public long loginGoogle(String email, String password) throws RemoteException;
	
	public String comprobar() throws RemoteException;
}