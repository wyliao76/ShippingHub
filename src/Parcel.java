package ShippingHub;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wei-Yu Liao
 */
public class Parcel {
    private String date;
    private int parcelID;
    Person person = new Person();

    public Parcel(){
        date = "";
        parcelID = 0;
    }
 public Parcel(String d, String f,String l,String a,String c,String s,String z,int id){
  date =d;
  person.setFirstName(f);
  person.setLastName(l);
  person.setAddress(a);
  person.setCity(c);
  person.setState(s);
  person.setZip(z);
  parcelID = id;
 }   
 public Parcel(Parcel anotherParcel){
     date = anotherParcel.date;
     parcelID = anotherParcel.parcelID;
}
    public String getDate() {
        return date;
    }
    public int getParcelID() {
        return parcelID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setParcelID(int parcelID) {
        this.parcelID = parcelID;
    }
 @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public boolean equals(Parcel parcel) {
        if (this.getParcelID() == parcel.getParcelID())
            return true;
        else 
             return false;
    }

}
