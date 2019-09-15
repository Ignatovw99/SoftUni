package app.ccb.domain.entities;

import app.ccb.domain.entities.base.BaseEntity;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    private String fullName;

    private Integer age;

    private BankAccount bankAccount;

    public Client() {
    }

    @Column(name = "full_name", nullable = false)
    @NotNull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToOne(targetEntity = BankAccount.class, mappedBy = "client")
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Transient
    public void setFirstName(String firstName) {
        this.fullName = firstName;
    }

    @Transient
    public void setLastName(String lastName) {
        this.setFullName(this.fullName.concat(" ").concat(lastName));
    }
}