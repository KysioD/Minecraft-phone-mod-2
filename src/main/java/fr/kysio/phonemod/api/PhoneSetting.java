package fr.kysio.phonemod.api;

public class PhoneSetting {

    private String name;
    private String description;
    private Object value;

    public PhoneSetting(String name, String description, Object value){
        this.name = name;
        this.description = description;
        this.value = value;
    }

    /**
     * Get the setting name
     * @return the setting name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the setting description
     * @return the setting description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the setting value
     * @return the setting value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Set the setting value
     * @param value an object corresponding to the setting value
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
