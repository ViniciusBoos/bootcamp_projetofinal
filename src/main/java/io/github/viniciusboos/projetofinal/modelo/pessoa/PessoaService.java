package io.github.viniciusboos.projetofinal.modelo.pessoa;

import io.github.viniciusboos.projetofinal.modelo.pessoa.PessoaDTO;
import io.github.viniciusboos.projetofinal.modelo.pessoa.Pessoa;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    public static Pessoa criarPessoa(PessoaDTO pessoaDTO) {

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setSobrenome(pessoaDTO.getSobreNome());
        pessoa.setIdade(Integer.parseInt(pessoaDTO.getIdade()));
        pessoa.setStatus(Integer.parseInt(pessoaDTO.getStatus()));
        pessoa.setLogin(pessoaDTO.getLogin());
        pessoa.setSenha(pessoaDTO.getSenha());

        return pessoa;
    }
}
