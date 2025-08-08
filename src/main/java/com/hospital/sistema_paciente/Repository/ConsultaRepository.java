package com.hospital.sistema_paciente.Repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.sistema_paciente.Model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPaciente_Id(Long pacienteId);
}
