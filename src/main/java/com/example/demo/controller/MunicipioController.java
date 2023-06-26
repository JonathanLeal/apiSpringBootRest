package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Municipio;
import com.example.demo.service.MunicipioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/muni")
public class MunicipioController {
	
	@Autowired
	private MunicipioService muniSer;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listar(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(muniSer.listarMunicipios());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(muniSer.obtenerMunicipio(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Municipio muni, BindingResult result){
		try {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo: " + err.getField() + " " + err.getDefaultMessage());
			});
			
			if (result.hasErrors()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
			}
			muniSer.guardarMunicipio(muni);
			return ResponseEntity.status(HttpStatus.OK).body("Municipio guardado con exito");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/depar/{nombreDepartamento}")
	public ResponseEntity<?> obtener(@PathVariable String nombreDepartamento){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(muniSer.listarMunicipioPorDepartamento(nombreDepartamento));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
