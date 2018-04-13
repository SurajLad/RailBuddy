package com.sl.railbuddy;

public class Users
{
    private String name,email,contact,password,id;

    public Users()
    {

    }
    public Users(String id,String name, String email, String contact, String password)
    {
        this.id=id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
    }

    public String getID() { return id; }

    public void setID(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
