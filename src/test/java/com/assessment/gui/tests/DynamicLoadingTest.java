package com.assessment.gui.tests;

import com.assessment.gui.base.BaseTest;
import com.assessment.gui.data.DynamicLoadingTestData;
import com.assessment.gui.utils.JsonDataReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DynamicLoadingTest extends BaseTest {

    private static final String TEST_DATA = "testdata/dynamic-loading.json";

    @DataProvider(name = "dynamicLoadingData")
    public Object[][] dynamicLoadingData() {
        return JsonDataReader.asDataProvider(TEST_DATA, DynamicLoadingTestData.class);
    }

    @Test(dataProvider = "dynamicLoadingData",
            description = "Content rendered after the loading indicator disappears shows the expected text")
    public void shouldDisplayTextAfterDynamicLoading(DynamicLoadingTestData data) {
        String loadedText = homePage()
                .openDynamicLoading()
                .openExample(data.getExampleNumber())
                .start()
                .waitUntilLoadingFinished()
                .loadedText();

        assertEquals(loadedText, data.getExpectedText(),
                "Text displayed after dynamic loading did not match");
    }
}
