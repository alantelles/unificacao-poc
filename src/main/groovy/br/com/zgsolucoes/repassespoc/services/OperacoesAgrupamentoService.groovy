package br.com.zgsolucoes.repassespoc.services

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.domain.RegistroAbstrato
import br.com.zgsolucoes.repassespoc.enums.ConfigAgrupamento
import br.com.zgsolucoes.repassespoc.repositories.AgrupamentoRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class OperacoesAgrupamentoService {

    @Inject
    AgrupamentoRepository repo

    @Inject
    List<RegistroService> registroServices

    List<RegistroAbstrato> listarRegistrosPorAgrupamento(UUID id) {
        Agrupamento agrupamento = repo.findById(id).orElse(null)
        RegistroService registroServiceEntidade = registroServices.find {it.getConfigAgrupamento() == agrupamento.configAgrupamento }
        return registroServiceEntidade.listarPorAgrupamento(agrupamento)
    }

    RegistroAbstrato agruparRegistro(UUID id, UUID registroId) {
        Agrupamento agrupamento = repo.findById(id).orElse(null)
        RegistroService registroServiceEntidade = registroServices.find {it.getConfigAgrupamento() == agrupamento.configAgrupamento }
        return registroServiceEntidade.ajustarAgrupamento(registroId, agrupamento) as RegistroAbstrato
    }

    void deletarAgrupamento(UUID id) {
        Agrupamento agrupamento = repo.findById(id).get()
        RegistroService registroServiceEntidade = registroServices.find {it.getConfigAgrupamento() == agrupamento.configAgrupamento }
        registroServiceEntidade.desagruparTodos(agrupamento)
        repo.deleteById(agrupamento.id)
    }

}
