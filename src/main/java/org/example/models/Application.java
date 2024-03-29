package org.example.models;

import javax.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long applicationId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "application_text",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "text_id")
    )
    private Text text;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private Date date;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "progress")
    private String progress;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceman_id")
    private User serviceman;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "application_response",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "text_id")
    )
    private Text responseText;
}
