package app.domain.dto;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class PersonJsonDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Set<String> phoneNumbers;

    @Expose
    private AddressJsonDto address;

    public PersonJsonDto(){
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public AddressJsonDto getAddress() {
        return address;
    }

    public void setAddress(AddressJsonDto address) {
        this.address = address;
    }
}
