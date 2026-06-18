package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/todos")
    public String mostraTodososNinjas(){
        return "Mostrar Todos os Ninjas";
    }

    // Mostrar Ninja por ID (READ)
    @GetMapping("/todosID")
    public String mostraTodososNinjasPorId(){
        return "Mostrar Ninja por id";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarID")
    public String alterarNijaPorId(){
        return "Alterar Ninjas por id";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId(){
        return "Deletar Ninjas por id";
    }


}
