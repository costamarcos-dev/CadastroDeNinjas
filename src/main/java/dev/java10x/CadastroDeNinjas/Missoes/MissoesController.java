package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET -- Mandar uma requisao para mostrar as missoes
    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    @GetMapping("/listar/{id}")
    public MissoesModel listarMissoesPorId(@PathVariable Long id) {
        return missoesService.listarMissoesPorId(id);
    }

    // POST -- Mandar uma requisao para criar as missoes
    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missoes){
        return missoesService.criarMissoes(missoes);
    }

    // PUT -- Mandar uma requisao para alterar as missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missão alterada com sucesso";
    }

    // DELETE -- Mandar uma requisao para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public void deletarMissoesPorId(@PathVariable Long id){
        missoesService.deletarMissoesPorId(id);
    }
}
