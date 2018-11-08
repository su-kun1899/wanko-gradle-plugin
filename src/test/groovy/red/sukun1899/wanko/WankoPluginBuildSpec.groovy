package red.sukun1899.wanko

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class WankoPluginBuildSpec extends Specification {
    TemporaryFolder testProjectDir
    File buildFile

    def setup() {
        testProjectDir = new TemporaryFolder()
        testProjectDir.create()
        buildFile = testProjectDir.newFile 'build.gradle'
        buildFile << """
            buildscript {
                repositories {
                    mavenCentral()
                }
            
                dependencies {
                    classpath 'postgresql:postgresql:9.0-801.jdbc4'
                }
            }
            
            plugins {
                id "red.sukun1899.wanko"
            }
        """
    }

    def cleanup() {
        testProjectDir.delete()
    }

    def "Load task"() {
        given:
        buildFile << """
            wanko {
                url = 'jdbc:postgresql://localhost:25432/gengar-sayque'
                user = 'gengar'
                password = 'gengar'
                driverClassName = 'org.postgresql.Driver'
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments('wankoLoad')
                .withPluginClasspath()
                .build()

        then:
        result.task(":wankoLoad").outcome == TaskOutcome.SUCCESS
    }
}
