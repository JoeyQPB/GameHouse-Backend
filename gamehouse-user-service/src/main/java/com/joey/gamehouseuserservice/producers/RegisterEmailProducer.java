package com.joey.gamehouseuserservice.producers;

import com.joey.gamehouseuserservice.domain.User;
import com.joey.gamehouseuserservice.domain.records.UserEmailProducerDTO;
import com.joey.gamehouseuserservice.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterEmailProducer {

    @Autowired
    private RabbitMQService rabbitMQService;

    public void emailProducer (User data) {
        List<GrantedAuthority> roleList = (List<GrantedAuthority>) data.getAuthorities();
        UserEmailProducerDTO dto =
                new UserEmailProducerDTO(
                        data.getEmail(),
                        roleList.toString(),
                        data.getId());
        this.rabbitMQService.sendRegisterEmail(dto);
    }

}
