package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Departamento;
import com.example.demo.entity.Municipio;
import com.example.demo.repository.DepartamentoRepository;
import com.example.demo.repository.MunicipioRepository;

@Service
public class MunicipioServiceImpl implements MunicipioService{
	
	@Autowired
	private MunicipioRepository muniRepo;
	
	@Autowired
	private DepartamentoRepository depaRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Municipio> listarMunicipios() {
		List<Municipio> lista = muniRepo.findAll();
		if (lista.size() == 0) {
			throw new IllegalArgumentException("No hay municipios registrados");
		}
		return lista;
	}

	@Override
	@Transactional(readOnly = true)
	public Municipio obtenerMunicipio(Long id) {
		Optional<Municipio> idMuni = muniRepo.findById(id);
		if (idMuni.isEmpty()) {
			throw new IllegalArgumentException("No se encontro el ID del municipio");
		}
		Municipio muni = idMuni.get();
		return muni;
	}

	@Override
	@Transactional
	public void guardarMunicipio(Municipio muni) {
		Municipio m = new Municipio();
		Long idDepa = muni.getDepartamento().getId();
		Optional<Departamento> departamento = depaRepo.findById(idDepa);
		if (departamento.isEmpty()) {
			throw new IllegalArgumentException("No se encontro el ID del departamento");
		}
		Departamento id = departamento.get();
		m.setNombre(muni.getNombre());
		id.setId(idDepa);
		m.setDepartamento(id);
		muniRepo.save(m);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> listarMunicipioPorDepartamento(String nombre) {
		List<String> lista = muniRepo.listarPorDepartamento(nombre);
		if (lista.size() == 0) {
			throw new IllegalArgumentException("No hay municipios en ese departamento");
		}
		return lista;
	}
}
