package com.assessment.gui.tests;

import com.assessment.gui.base.BaseTest;
import com.assessment.gui.data.FileUploadTestData;
import com.assessment.gui.pages.FileUploadedPage;
import com.assessment.gui.utils.JsonDataReader;
import com.assessment.gui.utils.TestResources;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FileUploadTest extends BaseTest {

    private static final String TEST_DATA = "testdata/file-upload.json";

    @DataProvider(name = "fileUploadData")
    public Object[][] fileUploadData() {
        return JsonDataReader.asDataProvider(TEST_DATA, FileUploadTestData.class);
    }

    @Test(dataProvider = "fileUploadData",
            description = "A file selected on the File Upload page is uploaded and confirmed back to the user")
    public void shouldUploadFileSuccessfully(FileUploadTestData data) {
        FileUploadedPage result = homePage()
                .openFileUpload()
                .chooseFile(TestResources.path(data.getFile()))
                .submit();

        assertEquals(result.heading(), data.getExpectedHeading(),
                "Upload confirmation heading did not match");
        assertEquals(result.uploadedFileName(), data.expectedFileName(),
                "The application did not confirm the uploaded file name");
    }
}
