package DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dominio.Sesiones;
import Dominio.Usuarios;

//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class UserAssembler {
	private static UserAssembler instance;

	private UserAssembler() { }
	
	public static UserAssembler getInstance() {
		if (instance == null) {
			instance = new UserAssembler();
		}

		return instance;
	}

	public UserDTO userToDTO(Usuarios user) {
		UserDTO dto = new UserDTO();
		
		dto.setEmail(user.getEmail());
		dto.setName(user.getName());
		dto.setFecha_ncto(new Date(2001-11-12));
		dto.setPeso(user.getPeso());
		dto.setContrasenya(user.getContrasenya());
		dto.setFrecuencia(user.getFrecuencia());
		return dto;
	}
	public List<UserDTO> categoryToDTO(List<Usuarios> categories) {		
		List<UserDTO> dtos = new ArrayList<>();
		
		for (Usuarios category : categories) {
			dtos.add(this.userToDTO(category));
		}
		
		return dtos;
	}
}
