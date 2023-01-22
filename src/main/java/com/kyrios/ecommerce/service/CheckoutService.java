package com.kyrios.ecommerce.service;

import com.kyrios.ecommerce.dto.Purchase;
import com.kyrios.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
