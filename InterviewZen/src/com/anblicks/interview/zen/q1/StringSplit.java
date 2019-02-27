package com.anblicks.interview.zen.q1;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringSplit {

	private String sourceString;
	private int splitParameter;

	public String getSourceString() {
		return sourceString;
	}

	public void setSourceString(String sourceString) {
		this.sourceString = sourceString;
	}

	public int getSplitParameter() {
		return splitParameter;
	}

	public void setSplitParameter(int splitParameter) {
		this.splitParameter = splitParameter;
	}

	public StringSplit(String sourceString, int splitParameter) {
		super();
		this.setSourceString(sourceString);
		this.setSplitParameter(splitParameter);
	}

	public String displayMulti() {

		if (sourceString.length() != 0) {
			if (sourceString.length() >= 3) {

				return IntStream.range(0, splitParameter)
						        .mapToObj(i -> sourceString.substring(0, 3))
						        .collect(Collectors.joining(""));

			} else {

				return IntStream.range(0, splitParameter)
						        .mapToObj(i -> new StringBuilder(sourceString).reverse())
						        .collect(Collectors.joining(""));
			}
		} else {
			return "Empty String !!";
		}

	}
}
