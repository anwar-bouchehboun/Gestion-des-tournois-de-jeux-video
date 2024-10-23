package spring.interfaces;

public interface OperationInteraface<T> {
	   void ajouterJoueur(Long Id, T entity);

	    void retirerJoueur(Long Id, T entity);

}
