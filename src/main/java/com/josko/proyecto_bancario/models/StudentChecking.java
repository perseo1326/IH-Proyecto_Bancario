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

    public StudentChecking(AccountHolder firstAccountHolder,
                           Optional<AccountHolder> secondAccountholder,
                           String iban,
                           Money balance) {
        super(firstAccountHolder, secondAccountholder, iban, balance);
    }

    @Override
    public String toString() {
        return "\nStudentChecking{ " + super.toString() + " }";
    }


}
