package com.hhhy.db.beans.item;

import java.util.Set;

public class Condition{
	private long start;
	private long end;
	private boolean merge;
	private Set<String> sources;
	private int[] sentiments;
	private Set<String> keywords;
	private Set<String> fields;
	private int size;
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public boolean isMerge() {
		return merge;
	}
	public void setMerge(boolean merge) {
		this.merge = merge;
	}
	public Set<String> getSources() {
		return sources;
	}
	public void setSources(Set<String> sources) {
		this.sources = sources;
	}
	public int[] getSentiments() {
		return sentiments;
	}
	public void setSentiments(int[] sentiments) {
		this.sentiments = sentiments;
	}
	public Set<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}
	public Set<String> getFields() {
		return fields;
	}
	public void setFields(Set<String> fields) {
		this.fields = fields;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}