package com.test.simplerest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public interface EmpTo {

     Integer getId();
    String getFirstName();
    String getLastName();
    Double getSalary();
    AddressTo getAddress();
}
