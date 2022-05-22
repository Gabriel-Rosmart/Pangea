package com.frontend;

import java.awt.*;
import java.io.*;


public class FontCreator {
    Font heavyData;
    Font caskaydiaCove;
    Font gohuFont;
    Color fontColorNormal;
    Color fontColorSelect;

    public FontCreator() throws IOException, FontFormatException {
        InputStream hd = new FileInputStream("Fonts" + File.separator + "Heavy_Data_Nerd_Font_Complete.ttf");
        this.heavyData = Font.createFont(Font.TRUETYPE_FONT, hd);

        InputStream cc = new FileInputStream("Fonts" + File.separator + "Caskaydia_Cove_Nerd_Font_Complete.ttf");
        this.caskaydiaCove = Font.createFont(Font.TRUETYPE_FONT, cc);

        InputStream ghf = new FileInputStream("Fonts" + File.separator + "GohuFont_Nerd_Font_Complete.ttf");
        this.gohuFont = Font.createFont(Font.TRUETYPE_FONT, ghf);

        this.fontColorNormal = new Color(234, 164, 0);
        this.fontColorSelect = new Color(0, 161, 217);
    }

    public Font getHeavyData(int size){ return this.heavyData.deriveFont(Font.BOLD, size); }

    public Font getCaskaydiaCove(int size) { return caskaydiaCove.deriveFont(Font.BOLD, size); }

    public Font getGohuFont(int size){ return this.gohuFont.deriveFont(Font.BOLD, size); }

    public Color getFontColorNormal(){ return this.fontColorNormal; };

    public Color getFontColorSelect() { return this.fontColorSelect; }
}
