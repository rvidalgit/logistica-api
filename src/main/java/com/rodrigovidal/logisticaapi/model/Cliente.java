package com.rodrigovidal.logisticaapi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "email_key")})
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank
    @Size(max = 255, min = 3)
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 255, min = 10)
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Size(max = 255, min = 10)
    @Column(nullable = false)
    private String telefone;

}
