package net.yxiao233.ifeu.common.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.opengl.GL11;

import java.util.function.Function;

public class RendererHelper {
    public static final Function<ResourceLocation, TextureAtlasSprite> TEXTURE_GETTER =
            location -> Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location);
    public static Triple<Float, Float, Float> intToRGB(int color) {
        float red, green, blue;
        red = (float)(color >> 16 & 255) / 255.0F;
        green = (float)(color >> 8 & 255) / 255.0F;
        blue = (float)(color & 255) / 255.0F;
        return Triple.of(red, green, blue);
    }

    @SuppressWarnings("deprecation")
    public static void renderFluidContext(FluidStack fluid, PoseStack matrixStack, IFluidContextRender render) {
        if(fluid != null && fluid.getAmount() > 0) {
            matrixStack.pushPose();

            RenderSystem.enableBlend();
            RenderSystem.disableCull();

            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            Minecraft.getInstance().getTextureManager().bindForSetup(TextureAtlas.LOCATION_BLOCKS);

            render.render();

            RenderSystem.disableBlend();
            matrixStack.popPose();
        }
    }

    public static TextureAtlasSprite getFluidIcon(Fluid fluid, Direction side) {
        return getFluidIcon(new FluidStack(fluid, 1000), side);
    }

    public static TextureAtlasSprite getFluidIcon(FluidStack fluid, Direction side) {
        if(side == null) side = Direction.UP;

        IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluid.getFluid());
        TextureAtlasSprite icon = TEXTURE_GETTER.apply(renderProperties.getFlowingTexture(fluid));
        if(icon == null || (side == Direction.UP || side == Direction.DOWN)) {
            icon = TEXTURE_GETTER.apply(renderProperties.getStillTexture(fluid));
        }

        return icon;
    }

    public static interface IFluidContextRender {
        public void render();
    }
}
