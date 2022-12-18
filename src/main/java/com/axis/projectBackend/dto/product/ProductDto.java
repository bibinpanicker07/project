package com.axis.projectBackend.dto.product;


public class ProductDto {
	   private String name;
	    private String imageURL;
	    private  double price;
	    private  String description;
	    private String categoryName;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getImageURL() {
			return imageURL;
		}
		public void setImageURL(String imageURL) {
			this.imageURL = imageURL;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public ProductDto(String name, String imageURL, double price, String description,
				String categoryName) {
			super();
			this.name = name;
			this.imageURL = imageURL;
			this.price = price;
			this.description = description;
			this.categoryName = categoryName;
		}
	    
	    
}
