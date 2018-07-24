package application.models;

public class Person {
	
	private String name;
	private String lastName;
	private int age;
	private String description;
	
	
	public Person(String name, String lastName, int age, String description) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.description = description;
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
