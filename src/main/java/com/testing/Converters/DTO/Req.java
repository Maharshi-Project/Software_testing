package com.testing.Converters.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Req {
    private String fromUnit;
    private String toUnit;
    private double value;
}
