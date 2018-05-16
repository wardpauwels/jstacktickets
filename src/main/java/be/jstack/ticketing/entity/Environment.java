package be.jstack.ticketing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Environment {

    @Id
    private String id;

    private String device;
    private String operatingSystem;
    private String browser;
}