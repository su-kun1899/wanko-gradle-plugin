package red.sukun1899.wanko


import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class WankoPluginBuildSpec extends Specification {
    def "Load task"() {
        given:
        def testProjectDir = new File(
                this.getClass().getResource('/buildspec/project').toURI()
        )

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments('wankoLoad')
                .withPluginClasspath()
                .withDebug(true)
                .build()

        then:
        result.task(":wankoLoad").outcome == TaskOutcome.SUCCESS
    }
}
