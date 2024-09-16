/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import gui.SplashWindow;
import java.awt.Frame;
import javax.swing.ImageIcon;

/**
 *
 * @author mamet
 */
public class AppIcon {
    
    public static void setAppIcon(Frame window) {
        window.setIconImage(new ImageIcon(AppIcon.class.getResource("/resources/app-icon.png")).getImage());
    }
    
}
