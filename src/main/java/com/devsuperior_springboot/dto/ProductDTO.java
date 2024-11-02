package com.devsuperior_springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Campo requerido.")
    @Size(min = 3, max = 80, message = "Nome deve ter entre 3 e 80 caracteres.")
    private String name;

    @NotBlank(message = "Campo requerido.")
    @Size(min = 10, message = "Descrição deve ter no mínimo 10 caracteres.")
    private String description;

    @Positive(message = "Preço deve ser um valor positivo.")
    private Double price;
    private String imgUrl;

}
