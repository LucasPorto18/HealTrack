package com.hospital.sistema_paciente.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hospital.sistema_paciente.Repository.ConsultaRepository;
import com.hospital.sistema_paciente.Repository.ConsultaSetorRepository;
import com.hospital.sistema_paciente.Repository.PacienteRepository;
import com.hospital.sistema_paciente.Repository.RegistroPassagemRepository;

@Service
public class LimpezaDiariaService {

    @Autowired private RegistroPassagemRepository registroPassagemRepo;
    @Autowired private ConsultaSetorRepository consultaSetorRepo;
    @Autowired private ConsultaRepository consultaRepo;
    @Autowired private PacienteRepository pacienteRepo;



    @Scheduled(cron = "0 59 23 * * *")
    public void limparDadosDoBanco() {
        

        registroPassagemRepo.deleteAllInBatch();    
        consultaSetorRepo.deleteAllInBatch();        
        consultaRepo.deleteAllInBatch();            
        pacienteRepo.deleteAllInBatch();             

       
    }
}
