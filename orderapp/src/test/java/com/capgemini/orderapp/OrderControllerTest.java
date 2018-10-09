package com.capgemini.orderapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.order.controller.OrderController;
import com.capgemini.order.entity.Order;
import com.capgemini.order.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderControllerTest {

	@InjectMocks
	private OrderController orderController;

	@Mock
	private OrderService orderService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@Test
	public void testAddOrder() throws Exception {
		Order order = new Order(1344, 12345, 1244, LocalDate.of(2018, 06, 02));
		when(orderService.addOrder(Mockito.isA(Order.class))).thenReturn(order);
		String content = "{\r\n" + "  \"orderId\": 1344,\r\n" + "  \"customerId\": 12345,\r\n"
				+ "  \"productId\": 1244,\r\n" + "  \"date\": \"2018-06-02\"\r\n" + "}";
		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.orderId").exists())
				.andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.orderId").value(1344))
				.andExpect(jsonPath("$.customerId").value(12345))
				.andExpect(jsonPath("$.productId").value(1244))
				.andDo(print());
	}
	@Test
	public void testgetOrderById() throws Exception {
		when(orderService.getOrderById(1344)).thenReturn(new Order(1344, 12345, 1244, LocalDate.of(2018, 06, 02)) );
		mockMvc.perform(MockMvcRequestBuilders.get("/orders/1344").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orderId").exists())
				.andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.date").exists())
				.andExpect(jsonPath("$.orderId").value(1344))
				.andExpect(jsonPath("$.customerId").value(12345))
				.andExpect(jsonPath("$.productId").value(1244))
				.andDo(print());
	}
	@Test
	public void testDeleteOrder() throws Exception {
		Order order = new Order(1344, 12345, 1244, LocalDate.of(2015, 05, 03));
		when(orderService.getOrderById(1344)).thenReturn(order);

		mockMvc.perform(delete("/order/1344").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk());

		verify(orderService, times(1)).cancelOrder(order);
	}
}
