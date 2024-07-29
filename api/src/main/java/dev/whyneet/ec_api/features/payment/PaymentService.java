package dev.whyneet.ec_api.features.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import dev.whyneet.ec_api.core.dtos.product.PurchaseProductsDto;
import dev.whyneet.ec_api.core.entities.Product;
import dev.whyneet.ec_api.features.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    @Autowired
    private ProductService productService;

    public Session purchaseProducts(List<PurchaseProductsDto.ProductPurchaseDataDto> productIds, String userId) throws StripeException {
        List<Product> products = productService.getProductsById(productIds.stream()
                .map(PurchaseProductsDto.ProductPurchaseDataDto::id).collect(Collectors.toList()));

        List<SessionCreateParams.LineItem> items = products.stream()
                .map(product -> SessionCreateParams.LineItem.builder()
                        .setQuantity((long) productIds.stream().filter(p -> Objects.equals(p.id(), product.getId()))
                                .findFirst().get().quantity())
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName(product.getName()).setDescription(product.getDescription()).build())
                                .setUnitAmount((long) product.getPrice())
                                .build()).build()).collect(Collectors.toList());

        SessionCreateParams params = SessionCreateParams.builder()
                .setSuccessUrl("http://localhost:8080/api/purchase/success")
                .setCancelUrl("http://localhost:8080/api/purchase/cancel").setMode(SessionCreateParams.Mode.PAYMENT)
                .addAllLineItem(items)
                .putMetadata("productIds", products.stream().map(Product::getId).collect(Collectors.joining(",")))
                .putMetadata("productQuantities", productIds.stream()
                        .map(PurchaseProductsDto.ProductPurchaseDataDto::quantity).map(Object::toString)
                        .collect(Collectors.joining(",")))
                .putMetadata("userId", userId)
                .build();

        Session session = Session.create(params);

        return session;
    }
}
