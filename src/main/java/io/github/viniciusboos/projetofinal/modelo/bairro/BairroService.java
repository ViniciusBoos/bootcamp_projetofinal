package io.github.viniciusboos.projetofinal.modelo.bairro;

import io.github.viniciusboos.projetofinal.modelo.municipio.Municipio;

public class BairroService {

    public static Bairro criarBairro(BairroDTO bairroDTO, Municipio municipio) {

        Bairro bairro = new Bairro();
        bairro.setMunicipio(municipio);
        bairro.setNome(bairroDTO.getNome());
        bairro.setStatus(bairroDTO.getStatus());

        return bairro;
    }

    public static Bairro alterarBairro(BairroDTO bairroDTO, Bairro bairro, Municipio municipio) {

        bairro.setCodigoBairro(bairroDTO.getCodigoBairro());
        bairro.setMunicipio(municipio);
        bairro.setNome(bairroDTO.getNome());
        bairro.setStatus(bairroDTO.getStatus());

        return bairro;
    }
}
