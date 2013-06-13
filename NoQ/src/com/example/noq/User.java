package com.example.noq;
 
public class User {
     
    private String _name;
    private String _NRIC;
    private String _contactNo;
    private String _email;
    private String _vehNo;
    private String _IUNo;
    private String _password;
    
    public User(){}
    
    public User(String name, String NRIC, String contactNo, String email, String vehNo, String IU, String password) {
    	setName(name);
    	setNRIC(NRIC);
    	setContact(contactNo);
    	setEmail(email);
    	setVehNo(vehNo);
    	setIU(IU);
    	setPassword(password);
    }

    public void setName(String name){
        this._name = name;
    }
     
    public String getName(){
        return this._name;
    }
    
    public void setNRIC(String NRIC){
        this._NRIC = NRIC;
    }
     
    public String getNRIC(){
        return this._NRIC;
    }
    
    public void setContact(String contactNo){
        this._contactNo = contactNo;
    }
     
    public String getContact(){
        return this._contactNo;
    }
    
    public void setEmail(String email){
        this._email = email;
    }
     
    public String getEmail(){
        return this._email;
    }

    public void setVehNo(String vehNo){
        this._vehNo = vehNo;
    }
     
    public String getVehNo(){
        return this._vehNo;
    }
    
    public void setIU(String IU){
        this._IUNo = IU;
    }
     
    public String getIU(){
        return this._IUNo;
    }
    public void setPassword(String pw) {
    	this._password = pw;
    }
}
