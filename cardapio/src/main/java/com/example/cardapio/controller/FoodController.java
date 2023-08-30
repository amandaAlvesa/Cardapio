package com.example.cardapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.dto.FoodRequestDTO;
import com.example.cardapio.dto.FoodResponseDTO;
import com.example.cardapio.entities.Food;
import com.example.cardapio.entities.RespostaModelo;
import com.example.cardapio.repository.FoodRepository;
import com.example.cardapio.service.FoodService;

@RestController
@RequestMapping("food")
public class FoodController {

	@Autowired
	private FoodRepository repository;
	
	@Autowired
	private	FoodService service;
	

	@CrossOrigin(origins = "*", allowedHeaders = "*" )
	@GetMapping
	public List<FoodResponseDTO> getAll() {
		List<FoodResponseDTO>findAll =  repository.findAll().stream().map(FoodResponseDTO::new).toList();
		return findAll;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*" )
	@PostMapping
	public void saveFood(@RequestBody FoodRequestDTO request) {
		Food data = new Food(request);
		repository.save(data);
		return;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*" )
	@DeleteMapping("delete/{codigo}")
	public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
		return service.remover(codigo);
		
	}
	
}
