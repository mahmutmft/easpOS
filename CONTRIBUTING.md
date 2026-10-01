# Contributing to ESAP

Thank you for helping improve ESAP. Contributions to the application, tests, documentation, restaurant workflows, and translations are welcome.

By participating in this project, you agree to follow the [Code of Conduct](CODE_OF_CONDUCT.md).

## Before You Start

- Check the existing issues before opening a new one.
- Open an issue before beginning a large feature or architectural change.
- Keep each pull request focused on one change.
- Do not include generated files, IDE settings, or unrelated formatting changes.

## Development Setup

You need Java 21 and Maven 3.9 or newer.

```bash
git clone https://github.com/mahmutmft/pos.git
cd pos
mvn test
```

## Making a Change

1. Fork the repository.
2. Create a branch with a clear name, such as `feature/table-map` or `fix/stock-validation`.
3. Make a focused change that follows the existing project structure.
4. Add or update tests when behavior changes.
5. Run the complete test suite with `mvn test`.
6. Commit the change with a short, descriptive message.
7. Open a pull request against the main repository.

## Pull Requests

A pull request should:

- Explain what changed and why.
- Reference its related issue when one exists.
- Mention any behavior or design decisions reviewers should know about.
- Include screenshots for visible interface changes.
- Pass the full test suite.

Review feedback is part of the contribution process. A change may need revisions before it can be merged.

## Tests

Tests are located under `src/test/java`. Cover both normal behavior and relevant edge cases. Run a specific test class with:

```bash
mvn -Dtest=ItemEdgeCaseTest test
```

## Languages

ESAP plans to support Macedonian, Albanian, and English. Translation contributions should preserve the meaning and terminology used in restaurant workflows rather than translating words without context.

## Security

Do not report security vulnerabilities in a public issue. Follow the private reporting process in [SECURITY.md](SECURITY.md).
