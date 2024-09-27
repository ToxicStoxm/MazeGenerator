package com.toxicstoxm.Ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {

    public record Component(Shape shape, Color color) {}

    private final List<Component> componentList;

    public Canvas() {
        componentList = new ArrayList<>();
    }

    public void addComponent(Component component) {
        componentList.add(component);
    }

    public void removeComponent(Component component) {
        componentList.remove(component);
    }

    public void addShape(Shape s) {
        componentList.add(new Component(s, Color.WHITE));
    }

    public void addShape(Shape s, Color color) {
        componentList.add(new Component(s, color));
    }

    public void removeShape(Shape s) {
        componentList.remove(new Component(s, Color.WHITE));
    }

    public void removeShape(Shape s, Color color) {
        componentList.remove(new Component(s, color));
    }

    public void clearComponents() {
        componentList.clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, getWidth(), getHeight());

        List<Component> cached_components = new ArrayList<>(componentList);

        for (Component component : cached_components)
        {
            g2d.setColor( component.color );
            g2d.fill( component.shape );
        }

        g2d.dispose();
        super.paintComponent(g2d);
    }

}
