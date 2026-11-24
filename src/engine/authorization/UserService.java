package engine.authorization;

import engine.authorization.DTO.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService (UserRepository userRepository,
                        PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save (RegisterRequest request){
        if (userRepository.existsByEmailIgnoreCase(request.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(request.password());

        userRepository.save(new UserEntity(
                request.email(),
                encodedPassword
        ));
    }
}
