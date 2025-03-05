package net.yxiao233.ifeu.common.utils;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyDownUtil {
    private static final long window = Minecraft.getInstance().getWindow().getWindow();
    public static boolean isShiftKeyDown(){
        boolean isLeftShiftDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS;
        boolean isRightShiftDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
        return isLeftShiftDown || isRightShiftDown;
    }

    public static boolean isCtrlKeyDown(){
        boolean isLeftCtrlDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS;
        boolean isRightCtrlDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;
        return isLeftCtrlDown || isRightCtrlDown;
    }

    public static boolean isAltKeyDown(){
        boolean isLeftAltDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_ALT) == GLFW.GLFW_PRESS;
        boolean isRightAltDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_ALT) == GLFW.GLFW_PRESS;
        return isLeftAltDown || isRightAltDown;
    }
}
