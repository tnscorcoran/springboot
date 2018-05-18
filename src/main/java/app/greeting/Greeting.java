package app.greeting;

public class Greeting {

    private final long id;
    private final String content;
    private String description = "Demo: Spring Boot on OCP - 11th Feb 2018";

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
