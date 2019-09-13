package usersystem.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {

    private Long id;

    private byte[] image;

    private String caption;

    private String pathOnFileSystem;

    private Set<Album> albums;

    public Picture() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "image")
    @Lob
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "caption")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Column(name = "path")
    public String getPathOnFileSystem() {
        return pathOnFileSystem;
    }

    public void setPathOnFileSystem(String pathOnFileSystem) {
        this.pathOnFileSystem = pathOnFileSystem;
    }

    @ManyToMany(targetEntity = Album.class, mappedBy = "pictures", cascade = CascadeType.ALL)
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
