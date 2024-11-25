package projet.isi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.isi.backend.models.System;
import projet.isi.backend.repository.SystemRepository;  // Assurez-vous de l'importation correcte

import java.util.List;
import java.util.Optional;

@Service
public class systemService {

    @Autowired
    private SystemRepository systemRepository;  // Injecter le repository correctement

    public System saveOrUpdateSystem(System system) {
        return systemRepository.save(system);
    }

    public List<System> getAllSystems() {
        return systemRepository.findAll();
    }

    public Optional<System> getSystemById(Long id) {
        return systemRepository.findById(id);
    }

    public void deleteSystem(Long id) {
        systemRepository.deleteById(id);
    }
}
