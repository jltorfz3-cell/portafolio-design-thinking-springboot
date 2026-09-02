package com.portafolio.dt.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.BigDecimal;
@Entity @Table(name="docentes") @Getter @Setter @NoArgsConstructor @AllArgsConstructor public class Docente {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(name="usuario_id",nullable=false,unique=true) Long usuarioId; @Column(name="institucion_id") Long institucionId; String especialidad; }