package be.jstack.ticketing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class Environment {
    private String device;
    private String operatingSystem;
    private String browser;
}