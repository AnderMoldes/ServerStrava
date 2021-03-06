package DTO;

import java.util.ArrayList;
import java.util.List;

import Dominio.Retos;
public class RetoAssembler {	
	private static RetoAssembler instance;

	private RetoAssembler() { }
	
	public static RetoAssembler getInstance() {
		if (instance == null) {
			instance = new RetoAssembler();
		}

		return instance;
	}

	public RetoDTO retoToDTO(Retos article) {
		RetoDTO dto = new RetoDTO();
		
		dto.setNumber(article.getNumber());
		dto.setName(article.getName());
		dto.setFecha_inicio(article.getFecha_inicio());
		dto.setFecha_fin(article.getFecha_fin());
		dto.setDistanciaObjetivo(article.getDistanciaObjetivo());
		dto.setDeporte(article.getDeporte());
	
		return dto;
	}
	
	public List<RetoDTO> retosToDTO(List<Retos> retos) {
		List<RetoDTO> dtos = new ArrayList<>();
		
		for (Retos reto : retos) {
			dtos.add(this.retoToDTO(reto));
		}
		
		return dtos;		
	}
}