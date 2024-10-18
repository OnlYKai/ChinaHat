package com.kai.chinahat.utils;

import java.awt.*;

import static com.kai.chinahat.Settings.saturation;
import static com.kai.chinahat.Settings.speed;

public class ColorGetter {

    public Color getRainbowColor(int index) {
        return ColorUtil.rainbow(speed, index, saturation, 1, 1); // Rainbow effect
    }

    public Color getGradientColor(int index, Color color1, Color color2) {
        return ColorUtil.interpolateColorsBackAndForth(speed, index, color1, color2, false);
    }

}