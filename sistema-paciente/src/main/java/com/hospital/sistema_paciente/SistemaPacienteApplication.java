package com.hospital.sistema_paciente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SistemaPacienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPacienteApplication.class, args);
	}

}
