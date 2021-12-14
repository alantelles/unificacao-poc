package br.com.zgsolucoes.repassespoc.domain

import br.com.zgsolucoes.repassespoc.enums.EstadoAnalise
import groovy.transform.EqualsAndHashCode

import javax.persistence.MappedSuperclass

@MappedSuperclass
@EqualsAndHashCode
abstract class RegistroAbstrato extends ModeloAbstrato {

    String idRepoDados
    String codigoHis
    EstadoAnalise estadoAnalise
    String datasource

}
