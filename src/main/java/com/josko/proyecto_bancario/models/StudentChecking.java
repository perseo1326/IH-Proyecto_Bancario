package com.josko.proyecto_bancario.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "student_checking")
public class StudentChecking extends Account {

    public StudentChecking() {
        super();
    }

    public StudentChecking(String iban, LocalDate creationDate, BigDecimal penaltyFee, AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder) {
        super(iban, creationDate, penaltyFee, firstAccountHolder, secondAccountholder);
    }

    @Override
    public String toString() {
        return "\nStudentChecking{ " + super.toString() + " }";
    }


}
