package net.yxiao233.ifeu.api.item;

public enum IFEUAugmentTypes {
    THREAD("thread"),
    APPLE("apple");
    private final String id;
    IFEUAugmentTypes(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
