package red.sukun1899.wanko

import groovy.sql.Sql
import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class WankoPluginBuildSpec extends Specification {
    final def testProjectDir = new TemporaryFolder()
    final def databaseProps = new DatabaseProperties()
    File buildFile
    Sql sql

    def setup() {
        testProjectDir.create()
        buildFile = testProjectDir.newFile('build.gradle')
        buildFile << """
            buildscript {
                repositories {
                    mavenCentral()
                }
            
                dependencies {
                    classpath 'org.postgresql:postgresql:9.3-1100-jdbc41'
                }
            }
            
            plugins {
                id "red.sukun1899.wanko"
            }
        """
        sql = Sql.newInstance(
                databaseProps.url,
                databaseProps.user,
                databaseProps.password,
                databaseProps.driverClassName,
        )
    }

    def cleanup() {
        testProjectDir.delete()
    }

    def "Run task (execute sql files)"() {
        given:
        def sqlDir = testProjectDir.newFolder("sql")
        testProjectDir.newFile("sql" + File.separator + "1_create_table.sql") << """
            CREATE TABLE person (
              id CHAR(36) PRIMARY KEY,
              name VARCHAR (10) NOT NULL,
              age INT NOT NULL
            );
        """
        testProjectDir.newFile("sql" + File.separator + "2_dummy.txt") << """
            It should be skipped.
        """
        testProjectDir.newFile("sql" + File.separator + "3_insert.sql") << """
            INSERT INTO person VALUES ('${UUID.randomUUID().toString()}', 'Alice', 18);
            INSERT INTO person VALUES ('${UUID.randomUUID().toString()}', 'Bob', 34);
        """

        and: 'execute sql files recursively'
        testProjectDir.newFolder("sql", "sub")
        testProjectDir.newFile("sql" + File.separator + "sub" + File.separator + "0_insert.sql") << """
            INSERT INTO person VALUES ('${UUID.randomUUID().toString()}', 'Charlie', 22);
            INSERT INTO person VALUES ('${UUID.randomUUID().toString()}', 'Dave', 41);
        """

        and:
        buildFile << """
            wanko {
                url = '${databaseProps.url}'
                user = '${databaseProps.user}'
                password = '${databaseProps.password}'
                driverClassName = '${databaseProps.driverClassName}'
                sqlDir = '${sqlDir.absolutePath}'
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments('wankoRun')
                .withPluginClasspath()
                .withDebug(true)
                .build()

        then:
        result.task(":wankoRun").outcome == TaskOutcome.SUCCESS

        and:
        def rows = sql.rows("""SELECT * FROM person;""")
        rows.size() == 4

        cleanup:
        sql.execute("DROP TABLE person;")
    }
}
