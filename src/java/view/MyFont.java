package view;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MyFont
{
    private Font font;
    public MyFont(float v) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/resources/PressStart2P.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        font = font.deriveFont(v);
    }

    public Font getFont() {
        return font;
    }
}

