
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.monView;
import view.parentView;


public class parentController implements ActionListener{
    
    public parentView ParentView;

    public parentController(parentView ParentView) {
        this.ParentView = ParentView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("Insert")){
            //ParentView.setVisible(false);
        }
        
    }
    
}
