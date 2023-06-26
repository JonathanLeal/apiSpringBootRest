package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{
	@Query("SELECT m.nombre FROM Municipio m INNER JOIN m.departamento p WHERE p.nombre = :nombreDepartamento")
    List<String> listarPorDepartamento(@Param("nombreDepartamento") String nombreDepartamento);
}
