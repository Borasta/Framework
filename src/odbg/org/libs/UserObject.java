package odbg.org.libs;

public class UserObject {
	
	private int id;
	private String userapp;
	private String name;
	private String lastname;
	
	public UserObject( int id, String userapp, String name, String lastname ) {
		this.id = id;
		this.userapp = userapp;
		this.name = name;
		this.lastname = lastname;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUserapp() {
		return userapp;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastname() {
		return lastname;
	}
}
