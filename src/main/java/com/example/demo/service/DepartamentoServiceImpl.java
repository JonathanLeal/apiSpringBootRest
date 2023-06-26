package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Departamento;
import com.example.demo.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{
	
	@Autowired
	private DepartamentoRepository depaRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Departamento> listarDepartamentos() {
		List<Departamento> lista = depaRepo.findAll();
		if (lista.size() == 0) {
			throw new IllegalArgumentException("No hay departamentos registrados");
		}
		return lista;
	}

	@Override
	@Transactional
	public void guardarDepartamento(Departamento departamento) {
		Departamento d = new Departamento();
		d.setNombre(departamento.getNombre());
		depaRepo.save(d);
	}

	@Override
	@Transactional(readOnly = true)
	public Departamento obtenerDepartamento(Long id) {
		Optional<Departamento> idDepa = depaRepo.findById(id);
		if (idDepa.isEmpty()) {
			throw new IllegalArgumentException("No existe ese id");
		}
		Departamento idDe = idDepa.get();
		return idDe;
	}

	@Override
	@Transactional
	public void eliminarDepartamento(Departamento departamento) {
		Optional<Departamento> id = depaRepo.findById(departamento.getId());
		if (id.isEmpty()) {
			throw new IllegalArgumentException("No existe ese id");
		}
		Departamento idDe = id.get();
		depaRepo.delete(idDe);
	}

}
