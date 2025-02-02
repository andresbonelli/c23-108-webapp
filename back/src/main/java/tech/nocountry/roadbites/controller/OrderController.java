package tech.nocountry.roadbites.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.roadbites.controller.dto.order.OrderResponseDTO;
import tech.nocountry.roadbites.controller.dto.order.PlaceOrderDTO;
import tech.nocountry.roadbites.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO placeOrder(@RequestBody PlaceOrderDTO order) {
        return orderService.placeOrder(order);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/user/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDTO> getAllOrdersByUser(@PathVariable String userName) {
        return orderService.getAllByUser(userName);
    }
}
