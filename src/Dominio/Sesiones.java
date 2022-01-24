package Dominio;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Join;

@PersistenceCapable
public class Sesiones {
	private int number;
	private String titulo;	
	private String deporte;	
	private double distancia;	
	private Date fecha_inicio;	
	private String hora_inicio;
	private double duracion;
	private Usuarios propietario;
	@Join
	private List<Sesiones> sesiones;
	
    
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDeporte() {
		return deporte;
	}
	
	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}
	
	public double getDistancia() {
		return distancia;
	}
	
	public void setDistancia(double d) {
		this.distancia = d;
	}
	
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	
	public void setFecha_inicio(Date date) {
		this.fecha_inicio = date;
	}
	
	public String getHora_inicio() {
		return hora_inicio;
	}
	
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	
	public double getDuracion() {
		return duracion;
	}
	
	public void setDuracion(double d) {
		this.duracion = d;
	}
	
	public Usuarios getPropietario() {
		return propietario;
	}
	
	public void setPropietario(Usuarios propietario) {
		this.propietario = propietario;
	}
	public void removePropietario() {
		sesiones.remove(propietario);
	}
	public List<Sesiones> getSesion() { 
		return sesiones;
	}
	
	@Override
	public String toString() { 

		StringBuffer result = new StringBuffer();
		
		result.append(this.number);
		result.append(" # '");
		result.append(this.titulo);
		result.append("' #  ");
		result.append(this.deporte);
		result.append("' #  ");
		result.append(this.distancia);
		result.append("' #  ");
		result.append(this.fecha_inicio);
		result.append("' #  ");
		result.append(this.hora_inicio);
		result.append("' #  ");
		result.append(this.duracion);
		result.append("' #  ");
		result.append(this.propietario);
		result.append("' #  ");
		result.append(this.number);
		result.append(" # '");
		
		
		return result.toString();		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.number == ((Sesiones)obj).number;
		}
		
		return false;
	}
	
}