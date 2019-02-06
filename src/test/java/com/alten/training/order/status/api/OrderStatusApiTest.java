package com.alten.training.order.status.api;

import com.alten.training.order.status.model.Order;
import com.alten.training.order.status.repo.OrderRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OrderStatusApiTest {


    private WebTestClient rest;

    @InjectMocks
    private OrderStatusApi api;

    @Mock
    private OrderRepo repo;

    @Before
    public void setup() {
        this.rest = WebTestClient
                .bindToController(api)
                .configureClient()
                .build();
    }

    @Test
    public void shouldReturn404IfOrderIdIsNotFound() {
        final Long orderId = 1234L;

        when(repo.findById(orderId)).thenReturn(empty());

        rest.get()
                .uri("/status/"+orderId)
                .exchange()
                .expectStatus().isNotFound();

        verify(repo, times(1)).findById(orderId);
    }

    @Test
    public void shouldReturn200AndOrderDetails() {
       final Long orderId = 12345L;
       final String description = "Hello Description";

        when(repo.findById(orderId)).thenReturn(
                of(Order.builder()
                        .id(orderId)
                        .description(description)
                        .build()
                ));

        rest
                .get().uri("/status/"+orderId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(orderId)
                .jsonPath("$.description").isEqualTo(description);

        verify(repo, times(1)).findById(orderId);
    }
}
