package com.example.ticketstracker.ui;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class GlassUtils {

    // 🎨 Draw frosted glass-style background
    public static void drawGlassPanel(Graphics g, JComponent c, int radius) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Base glass color (light mode)
        Color glass = new Color(255, 255, 255, 120);

        // Rounded rectangle
        Shape shape = new RoundRectangle2D.Float(0, 0, c.getWidth(), c.getHeight(), radius, radius);
        g2.setClip(shape);

        // Gradient overlay (slight white gloss)
        GradientPaint gp = new GradientPaint(0, 0, new Color(255, 255, 255, 180),
                0, c.getHeight(), new Color(255, 255, 255, 80));
        g2.setPaint(gp);
        g2.fillRect(0, 0, c.getWidth(), c.getHeight());

        // Border outline
        g2.setColor(new Color(255, 255, 255, 200));
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(shape);

        g2.dispose();
    }

    // 🌈 Gradient background for frames
    public static void paintGradientBackground(Graphics g, JComponent c, Color top, Color bottom) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, top, 0, c.getHeight(), bottom);
        g2.setPaint(gp);
        g2.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
}
