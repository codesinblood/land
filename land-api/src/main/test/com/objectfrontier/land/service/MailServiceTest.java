package com.objectfrontier.land.service;

import com.objectfrontier.land.config.MailConfiguration;
import com.objectfrontier.land.exception.MailErrorCode;
import com.objectfrontier.land.exception.MailException;
import com.objectfrontier.land.model.MailDetails;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class MailServiceTest {

    @Mock
    MailConfiguration mailConfigMock;

    @Mock
    MailService mailServiceMock;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test(dataProvider = "dp_sendMailPositive")
//    public void sendMailPositive() {
//        when(mailConfigMock.javaMailSender().thenReturn());
//    }
//
//    @DataProvider
//    Object[][] dp_sendMailPositive() {
//        MailDetails expectedResult = new MailDetails("asd@gmail.com", "test", "Sample test", "abc@gmail.com");
//        return new Object[][] {
//            {expectedResult}
//        };
//    }

    public void sendMailPositive(MailDetails mailDetails) {
        String expectedToSender = "abc@gmail.com";
        String expectedSubject = "hi";
        String expectedMailInfo = "Hello";
        String expectedCc = "adf@gmail.com";


        verify(mailServiceMock).sendTextMail(mailDetails.getToSender());

    }

    @Test(dataProvider = "dp_sendMailNegative")
    private void sendMailNegative(MailDetails mailDetails, MailException exception) throws Exception {
        try {
            mailServiceMock.sendTextMail(mailDetails);
        } catch (MailException e) {
            Assert.assertEquals(e.getErrorList(), exception.getErrorList());
        }
    }

    @DataProvider
    private Object[][] dp_sendMailNegative() {
        MailDetails mailDetails = new MailDetails(null, "test", "Sample test", "abc@gmail.com");
        ArrayList<MailErrorCode> errorList = new ArrayList<>();
        errorList.add(MailErrorCode.INVALID_RECIPIENT);

        return new Object[][]{
                {mailDetails, new MailException(errorList)},
        };
    }

}
