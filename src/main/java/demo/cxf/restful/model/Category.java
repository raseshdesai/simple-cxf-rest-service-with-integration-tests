package demo.cxf.restful.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "Category")
public class Category {

    private String categoryId;

	private String categoryName;

	private Collection<Book> books;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!books.equals(category.books)) return false;
        if (!categoryId.equals(category.categoryId)) return false;
        if (!categoryName.equals(category.categoryName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId.hashCode();
        result = 31 * result + categoryName.hashCode();
        result = 31 * result + books.hashCode();
        return result;
    }
}
