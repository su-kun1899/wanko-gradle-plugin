package red.sukun1899.wanko

import groovy.sql.Sql
import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import red.sukun1899.wanko.test.DatabaseProperties
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

    def "Load task"() {
        given:
        def sqlDir = testProjectDir.newFolder("sql")
        testProjectDir.newFile("sql" + File.separator + "1_create_table.sql") << """
            CREATE TABLE person (
              id CHAR(36) PRIMARY KEY,
              name VARCHAR (10) NOT NULL,
              age INT NOT NULL
            );
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
                .withArguments('wankoLoad')
                .withPluginClasspath()
                .withDebug(true)
                .build()

        then:
        result.task(":wankoLoad").outcome == TaskOutcome.SUCCESS
        println(result.output)

        cleanup:
        sql.execute("DROP TABLE person;")
    }
}
