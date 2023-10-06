package com.upoint.categoryatomic.util;

import org.springframework.stereotype.Component;


	@Component
	public class Message {
		
		public static final String DELETED = "Succesfull deletion";
		
		private static final String ENTITY_NOT_FOUND = "No content found for provided id: %1s";
		
		private static final String CATEGORY_UPDATED = "Category with id: %1s successfully updated ";
		
		public String getCategoryUpdated(Integer id) {
			return String.format(CATEGORY_UPDATED, id);
		}
		
		public String getEntityNotFound(Integer id) {
			return String.format(ENTITY_NOT_FOUND, id);
		}
	}

