package br.com.zgsolucoes.repassespoc.services

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.domain.RegistroAbstrato
import br.com.zgsolucoes.repassespoc.enums.ConfigAgrupamento
import br.com.zgsolucoes.repassespoc.repositories.AgrupamentoRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class AgrupamentoService {

    @Inject
    AgrupamentoRepository repo

    List<Agrupamento> encontrarPorEntidade(ConfigAgrupamento configAgrupamento) {
        return repo.findByConfigAgrupamento(configAgrupamento)
    }

    Agrupamento salvar(Map<String, Object> body) {
        return repo.save(new Agrupamento().tap {
            it.configAgrupamento = ConfigAgrupamento.valueOf(body.configAgrupamento as String)
            it.referencia = UUID.fromString(body.referencia as String)
        })
    }

    Optional<Agrupamento> encontrarUm(UUID id) {
        return repo.findById(id)
    }

    Boolean isReferencia(RegistroAbstrato registro) {
        return repo.findByReferencia(registro.id).orElse(null) as Boolean
    }

    Agrupamento ajustarReferencia(RegistroAbstrato registro) {
        Agrupamento agrupamento = repo.findById(registro.agrupamento.id).get()
        agrupamento.referencia = registro.id
        return repo.update(agrupamento)
    }

}
