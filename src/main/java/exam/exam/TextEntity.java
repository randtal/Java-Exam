package exam.exam;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

enum Category {
    politics, education, culture, entertainment
}

@Entity
class TextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String author;

    private String title;

    // EnumType.STRING specifies we save the value as a String and not a number
    // while translating it back in Spring.
    // All validation happens on the backend side and non in the database, in the 
    // DB this is just a normal text field and not a One-To-One relationship.
    @Enumerated(EnumType.STRING)
    private Category category;

    // Standard text length inside database would be 255 characters.
    @Column(length = 1200)
    private String text;

    // An empty constructor is an requirement from Spring.
    // It simple is and we shall oblidge.
    public TextEntity() {
    }

    public TextEntity(String author, String title, Category category, String text) {
        this.author = author;
        this.title = title;
        this.category = category;
        this.text = text;
    }

    // Used later to combine the data from the storage to the analytics.
    public HashMap<String, String> toJSON() {
        HashMap<String, String> json = new HashMap<>();
        json.put("author", this.author);
        json.put("title", this.title);
        json.put("category", this.category.toString());
        json.put("text", this.text);
        return json;
    }

    // Just a good thing to have, absolutely not necessary or useful in this case.
    @Override
    public String toString() {
        return "{" + " author='" + getAuthor() + "'" + ", title='" + getTitle() + "'" + ", category='" + getCategory()
                + "'" + ", text='" + getText() + "'" + "}";
    }

    // ######## Getters and Setters for accesability since our variables are private ########
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}