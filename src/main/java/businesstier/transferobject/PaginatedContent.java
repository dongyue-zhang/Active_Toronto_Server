package businesstier.transferobject;

import java.util.List;

public class PaginatedContent<T> {
	private List<T> content;
	private int page;
	private int size;
	private int offset;
	private boolean last;
	private int totalElements;
	private int totalPages;
	
	public PaginatedContent (List<T> contentsSublist) {
		setContent(contentsSublist);
		if (contentsSublist != null) {
			setTotalElements(contentsSublist.size());
		} else {
			setTotalElements(0);
		}
		
	}
	public PaginatedContent () {}

	public boolean getLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	public int getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElemets) {
		this.totalElements = totalElemets;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> contentsSublist) {
		this.content = contentsSublist;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}

}
