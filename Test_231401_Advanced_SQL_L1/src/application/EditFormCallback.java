package application;

@FunctionalInterface
public interface EditFormCallback {
    void onFormClosed(Product updatedProduct); 
}

