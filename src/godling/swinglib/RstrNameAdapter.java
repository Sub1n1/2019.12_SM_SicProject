package godling.swinglib;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.RestaurantVO;
import view.DetailsGUI;
import view.RestItemPanel;
import view.TodayGUI;

public class RstrNameAdapter extends BaseAdapter<RestaurantVO>{

   public RstrNameAdapter(ArrayList<RestaurantVO> items) {
      super(items);
   }
   

   
   protected Component getView(int position) {
      RestItemPanel panel = new RestItemPanel();
      RestaurantVO item =  getItem(position);

      panel.lbImage.setIcon(new ImageIcon(TodayGUI.class.getResource(item.getImage())));
      panel.lbRstrName.setText(item.getName());
      panel.btnMenu.setText("메뉴보기");
      panel.btnMenu.setActionCommand(position + "");

      panel.btnMenu.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            System.out.print( "position : " + btn.getActionCommand() + "  " + "itemsize : " + getCount());
            RestaurantVO item = getItem(Integer.parseInt(btn.getActionCommand()));
            DetailsGUI window = new DetailsGUI(item.getId());
            window.frame.setVisible(true);
         }
      });

      return panel;
   }

}