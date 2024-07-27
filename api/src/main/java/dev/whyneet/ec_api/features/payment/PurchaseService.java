package dev.whyneet.ec_api.features.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import dev.whyneet.ec_api.core.entities.Product;
import dev.whyneet.ec_api.features.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {
    @Autowired
    private ProductService productService;

    public Session purchaseProducts(List<String> productIds) throws StripeException {
        List<Product> products = productService.getProductsById(productIds);

        SessionCreateParams params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
                .addAllLineItem(products.stream()
                        .map(product -> SessionCreateParams.LineItem.builder().setPrice(product.getPrice().toString())
                                .build()).collect(Collectors.toList())).build();

        Session session = Session.create(params);

        return session;
    }
}
