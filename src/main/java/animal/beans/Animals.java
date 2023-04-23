package animal.beans;

/**
 * @author xbitt = twilkens
 * CIS171 - Spring 2023
 * Apr 4, 2023
 */


import java.sql.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xbitt
 *
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "animals")
public class Animals {
@Id
@GeneratedValue
private Long id;

private String name;
private String breed;
private String species;
private Integer age;
private Date arrivalDate;
private Boolean goodWithKids;
private String color;
private String sex;

private String search;


public Animals(String name, String species, Integer age) {
    this.name = name;
    this.species = species;
    this.age = age;
}

public Animals(String name, String breed, String species, Integer age, Date arrivalDate, Boolean goodWithKids, String color, String sex) {
    this.name = name;
    this.breed = breed;
    this.species = species;
    this.age = age;
    this.arrivalDate = arrivalDate;
    this.goodWithKids = goodWithKids;
    this.color = color;
    this.sex = sex;
}

public Animals(String name, Integer age) {
    this.name = name;
    this.age = age;
}

public Animals(String name) {
    this.name = name;
}
}