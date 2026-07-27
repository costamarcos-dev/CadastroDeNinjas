package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas" , description = "Essa rota da uma mensagem de boas vindas a quem acessa ela.")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja"),
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID) "+ novoNinja.getId());
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista os ninjas", description = "Rota lista a tabela de ninja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninjas listados com sucesso"),
    })
    public ResponseEntity<List> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar Ninja por ID (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por Id", description = "Rota lista um ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja nao encontrado"),
    })
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id)/*O PathVarible é para variavel*/{
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if(ninja != null ) {
             return ResponseEntity.ok(ninja);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ninja com o id: "+id+" nao existe em nossos registros.");
        }
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por Id", description = "Rota altera os dados do ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado, nao foi possivel alterar"),
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao")
            @PathVariable Long id,
            @Parameter(description = "Usuario mandas os dados do ninja a ser atualizado no corpo da requisicao")
            @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
                if (ninja != null){
                    return ResponseEntity.ok(ninja);
                } else {
                    return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Ninja com o id: "+id+" não existe em nossos registros.");
                }
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o ninja por Id", description = "Rota deleta o ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado, nao foi possivel deletar"),
    })
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){

        if(ninjaService.listarNinjasPorId(id) != null ) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o id "+id+" não encontrado.");
        }

    }


}
