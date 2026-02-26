package klu.SpringBoot_Demo;
import org.springframework.stereotype.Component;

@Component
public class Certification {


	
	

	    private int id;
	    private String name;
	    private String dateOfCompletion;

	    public Certification() {
	        this.id = 1;
	        this.name = "Java Developer";
	        this.dateOfCompletion = "2025-03-10";
	    }

	    @Override
	    public String toString() {
	        return "Certification{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", dateOfCompletion='" + dateOfCompletion + '\'' +
	                '}';
	    
	}
}
