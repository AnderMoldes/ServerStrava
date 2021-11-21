package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;


public class Usuario{

	private String email;
	private String name;
	private LocalDate fecha_ncto;
	private float peso;
	private String contrasenya;
	private String frecuencia;
	private List<Sesion> sesiones = new ArrayList<>();
	private List<Bid> bids = new ArrayList<>();
	
	public String getEmail() {
		return email;
	}
	
	
	public String getContrasenya() {
		return contrasenya;
	}
	public boolean comprobarContrasenya(String password) {
		return this.contrasenya.equals(password);
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}


	public void setPeso(float peso) {
		this.peso = peso;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getFecha_ncto() {
		return fecha_ncto;
	}
	
	public void setFecha_ncto(LocalDate fecha_ncto) {
		this.fecha_ncto = fecha_ncto;
	}
	
	public Float getPeso() {
		return peso;
	}
	
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	
	public String getFrecuencia() {
		return frecuencia;
	}
	
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	public List<Sesion> getSesiones() {
		return sesiones;
	}
	
	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	public void addSesiones(Sesion sesion) {
		if (sesion != null && !this.sesiones.contains(sesion)) {
			this.sesiones.add(sesion);
		}
	}
	public List<Bid> getBids() {
		return bids;
	}
	
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	
	public void addBid(Bid bid) {
		if (bid != null && !this.bids.contains(bid)) {
			this.bids.add(bid);
		}
	}
	
		
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append(this.email);
		result.append(" - (");
		result.append(this.name);
		result.append(" - ");
		result.append(this.contrasenya);
		result.append(" - ");
		result.append(this.fecha_ncto);
		result.append(" - (");
		result.append(this.peso);
		result.append(" - (");
		result.append(this.frecuencia);
		result.append(" - (");
		result.append(this.sesiones.size());
		result.append(" sesiones)");
		
		return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((Usuario)obj).email);
		}
		
		return false;
	}
}