package red.sukun1899.wanko

import org.gradle.testfixtures.ProjectBuilder
import red.sukun1899.wanko.task.WankoLoadTask
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class WankoPluginSpec extends Specification {

    def "Apply wanko plugin"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.pluginManager.apply 'red.sukun1899.wanko'

        then:
        project.plugins[0] instanceof WankoPlugin
        project.extensions.wanko instanceof WankoExtension
        project.tasks.wankoLoad instanceof WankoLoadTask
    }
}
