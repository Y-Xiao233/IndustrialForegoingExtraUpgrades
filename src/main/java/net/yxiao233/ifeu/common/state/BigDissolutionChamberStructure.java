package net.yxiao233.ifeu.common.state;

import net.yxiao233.ifeu.api.block.IEnumProperty;
import net.yxiao233.ifeu.api.block.RelativeBlockPos;

import java.util.Arrays;
import java.util.List;

public enum BigDissolutionChamberStructure implements IEnumProperty<BigDissolutionChamberStructure> {
    CORE("core",0,0,0),
    A("a",-1,0,0),
    B("b",-1,0,-1),
    C("c",-1,0,-2),
    D("d",0,0,-1),
    E("e",0,0,-2),
    F("f",1,0,-1),
    G("g",1,0,-2),
    H("h",0,1,0),
    I("i",-1,1,0),
    J("j",-1,1,-1),
    K("k",-1,1,-2),
    M("m",0,1,-1),
    N("n",0,1,-2),
    O("o",1,1,-1),
    P("p",1,1,-2),
    Q("q",0,2,0),
    R("r",-1,2,0),
    S("s",-1,2,-1),
    T("t",-1,2,-2),
    U("u",0,2,-1),
    V("v",0,2,-2),
    W("w",1,2,-1),
    X("x",1,2,-2),
    Y("y",1,0,0),
    Z("z",1,1,0),
    AA("aa",1,2,0);
    public final String name;
    private final RelativeBlockPos relativeBlockPos;
    BigDissolutionChamberStructure(String name, int relativeX, int relativeY, int relativeZ) {
        this.name = name;
        this.relativeBlockPos = new RelativeBlockPos(relativeX,relativeY,relativeZ);
    }
    @Override
    public List<BigDissolutionChamberStructure> getValues() {
        return Arrays.stream(values()).toList();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public RelativeBlockPos getRelativeBlockPos() {
        return relativeBlockPos;
    }

    @Override
    public IEnumProperty<BigDissolutionChamberStructure> getDefault() {
        return CORE;
    }
}
