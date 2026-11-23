# GUI Test Automation — the-internet

Selenium WebDriver + TestNG UI automation for two scenarios on
<https://the-internet.herokuapp.com/>, built on the Page Object Model.

## Scenarios covered

| Test | Flow | Assertion |
|---|---|---|
| `FileUploadTest` | Home → File Upload → choose a small PNG → Upload | Confirmation heading is `File Uploaded!` **and** the page echoes the uploaded file name |
| `DynamicLoadingTest` | Home → Dynamic Loading → Example 2 → Start → wait for loading to finish | Rendered text is `Hello World!` |

## How to run

Prerequisites: JDK 17+, Maven 3.8+, Google Chrome installed. Driver binaries are resolved
automatically by Selenium Manager — nothing to download or configure.

```bash
mvn clean test
```

The browser is **visible by default** so a run can be watched. Run headless (for CI), or on
another browser, without touching any code:

```bash
mvn clean test -Dheadless=true
mvn clean test -Dbrowser=firefox
```

Run a single test class:

```bash
mvn clean test -Dsuite=src/test/resources/suites/testng.xml -Dtest=FileUploadTest
```

Reports are written to `target/surefire-reports/` (open `index.html` or `emailable-report.html`).

## Project structure

```
src/test/java/com/assessment/gui/
├── base/      BaseTest        — per-method browser lifecycle, fluent entry point
├── config/    Configuration   — config.properties + -D overrides
├── data/      *TestData       — typed models of the JSON test data
├── driver/    DriverFactory   — browser options per BrowserType
│              DriverManager   — ThreadLocal session, safe for parallel runs
├── pages/     BasePage        — waits and interaction helpers (shared once)
│              HomePage, FileUploadPage, FileUploadedPage,
│              DynamicLoadingPage, DynamicLoadingExamplePage
├── tests/     FileUploadTest, DynamicLoadingTest
└── utils/     JsonDataReader, TestResources

src/test/resources/
├── config.properties           base URL, browser, headless, timeouts
├── files/sample-image.png      upload fixture
├── testdata/*.json             externalised scenario data
└── suites/testng.xml           suite definition (parallel by class)
```

## Design notes

- **Page Object Model** — one class per page; locators are `private static final By`
  constants and never leak into a test.
- **Fluent design** — each page action returns the page object the user lands on, so a test
  reads as the journey itself:
  ```java
  homePage().openFileUpload().chooseFile(file).submit();
  ```
  A navigation that changes page returns the *new* page object, which keeps the fluency
  honest rather than returning `this` everywhere.
- **OOP principles** — `BasePage` and `BaseTest` hold shared behaviour (inheritance),
  `DriverFactory` hides browser construction behind a `BrowserType` enum (a closed set, so the
  `switch` needs no default branch), and every page exposes intent-revealing methods rather
  than its elements (encapsulation).
- **Synchronisation** — all interaction goes through `WebDriverWait` conditions in `BasePage`;
  there is no `Thread.sleep` in the project. Two places need more than a generic wait:
  - *Dynamic Loading* waits for the loading indicator to become **invisible**, then for the
    result element to become visible.
  - *File Upload* waits for `#uploaded-files`, an element that exists only on the result page.
    Both the form page and the result page contain an `<h3>`, so waiting on the heading alone
    is satisfied by the previous page before the submit has navigated — a wait that looks
    correct but synchronises on nothing. The result page object performs this check in its
    constructor, so no getter can return a value read from the wrong page.
- **Data-driven** — scenario inputs and expected values live in `testdata/*.json`, are mapped
  to POJOs by Jackson, and reach tests via `@DataProvider`. Adding a case means adding a JSON
  object, not Java code.
- **Thread safety** — `DriverManager` keeps one `WebDriver` per thread, so the suite can run
  `parallel="classes"` as configured without cross-talk.
- **Configuration** — nothing environment-specific is compiled in; every value is overridable
  with `-D` at run time.

## Deliberately not included

Kept out because nothing in these two scenarios needs them, and unused code is a cost a
reviewer has to read:

- **No Edge support** — Chrome and Firefox are enough to prove the factory pattern, and those
  are the two that were actually run. A third untested branch would be decoration.
- **No per-case wait timeouts in the test data.** The loading step takes ~5s and the shared
  explicit wait is 15s, so a configurable timeout per case added a field, a `Duration` and a
  second `WebDriverWait` to solve a problem that does not exist.
- **No screenshot listener, retry analyser or Allure reporting.** All defensible in a real
  suite; none is required here, and each one is another moving part to justify.

## Running from IntelliJ

Open this repository as a Maven project (`File → Open` → select its `pom.xml`). Right-click
`FileUploadTest` or `DynamicLoadingTest` → *Run*.

Running a test class directly from the IDE uses the TestNG runner rather than Maven, so Maven
`-D` flags do not apply; the IDE run takes its settings from `config.properties`. To change the
browser or headless mode for an IDE run, either edit `config.properties` or add
`-Dheadless=true` to *VM options* in the run configuration.
