package com.testing.Converters.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Req {
    private String fromUnit;
    private String toUnit;
    private double value;
}
