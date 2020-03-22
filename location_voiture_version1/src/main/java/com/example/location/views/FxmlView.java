package com.example.location.views;

import java.util.ResourceBundle;

public enum FxmlView {

    LOCATION {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("location.title");
        }
        @Override
		public String getFxmlFile() {
            return "/templates/loc.fxml";
        }
    }, 
    ADDRESS {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("address.title");
        }

        @Override
		public String getFxmlFile() {
            return "/templates/addressView.fxml";
        }
    },
	 ADMIN{
	        @Override
			public String getTitle() {
	            return getStringFromResourceBundle("admin.title");
	        }

	        @Override
			public String getFxmlFile() {
	            return "/templates/admin.fxml";
	        }
	    },
	 LOGIN{
	        @Override
			public String getTitle() {
	            return getStringFromResourceBundle("login.title");
	        }

	        @Override
			public String getFxmlFile() {
	            return "/templates/login.fxml";
	        }
	    },
	WELCOME{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("welcome.title");
        }

        @Override
		public String getFxmlFile() {
            return "/templates/welcome.fxml";
        }
    },
	MORE{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("more.title");
        }

        @Override
		public String getFxmlFile() {
            return "/templates/more.fxml";
        }
    },
	REGISTER{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("register.title");
        }

        @Override
		public String getFxmlFile() {
            return "/templates/register.fxml";
        }
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
    

}

