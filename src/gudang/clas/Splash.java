/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gudang.clas;

import gudang.view.LoginView;

/**
 *
 * @author Fauzan13
 */
public class Splash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        sleepThread();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
     
            @Override
            public void run() {
                new LoginView().setVisible(true);
            }
    });
}
    private static void sleepThread(){
        try {
            Thread.sleep(5000);
            }
        catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }
}
}
