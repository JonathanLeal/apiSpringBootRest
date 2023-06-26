package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Departamento;

public interface DepartamentoService {
	public List<Departamento> listarDepartamentos();
	public void guardarDepartamento(Departamento departamento);
	public Departamento obtenerDepartamento(Long id);
	public void eliminarDepartamento(Departamento departamento);
}
