package com.dermopes.model;

import com.dermopes.model.enumeration.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @OneToMany (mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
    private LocalDate examDate;
    @ElementCollection(targetClass = Category.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<Category> category;
}
