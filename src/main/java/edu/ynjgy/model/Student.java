package edu.ynjgy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
}
