package abap.codemining.adt;

public class AbapCodeElementInformation {
	private String visibility; 
	private String level; 
	private String name; 

	public AbapCodeElementInformation(String visibility, String level, String name) 
	{
		this.visibility = visibility;
		this.level = level; 
		this.name = name; 
	}

	public String getVisibility() {
		return visibility; 
	}

	public String getLevel() {
		return level; 
	}
	
	public String getName() 
	{
		return name; 
	}

}
