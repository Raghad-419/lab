package com.example.blog_security.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    @Column
    @NotEmpty(message = "Empty title")
    private String title;
    @Column
    @NotEmpty(message = "Empty body")
    private String body;


    @ManyToOne
    @JsonIgnore
    private MyUser user;
}
