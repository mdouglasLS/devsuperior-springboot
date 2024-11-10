package com.devsuperior_springboot.dto;

import com.devsuperior_springboot.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;

    public ClientDTO(User client) {
        id = client.getId();
        name = client.getName();
    }
}
