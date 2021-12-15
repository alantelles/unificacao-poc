package br.com.zgsolucoes.repassespoc.services

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.enums.EstadoAnalise

abstract class RegistroServiceAbstrato<T> implements RegistroService<T> {

    Optional<T> encontrarUm(UUID id) {
        return repo.findById(id)
    }

    List<T> listarTodos() {
        return repo.findAll() as List<T>
    }

    List<T> listarPorEstadoAnalise(EstadoAnalise estadoAnalise) {
        return repo.findByEstadoAnalise(estadoAnalise)
    }

    List<T> listarPorAgrupamento(Agrupamento agrupamento) {
        return repo.findByAgrupamento(agrupamento)
    }

    List<T> desagruparTodos(Agrupamento agrupamento) {
        List<T> registros = repo.findByAgrupamento(agrupamento)
        registros.each {
            it.setAgrupamento(null)
            it.estadoAnalise = EstadoAnalise.EM_ANALISE
        }
        return repo.updateAll(registros) as List<T>
    }

    protected T salvarComAgrupamento(T registro, Agrupamento agrupamento) {
        registro.setAgrupamento(agrupamento)
        registro.estadoAnalise = EstadoAnalise.AGRUPADO
        return repo.save(registro)
    }

    protected T salvarComNovoAgrupamento(T registro) {
        repo.save(registro)
        Agrupamento agrupamento = agrupamentoService.salvar([configAgrupamento: configAgrupamento, referencia: registro.id])
        registro.estadoAnalise = EstadoAnalise.AGRUPADO
        registro.setAgrupamento(agrupamento)
        return repo.update(registro)
    }

    protected T salvarInferindoAgrupamento(T registro) {
        List<Agrupamento> agrupamentosPossiveis = inferirAgrupamento(registro)
        if (agrupamentosPossiveis.size() == 1) {
            return salvarComAgrupamento(registro, agrupamentosPossiveis[0])
        } else if (agrupamentosPossiveis.size() == 0) {
            return salvarComNovoAgrupamento(registro)
        }
        return repo.save(registro)
    }

}
