package org.example.codetohire.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.codetohire.enums.Difficulty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class questionListDTO {

    private  Long id;
    private String title;
    private String difficulty;

}
