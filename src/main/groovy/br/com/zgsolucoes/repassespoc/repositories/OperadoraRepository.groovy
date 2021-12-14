package br.com.zgsolucoes.repassespoc.repositories

import br.com.zgsolucoes.repassespoc.domain.Operadora
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface OperadoraRepository extends CrudRepository<Operadora, UUID> {

    List<Operadora> findAll()
    Optional<Operadora> findById(UUID id)

    Operadora save(Operadora operadora)
}
