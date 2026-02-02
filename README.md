# java-selenium-poc
Selenium tests with Java binding on a small dummy project for practice.

## Quickstart

Prerequisites:
- JDK (matching the project `pom.xml` compiler settings). The current `pom.xml` sets `maven.compiler.source`/`target` to `25` — ensure your installed JDK matches or update these properties (common choices: 11, 17).
- Maven 3.6+ installed and on your PATH.

Build and run tests:

```powershell
mvn clean test
```

Run a single test class:

```powershell
mvn -Dtest=tests.ui.LoginTest test
```

## What this project contains

Top-level files:
- `pom.xml` — Maven project descriptor (dependencies: Selenium, WebDriverManager, JUnit Jupiter, Surefire plugin).
- `src/main/java` — production/test framework code used by tests.
- `src/test/java` — test classes.
- `src/test/resources/config.properties` — test configuration.

Key packages and purpose:
- `framework.base`
  - `BaseTest.java` — base test lifecycle (setup/teardown). If this uses JUnit annotations from `org.junit.jupiter.api`, ensure JUnit is available on the classpath.
- `framework.config`
  - `ConfigReader.java` — reads values from `src/test/resources/config.properties`.
- `framework.driver`
  - `DriverFactory.java` — creates and configures WebDriver instances (uses WebDriverManager to resolve binaries).
- `framework.pages`
  - `BasePage.java`, `LoginPage.java`, `InventoryPage.java` — Page Object Model classes for the AUT.
- `framework.utils`
  - `WaitUtils.java`, `ScreenshotUtils.java`, `LoggerUtils.java` — helpers for waiting, screenshots, and logging.
- `src/test/java/tests/ui`
  - `LoginTest.java` — example UI test class that uses the framework.

## Configuration

Default configuration lives in `src/test/resources/config.properties`. Typical keys you may find or want to add:
- `base.url` — application under test base URL
- `browser` — browser name (chrome, firefox)
- `headless` — whether to run browser headless

Override properties at runtime via system properties:

```powershell
mvn -Dbase.url=https://example.com -Dbrowser=chrome test
```

## Notes & Troubleshooting

- JUnit imports not resolving: if you see missing imports for `@BeforeEach`/`@AfterEach` from `org.junit.jupiter.api`, check `pom.xml` for the JUnit dependency. By default tests under `src/test/java` only need JUnit declared with `<scope>test</scope>`; if you are using JUnit annotations in `src/main/java` (framework code) then JUnit must be available on the main classpath (remove the `<scope>test</scope>` or move annotations to test classes).

- Compiler level mismatch: `pom.xml` currently sets the compiler source/target to `25`. If your local JDK is older (e.g., 11 or 17), change these values in `pom.xml` to match your JDK to avoid compilation issues.

- Browser driver errors: ensure WebDriverManager can download driver binaries (network access). If running in CI, cache driver binaries or pin versions.

- Where to find logs/screenshots: `framework/utils/LoggerUtils` and `ScreenshotUtils` control where artifacts are written — search the code for the output path or adjust the utils to point to `target/`.

## Contributing

- Keep tests small and deterministic.
- Add new page objects under `framework.pages` and reusable helpers under `framework.utils`.
- Run `mvn -Dtest=<YourTestClass> test` while developing locally.
