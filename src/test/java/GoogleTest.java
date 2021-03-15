import com.fourcreate.base.TestBase;
import com.fourcreate.constants.Constants;
import com.fourcreate.pages.GmailPage;
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
        googlePage = new GooglePage(driver, "en");
    }

    @Test
    public void testGoogleSearch() {
        final String searchString = "4create";

        googleResultsPage = googlePage.searchFor(searchString);
        Assertions.assertTrue(googleResultsPage.isCorrectResultFirst(searchString));

        googlePage = new GooglePage(driver, "sr");
        googleResultsPage = googlePage.searchFor(searchString);
        Assertions.assertTrue(googleResultsPage.isCorrectResultFirst(searchString));
    }

    @Test
    public void testUnreadEmails() {
        googlePage.signInToGmail(Constants.EMAIL, Constants.PASSWORD);
        GmailPage gmailPage = googlePage.openGmail();
        Assertions.assertTrue(gmailPage.isThereUnreadEmails());

        googlePage = gmailPage.signOut();
    }
}
