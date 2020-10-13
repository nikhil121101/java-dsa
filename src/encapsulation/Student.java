package encapsulation;

 
public class Student{
	
		private String name;
	    private int age;
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			if(age<12)
			this.age = age;
			else 
				return;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	
}
