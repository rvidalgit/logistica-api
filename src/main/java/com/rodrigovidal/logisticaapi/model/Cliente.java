package com.rodrigovidal.logisticaapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
}
