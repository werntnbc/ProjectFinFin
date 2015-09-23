package com.projectfinfin.projectfinfin.WhereToGo;

import java.util.List;

/**
 * Created by TNBC's on 23/9/2558.
 */
public class Store {
    private int storeId;
    private int branchId;
    private int categoryId;
    private String storeName;
    private String storeDetail;
    private String officialHour;
    private String storeLogo;
    private List<Store> stores;

    public Store(){

    }

    public Store(List<Store> stores) {
        this.stores = stores;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(String storeDetail) {
        this.storeDetail = storeDetail;
    }

    public String getOfficialHour() {
        return officialHour;
    }

    public void setOfficialHour(String officialHour) {
        this.officialHour = officialHour;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

}
