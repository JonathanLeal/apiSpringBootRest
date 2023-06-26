package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Municipio;

public interface MunicipioService {
	public List<Municipio> listarMunicipios();
	public Municipio obtenerMunicipio(Long id);
	public void guardarMunicipio(Municipio muni);
	public List<String> listarMunicipioPorDepartamento(String nombre);
}
