package com.portafolio.dt.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.BigDecimal;
@Entity @Table(name="equipos") @Getter @Setter @NoArgsConstructor @AllArgsConstructor public class Equipo {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(name="curso_id",nullable=false) Long cursoId; @Column(nullable=false) String nombre; String descripcion; @Column(name="creado_por") Long creadoPor; @Column(name="creado_en") OffsetDateTime creadoEn; }