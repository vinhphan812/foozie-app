package vn.edu.huflit.foozie_app.Models;

public class intro_item {
    String Title, Description;
    int Poster;

    public intro_item(String title, String description, int poster) {
        Title = title;
        Description = description;
        Poster = poster;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPoster(int poster) {
        Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getPoster() {
        return Poster;
    }
}
