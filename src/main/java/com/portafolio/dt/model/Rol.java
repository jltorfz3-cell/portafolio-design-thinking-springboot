package com.portafolio.dt.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.BigDecimal;
@Entity @Table(name="roles") @Getter @Setter @NoArgsConstructor @AllArgsConstructor public class Rol {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false,unique=true) String nombre; String descripcion; @Column(name="creado_en") OffsetDateTime creadoEn; }