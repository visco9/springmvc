package com.visco9.services;

import com.visco9.domain.Customer;
import com.visco9.domain.DomainObject;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by x on 2/18/2017.
 */
@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }    private Map<Integer,Customer> customers;

    @Override
    protected void loadDomainObjects(){
        domainMap = new HashMap<>();
        Customer c1 = new Customer();
        c1.setId(1);
        c1.setAddress1("666 Butthole Blvd");
        c1.setAddress2("Apt 2F");
        c1.setCity("Tuckahoe");
        c1.setState("NY");
        c1.setZipcode("10707");
        c1.setEmail("creep@pizzagate.org");
        c1.setFirstname("John");
        c1.setLastname("Podesta");
        c1.setPhone("9176660001");
        domainMap.put(1, c1);
        Customer c2 = new Customer();
        c2.setId(2);
        c2.setAddress1("666 Butthole Blvd");
        c2.setAddress2("Apt 2F");
        c2.setCity("Tuckahoe");
        c2.setState("NY");
        c2.setZipcode("10707");
        c2.setEmail("jane@pizzagate.org");
        c2.setFirstname("Jane");
        c2.setLastname("Podesta");
        c2.setPhone("9176660002");
        domainMap.put(2, c2);
    }
}
