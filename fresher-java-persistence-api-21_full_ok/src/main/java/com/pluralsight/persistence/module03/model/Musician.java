package com.pluralsight.persistence.module03.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
public class Musician {
    @Id
    private Long id;

    private String name;

//    @OneToOne
//    @JoinColumn(name = "cd_id")
//    private CD cd;

    @ManyToMany(mappedBy = "musicians")
    private Set<CD> cds;
}