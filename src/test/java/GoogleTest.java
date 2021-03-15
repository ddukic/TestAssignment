import com.fourcreate.base.TestBase;
import com.fourcreate.pages.GooglePage;
import com.fourcreate.pages.GoogleResultsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoogleTest extends TestBase {

    private GooglePage googlePage;
    private GoogleResultsPage googleResultsPage;

    @BeforeEach
    public void before() {
        googlePage = new GooglePage(driver);
    }

    @Test
    public void testGoogleSearch() {
        final String searchString = "4create";

        googleResultsPage = googlePage.searchFor(searchString);

        Assertions.assertTrue(googleResultsPage.isCorrectResultFirst(searchString));
    }
}
