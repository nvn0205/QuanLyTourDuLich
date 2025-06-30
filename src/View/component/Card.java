package View.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Card extends JPanel {

    private Color colorGradient;
    private JLabel title;
    private JLabel values;
    private JLabel description;

    public Card() {
        setOpaque(false);
        setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]push"));
        init();
    }

    private void init() {
        title = new JLabel();
        title.setFont(new Font("sansserif", 1, 14));
        title.setForeground(new Color(255, 255, 255));
        add(title);
        
        values = new JLabel();
        values.setFont(new Font("sansserif", 1, 20));
        values.setForeground(new Color(255, 255, 255));
        add(values);
        
        description = new JLabel();
        description.setFont(new Font("sansserif", 0, 12));
        description.setForeground(new Color(255, 255, 255));
        add(description);
    }

    public void setData(Model_Card data) {
        title.setText(data.getTitle());
        values.setText(data.getValues());
        description.setText(data.getDescription());
        setColorGradient(data.getColorGradient());
    }

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, getHeight(), getBackground(), getWidth(), 0, colorGradient);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }
} 