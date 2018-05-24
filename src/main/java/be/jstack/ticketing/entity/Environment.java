package be.jstack.ticketing.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Environment {
    @ApiModelProperty(notes = "The device where the problem happened")
    private String device;
    @ApiModelProperty(notes = "The operating system of the device")
    private String operatingSystem;
    @ApiModelProperty(notes = "The browser which the user was using during the problem")
    private String browser;
}