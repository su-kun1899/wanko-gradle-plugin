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
            plugins {
                id "red.sukun1899.wanko"
            }
            
            repositories {
                mavenCentral()
            }
            
            configurations {
                jdbcdriver
            }
            
            dependencies {
                jdbcdriver 'org.postgresql:postgresql:9.3-1100-jdbc41'
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
                url = 'jdbc:postgresql://localhost:35432/postgres'
                user = 'postgres'
                password = 'wanko'
                driverClassName = 'org.postgresql.Driver'
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
    }
}
