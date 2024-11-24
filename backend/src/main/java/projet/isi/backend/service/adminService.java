package projet.isi.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projet.isi.backend.models.admin;
import projet.isi.backend.repository.adminRepository;

@Service
public class adminService {

    @Autowired
         private adminRepository repo ;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public adminService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    // Registration
    public admin register(String login, String password) {
        if (repo.findByLogin(login ) != null) {
            throw new RuntimeException("Login already exists");
        }

        admin newadmin = new admin();
        newadmin.setLogin(login);
        newadmin.setPassword(passwordEncoder.encode(password));
        return repo.save(newadmin);
    }
    // Login
    public admin login(String login, String password) {
        // Recherchez l'admin par son login
        admin existingAdmin = repo.findByLogin(login);
        if (existingAdmin == null || !passwordEncoder.matches(password, existingAdmin.getPassword())) {
            throw new RuntimeException("Invalid login or password");
        }

        return existingAdmin;
    }

        // Logout (simple pour une API REST)
    public void logout() {
        // Le logout peut être géré côté front-end en supprimant le token ou la session
    }
}
