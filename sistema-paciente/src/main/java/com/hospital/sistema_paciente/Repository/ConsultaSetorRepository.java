package com.hospital.sistema_paciente.Repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.sistema_paciente.Model.Consulta;
import com.hospital.sistema_paciente.Model.ConsultaSetor;

public interface ConsultaSetorRepository extends JpaRepository<ConsultaSetor, Long> {
    
    Optional<ConsultaSetor> findByConsultaAndSetorNome(Consulta consulta, String nome);

}