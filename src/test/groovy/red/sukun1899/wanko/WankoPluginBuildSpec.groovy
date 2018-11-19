package red.sukun1899.wanko

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class WankoPluginBuildSpec extends Specification {
    final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

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
    }

    def cleanup() {
        testProjectDir.delete()
    }

    def "Load task"() {
        given:
        // TODO 接続先情報を外部ファイルに
        buildFile << """
            wanko {
                url = 'jdbc:postgresql://localhost:35432/postgres'
                user = 'postgres'
                password = 'wanko'
                driverClassName = 'org.postgresql.Driver'
                sqlDir = 'sql'
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
    }
}
