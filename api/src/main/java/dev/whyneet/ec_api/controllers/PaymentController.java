package dev.whyneet.ec_api.controllers;

import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private IApplicationConfiguration configuration;

    @PostMapping("/webhook")
    public void paymentWebhook(HttpServletRequest request) throws IOException, SignatureVerificationException, EventDataObjectDeserializationException {
        String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String header = request.getHeader("Stripe-Signature");

        Event event = Webhook.constructEvent(payload, header, configuration.payments().webhookSecret());

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = dataObjectDeserializer.deserializeUnsafe();

        if (event.getType().equals("payment_intent.succeeded")) {
            PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
            String[] productIds = paymentIntent.getMetadata().get("productIds").split(",");
            // TODO: add orders to the database
        }
    }
}
