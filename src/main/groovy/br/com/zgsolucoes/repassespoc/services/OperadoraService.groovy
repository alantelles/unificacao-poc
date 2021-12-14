package br.com.zgsolucoes.repassespoc.services

import br.com.zgsolucoes.repassespoc.domain.Operadora
import br.com.zgsolucoes.repassespoc.repositories.OperadoraRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class OperadoraService implements RegistroService<Operadora> {

    @Inject
    OperadoraRepository repo

    List<Operadora> listarTodos() {
        return repo.findAll()
    }

    Optional<Operadora> encontrarUm(UUID id) {
        return repo.findById(id)
    }

    Operadora salvar(Map<String, String> body) {
        Operadora operadora = new Operadora(*:body)
        return repo.save(operadora)
    }

}
