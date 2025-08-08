package com.hospital.sistema_paciente.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.sistema_paciente.Model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {

    java.util.Optional<Setor> findByNome(String nome);
}
