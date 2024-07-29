package dev.whyneet.ec_api.controllers;

import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationConfiguration;
import dev.whyneet.ec_api.features.order.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private IApplicationConfiguration configuration;
    @Autowired
    private OrderService orderService;

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
            List<Integer> productQuantities = Arrays.stream(paymentIntent.getMetadata().get("productQuantities")
                    .split(",")).map(Integer::parseInt).toList();
            String userId = paymentIntent.getMetadata().get("userId");

            for (int i = 0; i < productIds.length; i++) {
                orderService.createOrder(productIds[i], userId, productQuantities.get(i));
            }
        }
    }
}
