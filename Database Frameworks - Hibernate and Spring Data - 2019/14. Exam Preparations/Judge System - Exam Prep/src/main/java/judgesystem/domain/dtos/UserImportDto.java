package judgesystem.domain.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserImportDto implements Serializable {

    @Expose
    private Long id;

    @Expose
    private String username;

    public UserImportDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}