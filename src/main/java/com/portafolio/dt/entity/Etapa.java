package com.portafolio.dt.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="etapas") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Etapa { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(name="proyecto_id",nullable=false) private Long proyectoId; @Enumerated(EnumType.STRING) @Column(nullable=false) private TipoEtapa tipo; @Column(nullable=false) private String titulo; @Column(columnDefinition="text") private String descripcion; @Enumerated(EnumType.STRING) @Column(nullable=false) private EstadoEtapa estado=EstadoEtapa.PENDIENTE; private Integer progreso=0; public enum TipoEtapa{EMPATIZAR,DEFINIR,IDEAR,PROTOTIPAR,PROBAR} public enum EstadoEtapa{PENDIENTE,EN_PROCESO,COMPLETADA} }
