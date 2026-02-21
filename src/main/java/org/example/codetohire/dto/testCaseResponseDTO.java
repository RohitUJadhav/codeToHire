package org.example.codetohire.dto;

import lombok.*;

@Setter @Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class testCaseResponseDTO {
    private Long id;
    private  String input;
    private  String expectedOutput;
    private  Boolean isHidden;


}
