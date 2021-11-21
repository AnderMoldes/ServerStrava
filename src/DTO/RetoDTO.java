package DTO;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

//This class implements DTO pattern
public class RetoDTO implements Serializable {
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
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



	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
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



	public void setDistanciaObjetivo(double distanciaObjetivo) {
		this.distanciaObjetivo = distanciaObjetivo;
	}



	public String getDeporte() {
		return deporte;
	}



	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}



	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");
		NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault()); 

		StringBuffer result = new StringBuffer();
		
		result.append(this.number);
		result.append(" # '");
		result.append(this.name);
		result.append("' # Name: ");

		result.append(dateFormatter.format(this.fecha_inicio));
		result.append(" (");
		result.append("\t");
		result.append(dateFormatter.format(this.fecha_fin));
		result.append(" )");
		result.append(this.distanciaObjetivo);
		result.append(" Distance)");
		result.append(this.deporte);
		result.append("' # Sport: ");

		
		return result.toString();
	}
}