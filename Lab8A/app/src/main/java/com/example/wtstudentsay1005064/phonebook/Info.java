package com.example.wtstudentsay1005064.phonebook;

public class Info {
    private int id;
    private String FirstName;
    private String LastName;
    private String PhoneNum;
    public Info(){

    }
    public Info(String fname,String lname, String num){
        FirstName = fname;
        LastName = lname;
        PhoneNum = num;
    }
    public Info(int i, String fname, String lname, String num){
        id = i;
        FirstName = fname;
        LastName = lname;
        PhoneNum = num;
    }
    public void setID(int i){

        id = i;
    }
    public void setFirstName(String fname){

        FirstName = fname;
    }
    public void setLastName(String lname){

        LastName = lname;
    }
    public void setPhoneNum(String num)
    {
        PhoneNum = num;
    }
    public int getId(){
        return  id;
    }
    public String getFirstName()
    {
        return FirstName;
    }
    public String getLastName()
    {
        return LastName;
    }
    public String getPhoneNum(){ return PhoneNum; }

}
