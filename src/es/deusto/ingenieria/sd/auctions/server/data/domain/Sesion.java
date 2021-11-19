package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Sesion {

	private int number;
	private String titulo;	
	private String deporte;	
	private Float distancia;	
	private LocalDate fecha_inicio;	
	private LocalTime hora_inicio;
	private Float duracion;
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
	
	public Float getDistancia() {
		return distancia;
	}
	
	public void setDistancia(Float distancia) {
		this.distancia = distancia;
	}
	
	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}
	
	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	
	public LocalTime getHora_inicio() {
		return hora_inicio;
	}
	
	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	
	public Float getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Float duracion) {
		this.duracion = duracion;
	}
	
	public Usuario getPropietario() {
		return propietario;
	}
	
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
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
			return this.number == ((Sesion)obj).number;
		}
		
		return false;
	}
	
}