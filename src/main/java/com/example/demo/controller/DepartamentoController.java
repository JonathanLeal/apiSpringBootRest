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

import com.example.demo.entity.Departamento;
import com.example.demo.service.DepartamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/depar")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService depaSer;
	
	@GetMapping("/lista")
	public ResponseEntity<?> lista(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(depaSer.listarDepartamentos());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(depaSer.obtenerDepartamento(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Departamento depa, BindingResult result){
		try {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo: " + err.getField() + " " + err.getDefaultMessage());
			});
			
			if (result.hasErrors()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
			}
			depaSer.guardarDepartamento(depa);
			return ResponseEntity.status(HttpStatus.OK).body("Departamento guardado con exito");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> obtener(@RequestBody Departamento depa){
		try {
			depaSer.eliminarDepartamento(depa);
			return ResponseEntity.status(HttpStatus.OK).body("Departamento eliminado con exito");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
