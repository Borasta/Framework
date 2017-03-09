package odbg.org.modules;

public class Mod1 {

	private String name = "Orlando";
	private int age = 20;
	
	public String sayHello() {
		return "Hello " + this.name + " you have " + this.age + " years old";
	}
	
	public void sayHello( String _name, int _age ) {
		System.out.println("Hello " + _name + " you have " + _age + " years old");
	}
	
}
