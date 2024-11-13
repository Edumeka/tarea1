package hn.emeka.tarea1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.emeka.tarea1.models.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    
}
