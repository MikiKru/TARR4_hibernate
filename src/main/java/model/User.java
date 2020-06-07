package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data                   // dodaje gettery, settery i toString
@AllArgsConstructor
@NoArgsConstructor
@Entity                 // mapuje klasę User na tabelkę db o nazwie user i kolumnach jak pola klasowe
@Table(name = "user")   // zmienia nazwę mapowanej tabelki
public class User {
    @Id                 // dodaje klauzule PRIMARY KEY do userId
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // dodaje klauzulę a_i do userId
    private int userId;
    private String userName;
    @Column(name = "user_lastname")                         // mapuje pole o nazwie określonej w name
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private LocalDateTime userRegistration = LocalDateTime.now();
    private boolean userStatus = true;
}
