package io.github.viniciusboos.projetofinal.modelo.uf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        uf.setCodigo(uf.getCodigo());
        uf.setNome(ufDTO.getNome());
        uf.setSigla(ufDTO.getSigla());
        uf.setStatus(ufDTO.getStatus());

        return uf;
    }
}
