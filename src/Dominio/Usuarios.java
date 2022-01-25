package Dominio;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Usuarios {
	@PrimaryKey
	private String email;
	private String name;
	private Date fecha_ncto;
	private double peso;
	private String contrasenya;
	private String frecuencia;
	private String modoRegistro;

	@Join
	@Persistent(mappedBy="propietario", dependentElement="true")
	private List<Sesiones> sesiones = new ArrayList<>();
	@Join

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



	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Date getFecha_ncto() {
		return fecha_ncto;
	}


	public void setFecha_ncto(Date fecha_ncto) {
		this.fecha_ncto = fecha_ncto;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public String getFrecuencia() {
		return frecuencia;
	}
	
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	public List<Sesiones> getSesiones() {
		return sesiones;
	}
	
	public void setSesiones(List<Sesiones> sesiones) {
		this.sesiones = sesiones;
	}
	
	public void addSesiones(Sesiones sesion) {
		if (sesion != null && !this.sesiones.contains(sesion)) {
			this.sesiones.add(sesion);
		}
	}
	public String getModoRegistro() {
		return modoRegistro;
	}


	public void setModoRegistro(String modoRegistro) {
		this.modoRegistro = modoRegistro;
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
			return this.email.equals(((Usuarios)obj).email);
		}
		
		return false;
	}
}