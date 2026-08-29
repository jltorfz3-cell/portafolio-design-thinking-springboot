package com.portafolio.dt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication public class PortafolioDesignThinkingApplication { 

    public static void main(String[] args){

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        System.out.println("Admin123     = " +
                encoder.encode("Admin123"));

        System.out.println("Profesor123  = " +
                encoder.encode("Profesor123"));

        System.out.println("Estudiante123 = " +
                encoder.encode("Estudiante123"));

        SpringApplication.run(PortafolioDesignThinkingApplication.class,args);
    } 
}
