package com.visco9.controllers;

import com.visco9.domain.Customer;
import com.visco9.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.*;

/**
 * Created by x on 3/4/2017.
 */
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getCustomer() throws Exception {
        Integer id = 1;
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());
        when(customerService.getById(id)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer"))
                .andExpect(model().attribute("customer",instanceOf(Customer.class)));
    }

    @Test
    public void newCustomer() throws Exception {
        verifyZeroInteractions(customerService);
        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerform"))
                .andExpect(model().attribute("customer",instanceOf(Customer.class)));
    }

    @Test
    public void saveOrUpdateCustomer() throws Exception {
        Customer c = new Customer();
        c.setId(1);
        c.setPhone("123456789");
        c.setZipcode("11111");
        c.setFirstname("Bob");
        when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(c);
        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("phone", "123456789")
                .param("zipcode", "11111")
                .param("firstname", "Bob"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(1))))
                .andExpect(model().attribute("customer", hasProperty("phone", is("123456789"))))
                .andExpect(model().attribute("customer", hasProperty("zipcode", is("11111"))))
                .andExpect(model().attribute("customer", hasProperty("firstname", is("Bob"))));

        //verify properties of bound object
        ArgumentCaptor<Customer> bound = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(bound.capture());

        assertEquals(new Integer(1), bound.getValue().getId());
        assertEquals("123456789", bound.getValue().getPhone());
        assertEquals("11111", bound.getValue().getZipcode());
        assertEquals("Bob", bound.getValue().getFirstname());
    }

    @Test
    public void updateCustomer() throws Exception {
        Integer id = 1;
        Customer c = new Customer();
        c.setId(1);
        when(customerService.getById(id)).thenReturn(c);
        mockMvc.perform(post("/customer/edit/" + id.toString())
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(1))));
    }

    @Test
    public void deleteCustomer() throws Exception {
        Integer id = 1;
        verifyZeroInteractions(customerService);
        mockMvc.perform(get("/customer/delete/" + id.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers"));
    }

}