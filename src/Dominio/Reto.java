package Dominio;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class Reto {

	private int number;
	private String name;	
	private Date fecha_inicio;
	private Date fecha_fin;
	private double distanciaObjetivo;	
	private String deporte;
	
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	
	public void setFecha_inicio(Date date) {
		this.fecha_inicio = date;
	}
	
	public Date getFecha_fin() {
		return fecha_fin;
	}
	
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
	
	public double getDistanciaObjetivo() {
		return distanciaObjetivo;
	}
	
	public void setDistanciaObjetivo(double d) {
		this.distanciaObjetivo = d;
	}
	
	public String getDeporte() {
		return deporte;
	}
	
	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	@Override
	public String toString() { 

		StringBuffer result = new StringBuffer();
		
		result.append(this.number);
		result.append(" # '");
		result.append(this.name);
		result.append("' #  ");
		result.append(this.fecha_inicio);
		result.append("' #  ");
		result.append(this.fecha_fin);
		result.append("' #  ");
		result.append(this.distanciaObjetivo);
		result.append("' #  ");
		result.append(this.deporte);
		result.append("' #  ");
		
		
		return result.toString();		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.number == ((Reto)obj).number;
		}
		
		return false;
	}
	
}