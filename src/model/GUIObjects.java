package model;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import view.DesignGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class GUIObjects extends JComponent {
    String type;
    int self;
    Component comp;
    DesignGUI dGui;

    public GUIObjects(Component comp, String type, int self, DesignGUI dGui) {
        this(comp, new ResizableBorder(8), type, self, dGui);

    }

    public GUIObjects(Component comp, ResizableBorder border, String type, int self, DesignGUI dGui) {
        this.comp = comp;
        this.self = self;
        this.type = type;
        this.dGui = dGui;
        setLayout(new BorderLayout());
        add(comp);
        addMouseListener(resizeListener);
        addMouseMotionListener(resizeListener);
        setBorder(border);
    }

    private void resize() {

        if (getParent() != null) {
            getParent().revalidate();
        }
    }

    MouseInputListener resizeListener = new MouseInputAdapter() {

        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2 && me.getButton() == MouseEvent.BUTTON1) {
                if (comp instanceof JLabel) {
                    JLabel textBox = (JLabel) comp;

                    LabelEditor options = new LabelEditor(textBox.getText(), self, type, dGui);
                    int test = JOptionPane.showConfirmDialog(null, options, "Edit text",
                            JOptionPane.OK_OPTION);
                    if (test == JOptionPane.OK_OPTION) {
                        textBox.setText(options.getEditText().getText());

                        int bold = options.getBoldText().isSelected() ? Font.BOLD : Font.PLAIN;
                        int italic = options.getItalicText().isSelected() ? Font.ITALIC : Font.PLAIN;
                        int textSize = options.getTextSize().getSelectedIndex() + 1;

                        textBox.setFont(new Font(textBox.getFont().getName(), italic + bold, textSize));
                    }

                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent me) {

            if (hasFocus()) {

                var resizableBorder = (ResizableBorder) getBorder();
                setCursor(Cursor.getPredefinedCursor(resizableBorder.getCursor(me)));
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            setCursor(Cursor.getDefaultCursor());

        }

        private int cursor;
        private Point startPos = null;

        @Override
        public void mousePressed(MouseEvent me) {

            var resizableBorder = (ResizableBorder) getBorder();
            cursor = resizableBorder.getCursor(me);
            startPos = me.getPoint();

            requestFocus();
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent me) {

            if (startPos != null) {

                int x = getX();
                int y = getY();
                int w = getWidth();
                int h = getHeight();

                int dx = me.getX() - startPos.x;
                int dy = me.getY() - startPos.y;

                switch (cursor) {

                    case Cursor.N_RESIZE_CURSOR -> {

                        if (!(h - dy < 50)) {
                            setBounds(x, y + dy, w, h - dy);
                            resize();
                        }
                    }

                    case Cursor.S_RESIZE_CURSOR -> {

                        if (!(h + dy < 50)) {
                            setBounds(x, y, w, h + dy);
                            startPos = me.getPoint();
                            resize();
                        }
                    }

                    case Cursor.W_RESIZE_CURSOR -> {

                        if (!(w - dx < 50)) {
                            setBounds(x + dx, y, w - dx, h);
                            resize();
                        }
                    }

                    case Cursor.E_RESIZE_CURSOR -> {

                        if (!(w + dx < 50)) {
                            setBounds(x, y, w + dx, h);
                            startPos = me.getPoint();
                            resize();
                        }
                    }

                    case Cursor.NW_RESIZE_CURSOR -> {

                        if (!(w - dx < 50) && !(h - dy < 50)) {
                            setBounds(x + dx, y + dy, w - dx, h - dy);
                            resize();
                        }
                    }

                    case Cursor.NE_RESIZE_CURSOR -> {

                        if (!(w + dx < 50) && !(h - dy < 50)) {
                            setBounds(x, y + dy, w + dx, h - dy);
                            startPos = new Point(me.getX(), startPos.y);
                            resize();
                        }
                    }

                    case Cursor.SW_RESIZE_CURSOR -> {

                        if (!(w - dx < 50) && !(h + dy < 50)) {
                            setBounds(x + dx, y, w - dx, h + dy);
                            startPos = new Point(startPos.x, me.getY());
                            resize();
                        }
                    }

                    case Cursor.SE_RESIZE_CURSOR -> {

                        if (!(w + dx < 50) && !(h + dy < 50)) {
                            setBounds(x, y, w + dx, h + dy);
                            startPos = me.getPoint();
                            resize();
                        }
                    }

                    case Cursor.MOVE_CURSOR -> {

                        var bounds = getBounds();
                        bounds.translate(dx, dy);
                        setBounds(bounds);
                        resize();
                    }
                }

                setCursor(Cursor.getPredefinedCursor(cursor));
            }
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

            startPos = null;
        }
    };

    public String getType() {
        return type;
    }

    public Component getComp() {
        return comp;
    }

}