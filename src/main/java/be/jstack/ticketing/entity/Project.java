package be.jstack.ticketing.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Project {

    @Id
    @ApiModelProperty(notes = "The database generated project ID")
    private String id;
    @ApiModelProperty(notes = "The name of the project", required = true)
    private String title;
}