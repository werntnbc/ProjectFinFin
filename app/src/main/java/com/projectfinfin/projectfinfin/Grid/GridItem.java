package com.projectfinfin.projectfinfin.Grid;

/**
 * Created by haball on 18/9/2558.
 */
public class GridItem {
    private String image;
    private String title;
    private String store_id;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    private String member_id;


    public GridItem() {
        super();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) { this.store_id = store_id; }
    public void setTitle(String title) {
        this.title = title;
    }
}
