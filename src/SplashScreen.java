/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShippingHub;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author WWei-Yu Liao
 */
public class SplashScreen extends JWindow  {
    static JProgressBar progressBar = new JProgressBar();
  static int count = 1, TIMER_PAUSE = 25, PROGBAR_MAX = 100;
  static Timer progressBarTimer;
  ActionListener al = new ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
      progressBar.setValue(count);
      if (PROGBAR_MAX == count) {
        progressBarTimer.stop();
        SplashScreen.this.setVisible(false);
        createAndShowFrame();
      }
      count++;
    }
  };

  public SplashScreen() {
    Container container = getContentPane();
    JPanel panel = new JPanel();
    panel.setBorder(new EtchedBorder());
    container.add(panel, BorderLayout.CENTER);
    
    JLabel label = new JLabel("ShippingHub Operational!");
    label.setFont(new Font("Verdana", Font.BOLD, 14));
    JLabel label2 = new JLabel();
    label2.setIcon(new ImageIcon("src/ShippingHub/wow-mailboxes.jpg"));
    panel.add(label);
    panel.add(label2);

    progressBar.setMaximum(PROGBAR_MAX);
    container.add(progressBar, BorderLayout.SOUTH);
    pack();
    setVisible(true);
    startProgressBar();
    setLocationRelativeTo(null);
  }
  private void startProgressBar() {
    progressBarTimer = new Timer(TIMER_PAUSE, al);
    progressBarTimer.start();
  }
  private void createAndShowFrame() {
    ShippingHubGUI shippingHubGUI = new ShippingHubGUI();
    shippingHubGUI.setVisible(true);
  }
}
