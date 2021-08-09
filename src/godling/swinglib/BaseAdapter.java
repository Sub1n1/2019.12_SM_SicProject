package godling.swinglib;

import java.awt.Component;
import java.util.EventListener;
import java.util.List;

public abstract class BaseAdapter<T> {
	
	public interface DataChangedEventListener extends EventListener {
		void dataChangedEvent();
	}
	
	
	private DataChangedEventListener _dataChangedEvent = null;
	
	public void addDataChangedEventListener(DataChangedEventListener event) {
		_dataChangedEvent = event;
	}

	public void notifyDataSetChange() {
		if (_dataChangedEvent != null) _dataChangedEvent.dataChangedEvent();
	}

	
	List<T> _items;
	
	public BaseAdapter(List<T> items) {
		_items = items;
	}
	
//	//////////
//	public void setItems(List<T> items) {
//		this._items = items;
//	}
//	//////////
	
	public int getCount() {
		return _items.size();
	}
	
	public T getItem(int position) {
		return _items.get(position);
	}

	protected abstract Component getView(int position);
}
