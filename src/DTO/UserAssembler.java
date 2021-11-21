package DTO;

import Dominio.Usuario;

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

	public UserDTO userToDTO(Usuario user) {
		UserDTO dto = new UserDTO();
		
		dto.setEmail(user.getEmail());
		dto.setNickname(user.getName());
		
		return dto;
	}
}