package com.camayopolis.service.interfaces;

import com.camayopolis.persistence.entity.UserEntity;
import jakarta.mail.MessagingException;

public interface IConfirmationTokenService {
    void confirmUserToken(String token);
    void sendConfirmationToken(UserEntity userEntity) throws MessagingException;
}
