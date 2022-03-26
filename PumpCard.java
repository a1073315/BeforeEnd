
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


public class PumpCard extends JPanel {

    public Label piclabel;
    public Image img[] = new Image[17];
    public ImageIcon[] imgs = new ImageIcon[17];
    public int pumbnum;
    Cardlabel cardlabel;
    Timer timer;
    int index;
    int count = 0;

    public PumpCard() {
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);

        this.piclabel = new Label();
        piclabel.getAllcard(Label.allcard);

        for (int h = 0; h < 17; h++) {
            img[h] = Label.allcard.get(h + 1);
            imgs[h] = new ImageIcon(img[h].getScaledInstance(275, 380, java.awt.Image.SCALE_SMOOTH));
        }

        cardlabel = new Cardlabel();
        cardlabel.setBounds(0, 0, 275, 380);
        cardlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent m) {
                super.mouseClicked(m);
                if (count < 1) {
                    timer.stop();
                    pumbnum = index % imgs.length;
                    Label b = new Label();
                    try {
                        b.addCard(pumbnum);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        count++;

               }
            }

        });
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlabel.repaint();
            }
        });
        timer.start();
        this.add(cardlabel);

    }
    class Cardlabel extends JLabel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(imgs[index % imgs.length].getImage(),0,0,275,380, this);
            index++;
        }
    }
}

