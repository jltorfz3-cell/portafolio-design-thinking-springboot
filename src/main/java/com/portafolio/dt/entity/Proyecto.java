package com.portafolio.dt.entity;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDate;
@Entity @Table(name="proyectos") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Proyecto { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(name="equipo_id",nullable=false) private Long equipoId; @Column(nullable=false) private String titulo; @Column(columnDefinition="text") private String descripcion; @Column(name="problema_inicial",columnDefinition="text") private String problemaInicial; @Column(name="reto_diseno",columnDefinition="text") private String retoDiseno; @Column(nullable=false) private String estado="BORRADOR"; private LocalDate fechaInicio; private LocalDate fechaFin; private Integer progreso=0; }
