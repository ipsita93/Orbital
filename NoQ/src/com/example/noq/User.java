package com.example.noq;
 
public class User {
    
	private int _id;
    private String _name;
    private String _NRIC;
    private String _contactNo;
    private String _email;
    private String _vehNo;
    private String _IUNo;
    private String _password;
    
    public User(){}
    
    public User(int id, String name, String NRIC, String contactNo, String email, String vehNo, String IU, String password) {
    	setId(id);
		setName(name);
    	setNRIC(NRIC);
    	setContact(contactNo);
    	setEmail(email);
    	setVehNo(vehNo);
    	setIU(IU);
    	setPassword(password);
    }

	public void setId(int id){
		_id = id;
	}
	
	public int getId(){
		return _id;
	}
	
    public void setName(String name){
        _name = name;
    }
     
    public String getName(){
        return _name;
    }
    
    public void setNRIC(String NRIC){
        _NRIC = NRIC;
    }
     
    public String getNRIC(){
        return _NRIC;
    }
    
    public void setContact(String contactNo){
        _contactNo = contactNo;
    }
     
    public String getContact(){
        return _contactNo;
    }
    
    public void setEmail(String email){
        _email = email;
    }
     
    public String getEmail(){
        return _email;
    }

    public void setVehNo(String vehNo){
        _vehNo = vehNo;
    }
     
    public String getVehNo(){
        return _vehNo;
    }
    
    public void setIU(String IU){
        _IUNo = IU;
    }
     
    public String getIU(){
        return _IUNo;
    }
    public void setPassword(String pw) {
    	_password = pw;
    }
	
	// here is where crypto comes in
	public String getPassword(){
		return _password;
	}
}
