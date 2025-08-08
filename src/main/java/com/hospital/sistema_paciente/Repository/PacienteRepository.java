package com.hospital.sistema_paciente.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.sistema_paciente.Model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}
