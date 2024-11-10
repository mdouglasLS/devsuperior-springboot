package com.devsuperior_springboot.dto;

import com.devsuperior_springboot.entities.Product;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @NotNull(message = "Campo requerido.")
    @Positive(message = "Preço deve ser um valor positivo.")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Deve ter pelo menos uma categoria.")
    List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
        product.getCategories().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

}
