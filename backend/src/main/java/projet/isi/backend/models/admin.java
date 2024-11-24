package projet.isi.backend.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "admin")
public class admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long id_admin;

    @Column(name = "login" , nullable = false )
    private  String login ;

    @Column(name = "password" , nullable = false)
    private  String password;

    public admin(Long id_admindmin, String login, String password) {
        this.id_admin = id_admin;
        this.login = login;
        this.password = password;
    }

    public admin() {
    }

    public Long getIdAdmin() {
        return id_admin;
    }

    public void setIdAdmin(Long id_admin) {
        this.id_admin = id_admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
