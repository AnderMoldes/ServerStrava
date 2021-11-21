package DTO;

import java.util.ArrayList;
import java.util.List;

import Dominio.Reto;


//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class RetoAssembler {	
	private static RetoAssembler instance;

	private RetoAssembler() { }
	
	public static RetoAssembler getInstance() {
		if (instance == null) {
			instance = new RetoAssembler();
		}

		return instance;
	}

	public RetoDTO articleToDTO(Reto article) {
		RetoDTO dto = new RetoDTO();
		
		dto.setNumber(article.getNumber());
		dto.setName(article.getName());
		dto.setFecha_inicio(article.getFecha_inicio());
		dto.setFecha_fin(article.getFecha_fin());
		dto.setDistanciaObjetivo(article.getDistanciaObjetivo());
		dto.setDeporte(article.getDeporte());
				
		return dto;
	}
	
	public List<RetoDTO> retoToDTO(List<Reto> articles) {
		List<RetoDTO> dtos = new ArrayList<>();
		
		for (Reto article : articles) {
			dtos.add(this.articleToDTO(article));
		}
		
		return dtos;		
	}
}