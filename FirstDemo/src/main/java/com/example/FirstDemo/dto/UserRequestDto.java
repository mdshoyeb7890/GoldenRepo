package com.example.FirstDemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String name;
    private Integer age;
    private String city;
}
