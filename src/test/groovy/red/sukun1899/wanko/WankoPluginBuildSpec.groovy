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
    }

    def cleanup() {
        testProjectDir.delete()
    }

    def "hello world task prints hello world"() {
        given:
        buildFile << """
            task helloWorld {
                doLast {
                    println 'Hello world!'
                }
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments('helloWorld')
                .build()

        then:
        result.output.contains('Hello world!')
        result.task(":helloWorld").outcome == TaskOutcome.SUCCESS
    }
}
