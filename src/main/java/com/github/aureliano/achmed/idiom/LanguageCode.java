package com.github.aureliano.achmed.idiom;

public enum LanguageCode {

	EN_US(Idiom.ENGLISH),
	PT_BR(Idiom.PORTUGUESE);
	
	private Idiom idiom;
	
	private LanguageCode(Idiom idiom) {
		this.idiom = idiom;
	}
	
	public Idiom getIdiom() {
		return idiom;
	}
}