package net.yxiao233.ifeu.api.item;

public enum IFEUAugmentTypes {
    THREAD("thread"),
    APPLE("apple"),
    SILK("silk"),
    HEAL("heal");
    private final String id;
    IFEUAugmentTypes(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
