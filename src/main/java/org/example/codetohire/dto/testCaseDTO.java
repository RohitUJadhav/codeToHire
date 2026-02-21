package org.example.codetohire.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class testCaseDTO {
    private  String input;
    private  String expectedOutput;
    private  Boolean isHidden;

}
