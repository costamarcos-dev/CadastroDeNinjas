package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUI {

    private final MissoesService missoesService;

    public MissoesControllerUI(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes",missoes);
        return "listarMissoes";
    }

    @GetMapping("/listar/{id}")
    public String listarMissoesPorId(@PathVariable Long id, Model model){
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if (missao != null){
            model.addAttribute("missao", missao);
            return "detalhesMissoes";
        }else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarMissoes";
        }

    }

    @GetMapping("/deletar/{id}")
    public String deletarMissaoPorId(@PathVariable Long id){
        missoesService.deletarMissoesPorId(id);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model){
        model.addAttribute("missao", new MissoesDTO());
        return "adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.criarMissoes(missao);
        redirectAttributes.addFlashAttribute("messagem","Missao cadastrada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }


}
