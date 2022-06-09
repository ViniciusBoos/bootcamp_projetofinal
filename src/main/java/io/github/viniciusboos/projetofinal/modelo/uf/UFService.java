package io.github.viniciusboos.projetofinal.modelo.uf;

import org.springframework.stereotype.Service;

@Service
public class UFService {

    public static UF criarUF(UFDTO ufDTO) {

        UF uf = new UF();
        uf.setNome(ufDTO.getNome());
        uf.setSigla(ufDTO.getSigla());
        uf.setStatus(ufDTO.getStatus());

        return uf;
    }

    public static UF alterarUF(UF uf, UFDTO ufDTO) {

        uf.setCodigoUF(uf.getCodigoUF());
        uf.setNome(ufDTO.getNome());
        uf.setSigla(ufDTO.getSigla());
        uf.setStatus(ufDTO.getStatus());

        return uf;
    }
}
