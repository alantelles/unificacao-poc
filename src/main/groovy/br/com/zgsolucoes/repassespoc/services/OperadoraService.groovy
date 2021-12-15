package br.com.zgsolucoes.repassespoc.services

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.domain.Operadora
import br.com.zgsolucoes.repassespoc.enums.ConfigAgrupamento
import br.com.zgsolucoes.repassespoc.enums.EstadoAnalise
import br.com.zgsolucoes.repassespoc.repositories.OperadoraRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class OperadoraService extends RegistroServiceAbstrato<Operadora> {

    @Inject
    OperadoraRepository repo

    @Inject
    AgrupamentoService agrupamentoService

    final ConfigAgrupamento configAgrupamento = ConfigAgrupamento.OPERADORA

    Operadora salvar(Map<String, String> body) {
        Operadora operadora = new Operadora(*:body)
        return salvarInferindoAgrupamento(operadora)
    }

    Operadora ajustarAgrupamento(UUID registroId, Agrupamento agrupamento) {
        Operadora registro = repo.findById(registroId).orElseThrow(e -> new UnsupportedOperationException("Registro $registroId não existe"))
        registro.setAgrupamento(agrupamento)
        registro.estadoAnalise = EstadoAnalise.AGRUPADO
        return repo.update(registro)
    }

    Operadora desagrupar(UUID registroId) {
        Operadora registro = repo.findById(registroId).orElseThrow(e -> new UnsupportedOperationException("Registro $registroId não existe"))
        if (agrupamentoService.isReferencia(registro)) {
            throw new UnsupportedOperationException("O registro $registroId é referência do agrupamento $registro.agrupamento.id e por isso não pode ser removido do agrupamento.")
        }
        registro.setAgrupamento(null)
        registro.estadoAnalise = EstadoAnalise.EM_ANALISE
        return repo.update(registro)
    }

//    List<Operadora> desagruparTodos(Agrupamento agrupamento) {
//        List<Operadora> operadoras = repo.findByAgrupamento(agrupamento)
//        operadoras.each {
//            it.setAgrupamento(null)
//            it.estadoAnalise = EstadoAnalise.EM_ANALISE
//        }
//        return repo.updateAll(operadoras)
//    }

    Agrupamento tornarReferencia(UUID id) {
        Operadora registro = repo.findById(id).orElseThrow(e -> new UnsupportedOperationException("Registro $registroId não existe"))
        if (!registro.agrupamento) {
            throw new UnsupportedOperationException("O registro $registro.id não faz parte de algum agrupamento, portanto não pode se tornar referência.")
        }
        return agrupamentoService.ajustarReferencia(registro)
    }

    List<Agrupamento> inferirAgrupamento(Operadora registro) {
        List<Agrupamento> agrupamentosEntidade = agrupamentoService.encontrarPorEntidade(ConfigAgrupamento.OPERADORA)
        List<Operadora> operadoras = repo.findByEstadoAnaliseAndIdIn(EstadoAnalise.AGRUPADO, agrupamentosEntidade*.referencia)
        List<Agrupamento> agrupaveisAutomaticamente = operadoras.findAll {
            (it.cnpj == registro.cnpj && it.ans == registro.ans) ||
            (it.cnpj == registro.cnpj && it.razaoSocial == registro.razaoSocial)
        }

//        List<Agrupamento> agrupamentos = agrupamentoService.encontrarPorEntidade(ConfigAgrupamento.OPERADORA)
//        List<String> args = configAgrupamento.camposReferencia.collect {registro[it] as String}
//        List<Operadora> registros = repo.findByAnsAndCnpjAndIdIn(*args, agrupamentos*.referencia)
//        return registros.findAll{it.agrupamento}.collect{it.agrupamento}
    }




}
