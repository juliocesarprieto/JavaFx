package models;

public class Person {
	
	private Boolean check;
	private String name;
	private String lastName;
	private Integer age;	
	
	public Person(String name, String lastName, Integer age) {
		super();
		this.check = false;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}
	
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
