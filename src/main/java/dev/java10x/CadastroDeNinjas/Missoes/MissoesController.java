package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Lista os missoes", description = "Rota lista a tabela de missoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missoes listados com sucesso"),
    })
    public ResponseEntity<List> listarMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o missoes por Id", description = "Rota lista um missoes pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao encontrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao nao encontrada"),
    })
    public ResponseEntity<?> listarMissoesPorId(@PathVariable Long id) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if(missao != null){
            return ResponseEntity.ok(missao);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Missao com o id: "+id+" nao existe em nossos registros.");
        }
    }

    // POST -- Mandar uma requisao para criar as missoes
    @PostMapping("/criar")
    @Operation(summary = "Criar uma nova missao", description = "Rota cria uma nova missao e insere no banco de dado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criacao da missao " )
    })
    public ResponseEntity <String> criarMissao(@RequestBody MissoesDTO missoes){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " + novaMissao.getNome() + " (ID) "+ novaMissao.getId());
    }

    // PUT -- Mandar uma requisao para alterar as missoes
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o missao por Id", description = "Rota altera os dados do missoes pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao não encontrada, nao foi possivel alterar"),
    })
    public ResponseEntity<?> alterarMissaoPorId(
        @Parameter(description = "Usuario manda o id no caminho da requisiçao")
        @PathVariable Long id,
        @Parameter(description = "Usuario manda os dados da missao a ser atualizada no corpo da requisicao")
        @RequestBody MissoesDTO missaoAtualizada){
        MissoesDTO missao = missoesService.atualizarMissoes(id, missaoAtualizada);
        if (missao != null){
            return ResponseEntity.ok(missao);
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao com o id: "+id+" não existe em nossos registros.");
        }
    }

    // DELETE -- Mandar uma requisao para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o missao por Id", description = "Rota deleta o missao pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao não encontrada, nao foi possivel deletar"),
    })
    public ResponseEntity<String> deletarMissoesPorId(@PathVariable Long id){

        if(missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissoesPorId(id);
            return ResponseEntity.ok("Missao com o ID "+id+ " deletada com sucesso.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missao com o id "+id+" não encontrada.");
        }
    }
}
