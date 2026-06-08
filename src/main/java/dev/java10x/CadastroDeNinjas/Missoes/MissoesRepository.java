package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;

public interface MissoesRepository extends JpaRepository<MissoesModel, Long> {
}
