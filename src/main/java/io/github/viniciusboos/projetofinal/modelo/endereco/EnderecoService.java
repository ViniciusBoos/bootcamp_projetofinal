package io.github.viniciusboos.projetofinal.modelo.endereco;

import io.github.viniciusboos.projetofinal.modelo.bairro.Bairro;
import io.github.viniciusboos.projetofinal.modelo.exception.DadoInvalidoException;
import io.github.viniciusboos.projetofinal.modelo.pessoa.Pessoa;
import io.github.viniciusboos.projetofinal.modelo.pessoa.PessoaDTO;
import io.github.viniciusboos.projetofinal.repository.BairroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnderecoService {

    public static Endereco criarEnderecos(EnderecoDTO enderecoDTO,
                                          Pessoa pessoa,
                                          BairroRepository bairroRepository) {

        Optional<Bairro> possivelBairro = bairroRepository.findByCodigoBairro(enderecoDTO.getCodigoBairro());

        if(possivelBairro.isPresent()) {

            Endereco endereco = new Endereco();
            endereco.setPessoa(pessoa);
            endereco.setBairro(possivelBairro.get());
            endereco.setNomeRua(enderecoDTO.getNomeRua());
            endereco.setNumero(enderecoDTO.getNumero());
            endereco.setComplemento(enderecoDTO.getComplemento());
            endereco.setCep(enderecoDTO.getCep());

            return endereco;
        }
        throw new DadoInvalidoException("Bairro do código " + enderecoDTO.getCodigoBairro() + " não existe");
    }

    public static Endereco alterarEndereco(EnderecoDTO enderecoDTO,
                                           Pessoa pessoa,
                                           BairroRepository bairroRepository) {

        Optional<Bairro> possivelBairro = bairroRepository.findByCodigoBairro(enderecoDTO.getCodigoBairro());

        if(possivelBairro.isPresent()) {

            Endereco endereco = new Endereco();
            endereco.setPessoa(pessoa);
            endereco.setCodigoEndereco(enderecoDTO.getCodigoEndereco());
            endereco.setBairro(possivelBairro.get());
            endereco.setNomeRua(enderecoDTO.getNomeRua());
            endereco.setNumero(enderecoDTO.getNumero());
            endereco.setComplemento(enderecoDTO.getComplemento());
            endereco.setCep(enderecoDTO.getCep());

            return endereco;
        }
        throw new DadoInvalidoException("Bairro do código " + enderecoDTO.getCodigoBairro() + " não existe");
    }
}
