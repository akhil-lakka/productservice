package dev.akhil.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ExecptionDTO {
    private HttpStatus errorCode;
    private String message;
}
