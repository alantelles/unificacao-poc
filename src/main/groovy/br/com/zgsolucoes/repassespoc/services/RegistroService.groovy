package br.com.zgsolucoes.repassespoc.services

interface RegistroService<T> {

    List<T> listarTodos()

    Optional<T> encontrarUm(UUID id)

    T salvar(Map<String, String> obj)

}
