package com.hospital.sistema_paciente.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.sistema_paciente.Model.RegistroPassagem;

public interface RegistroPassagemRepository extends JpaRepository<RegistroPassagem, Long> {
    List<RegistroPassagem> findByPacienteId(Long pacienteId);
}
