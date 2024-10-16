package com.umesh.jwtnotes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Getter
@Setter 
@ToString
@NoArgsConstructor 
@Table(name = "user", schema = "jwtnotes")
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
