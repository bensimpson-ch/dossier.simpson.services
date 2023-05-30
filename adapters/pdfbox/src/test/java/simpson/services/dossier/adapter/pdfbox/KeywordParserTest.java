package simpson.services.dossier.adapter.pdfbox;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordParserTest {


    @Test
    void testParserEmpty() {
        var text = "";

        var keywords = new KeywordParser().keywords(text);

        assertThat(keywords).isEmpty();
    }


    @Test
    void testParserPdfScrapedText() {
        var text = "Registration Main member of the\n" +
                "cooperative\n" +
                "Welcome to Mobility, Mr. Simpson\n" +
                "Order\n" +
                "Many thanks for your registration and your interest in our future-oriented transport services.\n" +
                "You are not yet able to log in and make reservations. We first have to verify your details as you have been\n" +
                "registered in the past. We will get back to you by email no later than the next working day (Mon-Fri).\n" +
                "Please note: the password you entered during registration has not been saved. You can set the\n" +
                "password again at a later time.\n" +
                "If you need to, you can reach the 24h Service Center on 0848 824 812.\n" +
                "Order #abc123\n" +
                "Main member of the cooperative CHF 1’250.00\n" +
                "Policy Deductible 300 for 12 months CHF 150.00\n" +
                "Cause We Care (carbon offsetting) 0.03 CHF/km CHF 0.00\n" +
                "Total (incl. VAT) CHF 1’400.00\n" +
                "My Mobility https://my.mobility.ch/register/wizard/confirmation\n" +
                "1 of 1 05.04.22, 07:51\n";

        var keywords = new KeywordParser().keywords(text);

        assertThat(keywords).hasSize(2);
    }
}