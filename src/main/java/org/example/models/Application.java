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
    private int applicationId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "text")
    private String text;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private Date date;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "progress")
    private String progress;

    @Column(name = "serviceman_id")
    private int servicemanEmail;

    @Column(name = "response_text")
    private String responseText;
}
