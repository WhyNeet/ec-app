package dev.whyneet.ec_api.features.seller;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.dtos.seller.CreateSellerDto;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.frameworks.exception.exceptions.SellerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    private IDataServices dataServices;
    @Autowired
    private SellerFactory sellerFactory;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public Seller createSeller(CreateSellerDto createSellerDto) throws SellerException.SellerAlreadyExists {
        Seller seller = sellerFactory.fromCreateDto(createSellerDto);

        String hashedPassword = passwordEncoder.encode(seller.getPassword());
        String hashedTaxId = passwordEncoder.encode(seller.getTaxId());

        seller.setPassword(hashedPassword);
        seller.setTaxId(hashedTaxId);

        try {
            seller = dataServices.sellers().save(seller);
        } catch (DuplicateKeyException ex) {
            throw new SellerException.SellerAlreadyExists();
        }

        return seller;
    }

    public Optional<Seller> getSellerById(String sellerId) {
        return dataServices.sellers().getById(sellerId);
    }

    public Optional<Seller> getSellerByShortBusinessName(String sellerShortBusinessName) {
        return dataServices.sellers().findOne(Example.of(new Seller(null, null, sellerShortBusinessName, null, null, null, null, null)));
    }
}
