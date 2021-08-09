package godling.swinglib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JListView extends JScrollPane {
   
   /**
    * 
    */
   private static final long serialVersionUID = -7669693115331297659L;
   private BaseAdapter<?> _adapter = null;
   private JPanel _container;
   private JPanel _containerBg;
   
   public JListView() {
      _container = new JPanel();
      _container.setLayout(new GridLayout(0,1,0,1));
      _container.setBackground(null);
      
      _containerBg = new JPanel();
      _containerBg.setLayout(new FlowLayout());
      _containerBg.add(_container);
      _containerBg.setBackground(Color.WHITE);
      
      this.setViewportView(_containerBg);
      this.addHierarchyBoundsListener(new HierarchyBoundsListener() {
         
         @Override
         public void ancestorResized(HierarchyEvent e) {
            System.out.print(JListView.this.getSize().width + "     ");
            System.out.print(_containerBg.getSize().width + "     ");
            System.out.println(_container.getSize().width + "");
            
            _containerBg.setPreferredSize(new Dimension(JListView.this.getSize().width - 20, _containerBg.getPreferredSize().height));
            _container.setPreferredSize(new Dimension(JListView.this.getSize().width - 30, _container.getPreferredSize().height));
            _container.invalidate();
         }
         
         @Override
         public void ancestorMoved(HierarchyEvent e) { }
      });
   }
   
   public void setDivid(Color color) {
      _container.setBackground(color);
   }
   
   public void setBound(Color color) {
      _containerBg.setBackground(color);
   }
   
   public void setAdapter(BaseAdapter<?> adapter) {
      adapter.addDataChangedEventListener(new BaseAdapter.DataChangedEventListener() {
         
         @Override
         public void dataChangedEvent() {
            _container.removeAll();
            for (int i = 0; i < _adapter.getCount(); i++)
               _container.add(_adapter.getView(i));
         }
      });
      
      _adapter = adapter;
   }
   
}