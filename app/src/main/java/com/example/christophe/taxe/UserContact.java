package com.example.christophe.taxe;

/**
 * Created by christophe on 10/17/17.
 */

public class UserContact {

    //private variables
    String _password;
    String _name;
    String _phone_number;

    // Empty constructor
    public UserContact(){

    }
    // constructor
    public UserContact(String password, String name, String _phone_number){
        this._password = password;
        this._name = name;
        this._phone_number = _phone_number;
    }

    // constructor
    public UserContact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }
    // getting ID
    public String getID(){
        return this._password;
    }

    // setting id
    public void setID(String password){
        this._password = password;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}
