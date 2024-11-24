package projet.isi.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import projet.isi.backend.models.admin;
import projet.isi.backend.service.adminService;

import java.util.Map;

@Controller
@RequestMapping("/api/admin")
public class adminController {

    @Autowired
    private adminService adminservice;

    // Registration
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody admin admin) {
        adminservice.register(admin.getLogin(), admin.getPassword());
        return ResponseEntity.ok("Admin registered successfully");
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody admin admin) {
        System.out.println("Login attempt: " + admin.getLogin());
        try {
            // Tentative de connexion
            admin loggedAdmin = adminservice.login(admin.getLogin(), admin.getPassword());

            // Si la connexion est réussie
            if (loggedAdmin != null) {
                System.out.println("Login successful for: " + loggedAdmin.getLogin());

                // Retourne une réponse avec succès et les détails de l'utilisateur
                return ResponseEntity.ok().body(Map.of(
                        "message", "Login successful",
                        "user", loggedAdmin
                ));
            } else {
                System.out.println("Login failed for: " + admin.getLogin());

                // Retourne une erreur 401 (Unauthorized)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "message", "Invalid login or password"
                ));
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());

            // Retourne une erreur 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message", "An error occurred during login",
                    "error", e.getMessage()
            ));
        }}}






// Logout
  /*  @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        adminService.logout();
        return ResponseEntity.ok("Logout successful");
    }
*/

