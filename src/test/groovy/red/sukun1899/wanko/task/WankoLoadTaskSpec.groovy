package red.sukun1899.wanko.task

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import red.sukun1899.wanko.WankoExtension
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class WankoLoadTaskSpec extends Specification {
    def "hello"() {
        given:
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'red.sukun1899.wanko'
        def sut = project.tasks.wankoLoad as WankoLoadTask
        def extension = project.extensions.wanko as WankoExtension

        when:
        sut.loadData()

        then:
        true
    }
}
