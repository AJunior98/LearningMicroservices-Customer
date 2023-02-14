package org.acme.service;

import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public CustomerDTO findCustomerById(Long id){
        CustomerEntity customerEntity = customerRepository.findById(id);
        return mapCustomerEntityToDTO(customerEntity);
    }

    public List<CustomerDTO> findAllCustomers() {
        List<CustomerDTO> customers = new ArrayList<>();

        customerRepository.findAll().stream().forEach(item -> {
            customers.add(mapCustomerEntityToDTO(item));
        });

        return customers;
    }

    public void createNewCustomer(CustomerDTO customerDTO) {
        customerRepository.persist(mapCustomerDTOToEntity(customerDTO));
    }

    public void changeCustomer(Long id, CustomerDTO customerDTO){
        CustomerEntity customer = customerRepository.findById(id);

        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customer.getPhone());
        customer.setEmail(customer.getEmail());
        customer.setAge(customer.getAge());

        customerRepository.persist(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO mapCustomerEntityToDTO(CustomerEntity customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setAge(customer.getAge());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());

        return customerDTO;
    }

    private CustomerEntity mapCustomerDTOToEntity(CustomerDTO customer) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customer.getId());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setAge(customer.getAge());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setName(customer.getName());
        customerEntity.setPhone(customer.getPhone());

        return customerEntity;
    }

}
