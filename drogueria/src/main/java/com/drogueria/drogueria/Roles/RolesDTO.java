package com.drogueria.drogueria.Roles;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}