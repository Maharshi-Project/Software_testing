package com.testing.Converters.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Req {
    private String fromUnit;
    private String toUnit;
    private double value;

    public Req() {}

    public Req(String fromUnit, String toUnit, double value) {
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.value = value;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
