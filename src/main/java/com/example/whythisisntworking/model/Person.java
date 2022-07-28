package com.example.whythisisntworking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class Person {
    private Long id;
    @NotEmpty(message = "имя не может быть пустым")
    @Size(min = 2, max = 20, message = "длина должна быть от 2 до 20 символов")
    private String name;

    @NotEmpty
    @Min(value = 0, message = "возраст не может быть отрицательным")
    private int age;

    @Email
    @NotEmpty
    private String email;

    public Person() {

    }

    @Override
    public String toString() {
        return id+name+age;
    }
}
