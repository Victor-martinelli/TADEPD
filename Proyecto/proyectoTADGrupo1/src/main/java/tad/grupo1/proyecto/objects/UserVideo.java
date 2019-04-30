package tad.grupo1.proyecto.objects;

import java.util.List;

/**
 *
 * @author Portatil
 */
public class UserVideo {
    
    private String title;
    private String videoPath;
    private int views;
    private int likes;
    private int dislikes;
    private List<UserComment> comments;

    public UserVideo(String video, String videoPath, int views, int likes, int dislikes, List<UserComment> comments) {
        this.title = video;
        this.videoPath = videoPath;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public List<UserComment> getComments() {
        return comments;
    }

    public void setComments(List<UserComment> comments) {
        this.comments = comments;
    }
    
    
    
}
