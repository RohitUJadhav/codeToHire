package org.example.codetohire.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.codetohire.enums.Role;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String name;

   @Pattern(regexp ="^[A-Za-z0-9+_.-]+@gmail\\.com$")
   private String email;

   private String password;
   private Role role;


}
