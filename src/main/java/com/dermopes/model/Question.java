package com.dermopes.model;

import com.dermopes.model.enumeration.Category;
import com.dermopes.model.enumeration.Difficulty;
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

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Question other))
            return false;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
