package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.ConfirmationTokenEntity;
import com.camayopolis.persistence.entity.UserEntity;
import com.camayopolis.persistence.repository.ConfirmationTokenRepository;
import com.camayopolis.persistence.repository.UserRepository;
import com.camayopolis.service.interfaces.IConfirmationTokenService;
import com.camayopolis.service.interfaces.IEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements IConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final IEmailService emailService;

    private ConfirmationTokenEntity generateTokenForUser(UserEntity userEntity) {
        String token = UUID.randomUUID().toString();
        ConfirmationTokenEntity confirmationToken = ConfirmationTokenEntity.builder()
                .contokToken(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .usu(userEntity)
                .build();

        confirmationTokenRepository.save(confirmationToken);
        return confirmationToken;
    }

    @Override
    public void confirmUserToken(String token) {
        ConfirmationTokenEntity confirmationToken = confirmationTokenRepository.findByContokToken(token)
                .orElseThrow(() -> new IllegalStateException("Token no encontrado"));

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("El token ha expirado");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);

        UserEntity userEntity = confirmationToken.getUsu();
        userEntity.setUsuActivado(true);
        userRepository.save(userEntity);
    }

    @Override
    public void sendConfirmationToken(UserEntity userEntity) throws MessagingException {

        ConfirmationTokenEntity newConfirmationToken = generateTokenForUser(userEntity);

        confirmationTokenRepository.save(newConfirmationToken);

        String confirmationLink = "http://localhost:8080/auth/confirm?token=" + newConfirmationToken.getContokToken();
        String message = "<p>Gracias por registrarte. Haz clic en el siguiente enlace para confirmar tu cuenta de CAMAYOPOLIS:</p>"
                + "<a href=\"" + confirmationLink + "\">Confirmar cuenta</a>";

        emailService.sendEmail(userEntity.getUsuCorreo(), "Confirmación de registro", message);
    }
}
