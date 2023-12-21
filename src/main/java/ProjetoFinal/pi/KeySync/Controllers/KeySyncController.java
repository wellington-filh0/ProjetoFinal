package ProjetoFinal.pi.KeySync.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ProjetoFinal.pi.KeySync.Models.Chave;
import ProjetoFinal.pi.KeySync.Models.Laboratorio;
import ProjetoFinal.pi.KeySync.Models.Professor;
import ProjetoFinal.pi.KeySync.Repositories.ChaveRepository;
import ProjetoFinal.pi.KeySync.Repositories.LaboratorioRepository;
import ProjetoFinal.pi.KeySync.Repositories.ProfessorRepository;

@Controller
public class KeySyncController {
	
	@Autowired // Define automaticamente a variável abaixo como um objeto
	private LaboratorioRepository lr;
	@Autowired
	private ChaveRepository cr;
	@Autowired
	private ProfessorRepository pr;

	// FAZENDO LOGIN DE UM ADM

	@GetMapping("/KeySync/LoginAdm")
	public String LoginAdm() {
		return "KeySync/LoginAdm";
	}

	// FAZENDO LOGIN DE UM PROFESSOR

	@GetMapping("/KeySync/LoginProfessor")
	public String LoginProfessor() {
		return "KeySync/LoginProfessor";
	}

	// ACESSANDO ÁREA DO ADM ATRAVÉS DA URL TEMPORARIAMENTE

	@GetMapping("/administrador")
	public String AreaAdm() {
		return "KeySync/Administrador";
	}

	// ACESSANDO ÁREA DO PROF ATRAVÉS DA URL TEMPORARIAMENTE

	@GetMapping("/professor")
	public String AreaProf() {
		return "KeySync/Professor";
	}
	
	//ADICIONANDO LABORATÓRIO
	
	@GetMapping("/administrador/adicionarLaboratorio")
	public String adicionarLaboratorio(Laboratorio laboratorio) {
		return "KeySync/FormLaboratorio";
	}
	
	@PostMapping("/administrador/adicionarLaboratorio")
	private String adicionarLaboratorio(Long idLaboratorio, Laboratorio laboratorio, RedirectAttributes attributes) {
		System.out.println(laboratorio);
		lr.save(laboratorio);
		attributes.addFlashAttribute("mensagem", "Laboratório cadastrado com sucesso!");
		return "redirect:/administrador/adicionarLaboratorio";
	}
	
	//ADICIONANDO CHAVE
	
	@GetMapping("/administrador/adicionarChave")
	private String adicionarChave(Chave chave) {
		return "KeySync/FormChave";
	}
	
	@PostMapping("/administrador/adicionarChave")
	private String adicionarChave(Long idChave, Chave chave, RedirectAttributes attributes) {
		System.out.println(chave);
		cr.save(chave);
		attributes.addFlashAttribute("mensagem", "Chave cadastrada com sucesso!");
		return "redirect:/administrador/adicionarChave";
	}
	
	//ADICIONANDO PROFESSOR
	
	@GetMapping("/administrador/adicionarProfessor")
	private String adicionarProfessor(Professor professor) {
		return "KeySync/FormProfessor";
	}
	
	@PostMapping("/administrador/adicionarProfessor")
	private String adicionarProfessor(Long idProfessor, Professor professor, RedirectAttributes attributes) {
		System.out.println(professor);
		pr.save(professor);
		attributes.addFlashAttribute("mensagem", "Professor cadastrado com sucesso!");
		return "redirect:/administrador/adicionarProfessor";
	}
	
	//LISTANDO LABORATÓRIOS
	
	@GetMapping("/administrador/laboratorios")
	private ModelAndView listarLaboratorios() {
		
		List<Laboratorio> laboratorios = lr.findAll();
		ModelAndView mv = new ModelAndView("KeySync/Laboratorios");
		mv.addObject("laboratorios", laboratorios);
		return mv;
	}

}
