/**
 * Created by alex2000 on 09.10.16.
 */
public class Contact {
    private String name;
    private String surName;
    private String phone;

    public Contact(String name, String surName, String phone) {
        this.name = name;
        this.surName = surName;
        this.phone = phone;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSurname(String surname) {
        this.surName = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surName;
    }

    public String getPhone() {
        return phone;
    }

    public void print() {
        String contact = "";
        contact = contact.concat(name + " " + surName + " " + phone);
        System.out.println(contact);
    }
}
