package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    public Type(Long id, String name) {
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
