package org.example.dto;

import lombok.Data;
import lombok.Value;


@Value
@Data
public class ApplicationDTO {
    Long applicationId;
    Long userId;
    String text;
}
