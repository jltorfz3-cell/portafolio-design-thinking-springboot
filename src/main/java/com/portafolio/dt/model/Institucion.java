package com.portafolio.dt.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.BigDecimal;
@Entity @Table(name="instituciones") @Getter @Setter @NoArgsConstructor @AllArgsConstructor public class Institucion {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) String nombre; @Column(unique=true) String codigo; String ciudad; String departamento; String direccion; @Column(nullable=false) Boolean activa=true; @Column(name="creado_en") OffsetDateTime creadoEn; }