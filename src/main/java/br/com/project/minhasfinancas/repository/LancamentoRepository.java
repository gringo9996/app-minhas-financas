package br.com.project.minhasfinancas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.minhasfinancas.domain.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
