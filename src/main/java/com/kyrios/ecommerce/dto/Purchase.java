package com.kyrios.ecommerce.dto;

import com.kyrios.ecommerce.entity.Address;
import com.kyrios.ecommerce.entity.Customer;
import com.kyrios.ecommerce.entity.Order;
import com.kyrios.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
