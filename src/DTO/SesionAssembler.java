package DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Dominio.Sesion;
import Dominio.Usuario;


//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class SesionAssembler {
	private static SesionAssembler instance;
	private SesionAssembler() { }
	
	public static SesionAssembler getInstance() {
		if (instance == null) {
			instance = new SesionAssembler();
		}
		
		return instance;
	}

	public SesionDTO sesionToDTO(Sesion category) {
		SesionDTO dto = new SesionDTO();		
		dto.setNumber(category.getNumber());
		dto.setTitulo(category.getTitulo());
		dto.setDeporte(category.getDeporte());
		dto.setDistancia(category.getDistancia());
		dto.setFecha_inicio(category.getFecha_inicio());
		dto.setHora_inicio(category.getHora_inicio());
		dto.setDuracion(category.getDuracion());
		dto.setPropietario(category.getPropietario());
		return dto;
	}

	public List<SesionDTO> categoryToDTO(List<Sesion> categories) {		
		List<SesionDTO> dtos = new ArrayList<>();
		
		for (Sesion category : categories) {
			dtos.add(this.sesionToDTO(category));
		}
		
		return dtos;
	}
}