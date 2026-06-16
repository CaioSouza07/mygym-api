package com.api.mygym.infra.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendWelcomeEmail(String to, String name) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                true,
                "UTF-8"
        );

        helper.setTo(to);
        helper.setSubject("Bem-vindo ao MyGym 💪");

        String html = """
        <div style="font-family: Arial, sans-serif; text-align: center;">
            <h2>Bem-vindo, %s! 💪</h2>

            <p>Seu cadastro foi realizado com sucesso.</p>
            <p>Agora você já pode montar seus treinos 🚀</p>

            <br>

            <img src="cid:logoMyGym"
                 alt="MyGym"
                 style="max-width: 250px; height: auto;">

            <br><br>

            <b>Vamos evoluir juntos!</b>

            <br><br>

            <span style="color: red; font-size: 12px;">
                E-mail automático, favor não responder.
            </span>
        </div>
        """.formatted(name);

        helper.setText(html, true);

        ClassPathResource logo = new ClassPathResource("static/images/mygym_logo.png");

        helper.addInline("logoMyGym", logo);

        mailSender.send(message);
    }
}
