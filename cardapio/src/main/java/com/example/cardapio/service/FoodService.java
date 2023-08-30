package com.example.cardapio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cardapio.entities.RespostaModelo;
import com.example.cardapio.repository.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	private FoodRepository repository;
	
	@Autowired
	private RespostaModelo rm;
	
	public ResponseEntity<RespostaModelo> remover(Long codigo){
		repository.deleteById(codigo);
		rm.setMensagem("Produto Deletado!");
		return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
	}
}
