package com.library.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id", nullable = false)
    @JsonBackReference
    private Title title;

    public Book(String status, Title title){
        this.status = status;
        this.title = title;
    }
    public Book(Long id, String status){
        this.id = id;
        this.status = status;
    }
}
