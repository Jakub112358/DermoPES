package com.dermopes.model;

import com.dermopes.model.enumeration.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDate examDate;
    @ElementCollection(targetClass = Category.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<Category> categories;
    @OneToMany (mappedBy = "question", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Answer> answers;
}
