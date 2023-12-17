package ProjetoFinal.pi.KeySync.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KeySyncController {

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

}
