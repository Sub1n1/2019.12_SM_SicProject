package godling.swinglib;

import java.awt.Component;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.ImageIcon;

import godling.swinglib.BaseAdapter;
import model.MenuVO;
import view.MenuItemPanel;
import view.MenuItemPanel.DataChangedEventListener;

public class MenuListAdapter extends BaseAdapter<MenuVO> {
   
   public interface ChangedEventListener extends EventListener {
      void dataChangedEvent();
   }
   
   
   private ChangedEventListener _dataChangedEvent = null;
   
   public void addDataChangedEventListener(ChangedEventListener event) {
      _dataChangedEvent = event;
   }

   public void sumDataSetChange() {
      if (_dataChangedEvent != null) _dataChangedEvent.dataChangedEvent();
   }
   

   public MenuListAdapter(ArrayList<MenuVO> items) {
      super(items);
      
   }
   
   public static ArrayList<MenuVO> menuList = null;

   @Override
   protected Component getView(int position) {
      
      MenuItemPanel panel = new MenuItemPanel();
      MenuVO item = getItem(position);
      

      panel.lbl_image.setIcon(new ImageIcon(MenuItemPanel.class.getResource( item.getImage() )));

      panel.lbl_menuName.setText(item.getMenu_name());
      panel.lbl_price.setText(item.getPrice()+"¿ø");
      
      panel.itemPosition = position;
      panel.addDataChangedEventListener(new MenuItemPanel.DataChangedEventListener() {
         
         @Override
         public void dataChangedEvent(int quan, int itemPosition) {
            MenuVO i = getItem(itemPosition);
            i.setSum( i.getPrice() * quan );
            sumDataSetChange();
         }
      });

      return panel;
   
   }

}