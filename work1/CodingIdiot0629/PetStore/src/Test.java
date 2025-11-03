import com.github.lpx.model.Supplier;
import com.github.lpx.service.MyPetStore;
import com.github.lpx.service.OperationPage;

public class Test {
    public static void main(String[] args) {
        Supplier supplier = new Supplier(100, 200, 50, 1000, 300);
        MyPetStore a = new MyPetStore(10000.0, supplier);
        OperationPage operationPage = new OperationPage(a);
        operationPage.showPage();
    }
}
