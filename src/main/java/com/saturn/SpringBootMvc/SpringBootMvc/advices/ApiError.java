package com.saturn.SpringBootMvc.SpringBootMvc.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {

    private HttpStatus status;

    private List<String> message;

}
