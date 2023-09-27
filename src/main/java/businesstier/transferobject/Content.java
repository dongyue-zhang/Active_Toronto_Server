package businesstier.transferobject;

import java.io.Serializable;

public abstract class Content<T> implements Serializable, Comparable<T>{

	protected static final long serialVersionUID = 1L;

	public abstract int compareTo(T o);
}
