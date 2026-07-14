package net.yxiao233.ifeu.common.compact.jade;

import com.buuz135.industrial.block.IndustrialBlock;
import net.yxiao233.ifeu.common.compact.jade.provider.MachineTileProvider;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class IFEUJadePlugin implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(new MachineTileProvider(), IndustrialBlock.class);
    }
}
