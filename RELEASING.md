# Releasing

1. Update the `VERSION_NAME` in `gradle.properties` to the release version.

2. Update the `CHANGELOG.md`:
    1. Change the `Unreleased` header to the release version.
    2. Add a link URL to ensure the header link works.
    3. Add a new `Unreleased` section to the top.

3. Update the `README.md`:
    1. Change the "Download" section to reflect the new release version.

4. Commit

   ```
   $ git commit -am "Prepare version X.Y.X"
   ```

5. Tag

   ```
   $ git tag -am "Version X.Y.Z" X.Y.Z
   ```

6. Update the `VERSION_NAME` in `gradle.properties` to the next "SNAPSHOT" version.

7. Commit

   ```
   $ git commit -am "Prepare next development version"
   ```

8. Push!

   ```
   $ git push && git push --tags
   ```

   This will trigger a GitHub Action workflow which will create a GitHub release. It will also publish to maven central automatically.

If the automation fails for some reason, manually release and upload artifacts:
   1. Checkout the commit from step 4.
   2. Run `./gradlew clean publish`
   3. Visit [Sonatype Nexus](https://oss.sonatype.org/) and promote the artifact.
   4. If either fails, drop the Sonatype repo, fix the problem, commit, and restart this section.
