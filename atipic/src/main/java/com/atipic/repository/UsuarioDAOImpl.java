package com.atipic.repository;
import org.springframework.stereotype.Repository;

import com.atipic.model.Usuario;

@Repository
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Long> {

    public UsuarioDAOImpl(){
        super(Usuario.class);
    }

    @Override
    protected Long getEntityId(Usuario entity){
        return entity.getId();
    }

}
