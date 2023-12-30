package ProjetoFinal.pi.KeySync.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ProjetoFinal.pi.KeySync.Models.Agendamento;
import ProjetoFinal.pi.KeySync.Models.Chave;
import ProjetoFinal.pi.KeySync.Models.Laboratorio;
import ProjetoFinal.pi.KeySync.Models.Professor;
import ProjetoFinal.pi.KeySync.Repositories.AgendamentoRepositoty;
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
	@Autowired
	private AgendamentoRepositoty ar;

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

	// ADICIONANDO LABORATÓRIO

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

	// ADICIONANDO CHAVE

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

	// ADICIONANDO PROFESSOR

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

	// LISTANDO LABORATÓRIOS

	@GetMapping("/administrador/laboratorios")
	private ModelAndView listarLaboratorios() {

		List<Laboratorio> laboratorios = lr.findAll();
		ModelAndView mv = new ModelAndView("KeySync/Laboratorios");
		mv.addObject("laboratorios", laboratorios);
		return mv;
	}

	// EDITANDO LABORATÓRIO

	@GetMapping("/administrador/selecionarLaboratorio/{id}")
	public ModelAndView selecionarLaboratorio(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Laboratorio> opt = lr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/KeySync/Laboratorios");
			return md;
		}

		Laboratorio laboratorio = opt.get();

		md.setViewName("KeySync/FormLaboratorio");
		md.addObject("laboratorio", laboratorio);

		return md;

	}

	// APAGANDO LABORATÓRIO

	@GetMapping("/administrador/removerLaboratorio/{id}")
	public String apagarLaboratorio(@PathVariable Long id, RedirectAttributes attributes) {
		Optional<Laboratorio> opt = lr.findById(id);
		Laboratorio laboratorio = opt.get();

		if (!opt.isEmpty()) {
			lr.delete(laboratorio);
			attributes.addFlashAttribute("mensagem", "Laboratorio removido com sucesso!");
		}

		return "redirect:/administrador/laboratorios";
	}

	// LISTANDO CHAVES

	@GetMapping("/administrador/chaves")
	private ModelAndView listarChaves() {

		List<Chave> chaves = cr.findAll();
		ModelAndView mv = new ModelAndView("KeySync/Chaves");
		mv.addObject("chaves", chaves);
		return mv;
	}

	// EDITANDO CHAVE

	@GetMapping("/administrador/selecionarChave/{id}")
	public ModelAndView selecionarChave(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Chave> opt = cr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/KeySync/Chaves");
			return md;
		}

		Chave chave = opt.get();

		md.setViewName("KeySync/FormChave");
		md.addObject("chave", chave);

		return md;

	}

	// APAGANDO CHAVE

	@GetMapping("/administrador/removerChave/{id}")
	public String apagarChave(@PathVariable Long id, RedirectAttributes attributes) {
		Optional<Chave> opt = cr.findById(id);
		Chave chave = opt.get();

		if (!opt.isEmpty()) {
			cr.delete(chave);
			attributes.addFlashAttribute("mensagem", "Chave removida com sucesso!");
		}

		return "redirect:/administrador/chaves";
	}

	// LISTANDO PROFESSORES

	@GetMapping("/administrador/professores")
	private ModelAndView listarProfessores() {

		List<Professor> professores = pr.findAll();
		ModelAndView mv = new ModelAndView("KeySync/Professores");
		mv.addObject("professores", professores);
		return mv;
	}

	// EDITANDO PROFESSOR

	@GetMapping("/administrador/selecionarProfessor/{id}")
	public ModelAndView selecionarProfessor(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Professor> opt = pr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/KeySync/Professores");
			return md;
		}

		Professor professor = opt.get();

		md.setViewName("KeySync/FormProfessor");
		md.addObject("professor", professor);

		return md;

	}

	// APAGANDO PROFESSOR

	@GetMapping("/administrador/removerProfessor/{id}")
	public String apagarProfessor(@PathVariable Long id, RedirectAttributes attributes) {
		Optional<Professor> opt = pr.findById(id);
		Professor professor = opt.get();

		if (!opt.isEmpty()) {
			pr.delete(professor);
			attributes.addFlashAttribute("mensagem", "Professor removido com sucesso!");
		}

		return "redirect:/administrador/professores";
	}

	// LISTANDO LABORATÓRIOS PARA PROFESSORES

	@GetMapping("/professor/laboratorios")
	private ModelAndView listarLaboratoriosParaProf() {

		List<Laboratorio> laboratorios = lr.findAll();
		ModelAndView mv = new ModelAndView("KeySync/LaboratoriosParaProf");
		mv.addObject("laboratorios", laboratorios);
		return mv;
	}

	// LISTANDO CHAVES PARA PROFESSORES

	@GetMapping("/professor/chaves")
	private ModelAndView listarChavesParaProf() {

		List<Chave> chaves = cr.findAll();
		ModelAndView mv = new ModelAndView("KeySync/ChavesParaProf");
		mv.addObject("chaves", chaves);
		return mv;
	}

	// ADICIONANDO AGENDAMENTO

	@RequestMapping("/professor/agendamento")
	public String agendamento() {
		return "KeySync/agendamento";

	}

	@PostMapping("/professor")
	public String adicionar(Agendamento agendamento) {

		System.out.println(agendamento);
		ar.save(agendamento);
		return "KeySync/agendamento-adicionado";
	}

	// LISTANDO AGENDAMENTO
	@GetMapping("/KeySync/lista")
	public ModelAndView listarA() {
		List<Agendamento> agendamentos = ar.findAll();
		ModelAndView mv = new ModelAndView("/KeySync/lista");
		mv.addObject("agendamentos", agendamentos);
		return mv;
	}

	// APAGANDO AGENDAMENTO
	@GetMapping("agendamentos/{id}/remover")
	public String apagarAgendamento(@PathVariable Long id) {
		Optional<Agendamento> opt = ar.findById(id);

		if (!opt.isEmpty()) {
			Agendamento agendamento = opt.get();
			ar.delete(agendamento);
		}
		return "redirect:/KeySync/lista";
	}

}
