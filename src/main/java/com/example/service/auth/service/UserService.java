package com.example.service.auth.service;

import com.example.service.auth.config.TokenConfig.TokenService;
import com.example.service.auth.config.exceptions.AuthExceptions;
import com.example.service.auth.domain.User;
import com.example.service.auth.model.DataLoginDTO;
import com.example.service.auth.model.DataToken;
import com.example.service.auth.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService service;
    public DataToken login(DataLoginDTO dto) {
        log.info("Performing a login");
        var user = new UsernamePasswordAuthenticationToken(dto.login(),dto.password());
        var userAuth = manager.authenticate(user);
        return new DataToken(service.generatesToken((User)userAuth.getPrincipal()));
    }

    public DataLoginDTO register(DataLoginDTO dto) {
        log.info("registering a new login");
        if(repository.existsByLogin(dto.login())){
            log.error("error registering a new login");
            throw new AuthExceptions();
        }
        var user = repository.save(new User(dto.login(),this.encoder(dto.password())));
        return new DataLoginDTO(user.getLogin(), user.getPassword());


    }

    private String encoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public void valid(String token, HttpServletResponse response) {
        log.info("validating login");
        this.service.getToken(token);
    }
}
