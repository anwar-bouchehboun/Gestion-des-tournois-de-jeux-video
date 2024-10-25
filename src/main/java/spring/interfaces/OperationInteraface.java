package spring.interfaces;

public interface OperationInteraface<T> {
	   void ajouter(Long Id, T entity);

	    void retirer(Long Id, T entity);

}
