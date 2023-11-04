package vn.edu.iuh.fit.models;

import jakarta.ejb.Local;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime logIn;
    private LocalDateTime logOut;
    private String note;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Log(LocalDateTime logIn, LocalDateTime logOut, String note, Account account) {
        this.logIn = logIn;
        this.logOut = logOut;
        this.note = note;
        this.account = account;
    }

    public Log() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getLogIn() {
        return logIn;
    }

    public void setLogIn(LocalDateTime logIn) {
        this.logIn = logIn;
    }

    public LocalDateTime getLogOut() {
        return logOut;
    }

    public void setLogOut(LocalDateTime logOut) {
        this.logOut = logOut;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", logIn=" + logIn +
                ", logOut=" + logOut +
                ", note='" + note + '\'' +
                ", account=" + account +
                '}';
    }
}
