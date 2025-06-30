package View.component;

import java.awt.Color;

public class Model_Card {

    private String title;
    private String values;
    private String description;
    private Color colorGradient;

    public Model_Card(String title, String values, String description, Color color) {
        this.title = title;
        this.values = values;
        this.description = description;
        this.colorGradient = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
    }
} 