package tech.nocountry.roadbites.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.roadbites.controller.dto.PlaceOrderDTO;
import tech.nocountry.roadbites.domain.model.Order;
import tech.nocountry.roadbites.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(PlaceOrderDTO order) {
        return orderService.placeOrder(order);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }
}
