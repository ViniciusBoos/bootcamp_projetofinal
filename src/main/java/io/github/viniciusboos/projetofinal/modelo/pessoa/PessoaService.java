package io.github.viniciusboos.projetofinal.modelo.pessoa;

import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    public static Pessoa criarPessoa(PessoaDTO pessoaDTO) {

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setSobrenome(pessoaDTO.getSobrenome());
        pessoa.setIdade(pessoaDTO.getIdade());
        pessoa.setStatus(pessoaDTO.getStatus());
        pessoa.setLogin(pessoaDTO.getLogin());
        pessoa.setSenha(pessoaDTO.getSenha());

        return pessoa;
    }

    public static Pessoa alterarPessoa(PessoaDTO pessoaDTO, Pessoa pessoa) {

        pessoa.setCodigoPessoa(pessoaDTO.getCodigoPessoa());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setSobrenome(pessoaDTO.getSobrenome());
        pessoa.setIdade(pessoaDTO.getIdade());
        pessoa.setStatus(pessoaDTO.getStatus());
        pessoa.setLogin(pessoaDTO.getLogin());
        pessoa.setSenha(pessoaDTO.getSenha());

        return pessoa;
    }
}
