package ProjetoFinal.pi.KeySync.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetoFinal.pi.KeySync.Models.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>{
	
	//Esse repository tem a capacidade de salvar, buscar... Alunos no meu banco de dados
	//Isso porque ele extende o repositório JpaRepository que tem todas essas funcionalidades

}
