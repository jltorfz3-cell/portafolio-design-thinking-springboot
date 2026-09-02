package com.portafolio.dt.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.BigDecimal;
@Entity @Table(name="estudiantes") @Getter @Setter @NoArgsConstructor @AllArgsConstructor public class Estudiante {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(name="usuario_id",nullable=false,unique=true) Long usuarioId; @Column(name="codigo_estudiante",unique=true) String codigoEstudiante; String grado; String grupo; @Column(name="institucion_id") Long institucionId; }