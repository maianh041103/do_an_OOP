
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder{
    private final int radius;
    private final Color color;

    public RoundedBorder(int radius) {
        this(radius, null);
    }

    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color != null ? color : c.getBackground());
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int value = (int) Math.ceil(radius / 2.0);
        return new Insets(value, value, value, value);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom =
                (int) Math.ceil(radius / 2.0);
        return insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return color != null;
    }
}
