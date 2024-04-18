import java.io.Serializable;

public class Exercise implements Serializable {

	private String name;
	private String muscleGroup;
	private String description;

	public Exercise(String name, String muscleGroup, String description) {
		this.name = name;
		this.muscleGroup = muscleGroup;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getMuscleGroup() {
		return muscleGroup;
	}

	public String getDescription() {
		return description;
	}
}
