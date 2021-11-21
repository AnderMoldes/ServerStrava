package DTO;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

import Dominio.Usuario;

//This class implements DTO pattern
public class SesionDTO implements Serializable {	
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;	
	private int number;
	private String titulo;	
	private String deporte;	
	private double distancia;	
	private Date fecha_inicio;	
	private String hora_inicio;
	private double duracion;
	private Usuario propietario;

	

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

	public void setHora_inicio(String string) {
		this.hora_inicio = string;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double d) {
		this.duracion = d;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	
	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");
		NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault()); 

		StringBuffer result = new StringBuffer();
		
		result.append(this.number);
		result.append(" # '");
		result.append(this.titulo);
		result.append("' # Titulo: ");
		result.append(dateFormatter.format(this.fecha_inicio));
		result.append(" (");
		result.append("\t");
		result.append(dateFormatter.format(this.hora_inicio));
		result.append(" )");
		result.append(this.distancia);
		result.append(" Distance)");
		result.append(this.deporte);
		result.append("' # Sport: ");
		result.append(this.duracion);
		result.append("' # Duracion: ");
		result.append(this.propietario);
		result.append("' # Propietario: ");
		
		return result.toString();		
	}
}