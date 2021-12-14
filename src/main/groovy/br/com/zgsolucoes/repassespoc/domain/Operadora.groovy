package br.com.zgsolucoes.repassespoc.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(includeNames = true)
@Table(name = "operadoras")
class Operadora extends RegistroAbstrato {

    String razaoSocial
    String ans
    String cnpj

}
