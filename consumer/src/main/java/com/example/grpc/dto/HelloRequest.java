package com.example.grpc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloRequest {
    
    private String firstName;
    private String lastName;
    
}
