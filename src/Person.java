/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShippingHub;

/**
 *
 * @author Wei-Yu Liao
 */
public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    
    public Person(){ 
     firstName = "";
     lastName = "";
     address ="";
     city = "";
     state ="";
     zip = "";  
}   
 public Person(String f,String l,String a,String c,String s,String z){
    firstName = f;
    lastName = l;
    address =a ;
    city = c;
    state =s;
    zip = z;

 } 
 public Person(Person anotherPerson){  
     firstName = anotherPerson.firstName;
     lastName = anotherPerson.lastName;
     address = anotherPerson.address;
     city = anotherPerson.city;
     state = anotherPerson.state;
     zip = anotherPerson.zip; 
}

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip + '}';
    }
@Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}

