// Version 1.0

package odbg.org.libs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JFlex {
	
	public Object useMethod(String className, String methodName, String arguments)  {
		
		String[] variables = arguments.trim().split(",");
		
		String argsType[] = new String[variables.length];
		
		for (int i = 0; i < variables.length; i++) {
			String data[] = variables[i].trim().split(" ", 2);
			argsType[i] = data[0];
			variables[i] = data[1];
		}
		
		Class<?>[] types = this.stringToClass(argsType);
		Object[] values = this.stringToValues(argsType, variables);
		
		Class<?> clase;
		try {
			clase = Class.forName("odbg.org.modules." + className);
			
			Object obj = clase.newInstance();
			
	    	Method metodo = clase.getMethod(methodName, types);
	   
	    	metodo.invoke(obj, values);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    	
	}
		
	public Object useMethod(String className, String methodName) {
		
		Class<?> clase;
		Object res = null;
		try {
			clase = Class.forName("odbg.org.modules." + className);
			
			Object obj = clase.newInstance();
			
	    	Method metodo = clase.getMethod(methodName);
	    	
	    	res = metodo.invoke(obj);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
    	
	}
	
	// Hace un cast de los valores de Objects a su respectivo tipo
	private Object[] stringToValues(String types[], String texts[]) {
		Object[] values = new Object[texts.length];
		
		for(int i = 0; i < texts.length; i++) {
			switch(types[i].toLowerCase()){
				case "string":
					values[i] = texts[i].toString();
					break;
					
				case "int":
				case "integer":
					values[i] = Integer.parseInt(texts[i]);
					break;
					
				case "double":
				case "number":
					values[i] = Double.parseDouble(texts[i]);
					break;
					
				case "char":
				case "character":
					values[i] = texts[i].toString().charAt(0);
					break;
					
				case "float":
					values[i] = Float.parseFloat(texts[0]);
					break;
					
				case "boolean":
					values[i] = Boolean.parseBoolean(texts[0]);
					break;
					
				default:
					System.out.println("You specify a type that does not exist");
					break;
			}
			
		}
		
		return values;
	}
	
	// Extrae las clases de cada una de las variables
	private Class<?>[] stringToClass(String texts[]) {
		Class<?>[] types = new Class[texts.length];
		
		for(int i = 0; i < texts.length; i++) {
			switch(texts[i].toLowerCase()){
				case "string":
					types[i] = String.class;
					break;
					
				case "int":
				case "integer":
					types[i] = Integer.TYPE;
					break;
					
				case "double":
				case "number":
					types[i] = Double.TYPE;
					break;
					
				case "char":
				case "character":
					types[i] = Character.TYPE;
					break;
					
				case "float":
					types[i] = Float.TYPE;
					break;
					
				case "boolean":
					types[i] = Boolean.TYPE;
					break;
					
				default:
					System.out.println("You specify a type that does not exist");
					break;
			}
			
		}
		
		return types;
	}
	
}
