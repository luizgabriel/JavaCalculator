package presenters;

public class BasePresenter<T> {
    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }
}
