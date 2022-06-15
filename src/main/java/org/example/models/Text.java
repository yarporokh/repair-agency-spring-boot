package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "text")
public class Text {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long textId;
    @Column(name = "text")
    private String text;
}
