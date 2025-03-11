package net.yxiao233.ifeu.api.item;

public enum IFEUAugmentTypes {
    THREAD("thread");
    private String id;
    IFEUAugmentTypes(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
