package br.com.zgsolucoes.repassespoc.enums

import br.com.zgsolucoes.repassespoc.services.OperadoraService


enum ConfigAgrupamento {

    OPERADORA("OPERADORA", ['ans', 'cnpj'], OperadoraService)

    List<String> camposReferencia = []
    String entidade
    Class<?> classeEntidade

    ConfigAgrupamento(String entidade, List<String> camposReferencia, Class classeEntidade) {
        this.entidade = entidade
        this.camposReferencia = camposReferencia
        this.classeEntidade = classeEntidade
    }

}
