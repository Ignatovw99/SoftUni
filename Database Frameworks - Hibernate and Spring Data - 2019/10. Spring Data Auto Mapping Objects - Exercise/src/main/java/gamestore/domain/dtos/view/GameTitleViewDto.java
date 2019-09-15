package gamestore.domain.dtos.view;

import gamestore.domain.validators.gametitle.GameTitle;

public class GameTitleViewDto {

    private String title;

    public GameTitleViewDto(){
    }

    public GameTitleViewDto(String title) {
        this.title = title;
    }

    @GameTitle
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
