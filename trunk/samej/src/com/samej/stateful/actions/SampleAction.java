package com.samej.stateful.actions;

import java.util.Set;

import org.apache.struts2.config_browser.ConfigurationHelper;

public class SampleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String sampleAction() {
		result = SUCCESS;
		ConfigurationHelper cHelper = new ConfigurationHelper();
		Set<String> nsList = cHelper.getNamespaces();
		System.out.println(nsList);
		return result;
	}

	public String sessionAction() {
		result = SUCCESS;
		return result;
	}

}
