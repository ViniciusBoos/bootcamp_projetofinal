package io.github.viniciusboos.projetofinal.modelo.municipio;

import io.github.viniciusboos.projetofinal.modelo.uf.UF;
import io.github.viniciusboos.projetofinal.modelo.uf.UFDTO;

public class MunicipioService {

    public static Municipio criarMunicipio(MunicipioDTO municipioDTO, UF uf) {

        Municipio municipio = new Municipio();
        municipio.setUf(uf);
        municipio.setNome(municipioDTO.getNome());
        municipio.setStatus(municipioDTO.getStatus());

        return municipio;
    }

    public static Municipio alterarMunicipio(Municipio municipio, MunicipioDTO municipioDTO, UF uf) {

        municipio.setCodigoMunicipio(municipioDTO.getCodigoMunicipio());
        municipio.setUf(uf);
        municipio.setNome(municipioDTO.getNome());
        municipio.setStatus(municipioDTO.getStatus());

        return municipio;
    }
}
