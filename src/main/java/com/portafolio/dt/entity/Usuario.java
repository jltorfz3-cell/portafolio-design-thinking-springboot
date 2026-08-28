package com.portafolio.dt.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="usuarios") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false,unique=true) private String username; @Column(nullable=false,unique=true) private String email; @Column(name="password_hash",nullable=false) private String passwordHash; @Column(nullable=false) private String nombres; @Column(nullable=false) private String apellidos; @Column(nullable=false) private boolean activo=true; }
