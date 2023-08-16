package com.Service.Catalog.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table
public class Member {
    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_sequence"
    )


    private Long id;
    private String cin;
    private String matriculation;
    private String password;

    public Member(String cin, String matriculation, String password) {
        this.cin = cin;
        this.matriculation = matriculation;
        this.password = password;
    }
}
