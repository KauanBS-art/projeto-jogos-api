package com.kauangamestore.service;

import com.kauangamestore.model.Usuario;

public interface JwtService {
    String generateJwt(Usuario usuario);
}
